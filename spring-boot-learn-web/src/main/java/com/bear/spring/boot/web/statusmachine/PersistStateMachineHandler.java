package com.bear.spring.boot.web.statusmachine;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 16:49
 */

@Component
public class PersistStateMachineHandler extends LifecycleObjectSupport {

    private final StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine;
    private final PersistingStateChangeInterceptor interceptor = new PersistingStateChangeInterceptor();
    private final CompositePersistStateChangeListener listeners = new CompositePersistStateChangeListener();

    /**
     * Instantiates a new persist state machine handler.
     *
     * @param stateMachine the state machine
     */
    @Autowired
    public PersistStateMachineHandler(StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
        Assert.notNull(stateMachine, "State machine must be set");
        this.stateMachine = stateMachine;
    }



    //会被LifecycleObjectSupport父类的InitializingBean.afterPropertiesSet()里调用
    protected void onInit(){
        //往stateMachine加入拦截器PersistingStateChangeInterceptor
        stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.addStateMachineInterceptor(interceptor));
        //获取所有 PersistStateChangeListener的bean注册到CompositePersistStateChangeListener
        Map<String, PersistStateChangeListener> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) this.getBeanFactory(), PersistStateChangeListener.class, true, false);

        if (!matchingBeans.isEmpty()) {
            listeners.setListeners(Lists.newArrayList(matchingBeans.values()));
        }
    }

    /**
     * Handle event with entity.
     *
     * @param event the event
     * @param state the state
     * @return true if event was accepted
     */
    public boolean handleEventWithState(Message<OrderEventEnum> event, OrderStatusEnum state) {
        stateMachine.stop();
        List<StateMachineAccess<OrderStatusEnum, OrderEventEnum>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        for (StateMachineAccess<OrderStatusEnum, OrderEventEnum> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null));
        }
        stateMachine.start();
        return stateMachine.sendEvent(event);
    }

    /**
     * Adds the persist state change listener.
     *
     * @param listener the listener
     */
    public void addPersistStateChangeListener(PersistStateChangeListener listener) {
        listeners.register(listener);
    }

    /**
     * The listener interface for receiving persistStateChange events.
     * The class that is interested in processing a persistStateChange
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addPersistStateChangeListener</code> method. When
     * the persistStateChange event occurs, that object's appropriate
     * method is invoked.
     */
    public interface PersistStateChangeListener {
        /**
         * Called when state needs to be persisted.
         *
         * @param state        the state
         * @param message      the message
         * @param transition   the transition
         * @param stateMachine the state machine
         */
        void onPersist(State<OrderStatusEnum, OrderEventEnum> state, Message<OrderEventEnum> message, Transition<OrderStatusEnum, OrderEventEnum> transition,
                       StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine);
    }

    private class PersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatusEnum, OrderEventEnum> {
        @Override
        public void preStateChange(State<OrderStatusEnum, OrderEventEnum> state, Message<OrderEventEnum> message, Transition<OrderStatusEnum, OrderEventEnum> transition, StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
            listeners.onPersist(state, message, transition, stateMachine);
        }
    }

    private class CompositePersistStateChangeListener extends AbstractCompositeListener<PersistStateChangeListener> implements
            PersistStateChangeListener {

        public void onPersist(State<OrderStatusEnum, OrderEventEnum> state, Message<OrderEventEnum> message,
                              Transition<OrderStatusEnum, OrderEventEnum> transition, StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
            for (Iterator<PersistStateChangeListener> iterator = getListeners().reverse(); iterator.hasNext(); ) {
                PersistStateChangeListener listener = iterator.next();
                listener.onPersist(state, message, transition, stateMachine);
            }
        }
    }


}

