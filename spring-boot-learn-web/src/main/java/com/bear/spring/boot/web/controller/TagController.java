package com.bear.spring.boot.web.controller;

import com.bear.spring.boot.web.WebApplication;
import com.bear.spring.boot.web.domain.criteria.TagCriteria;
import com.bear.spring.boot.web.domain.entity.TagEntity;
import com.bear.spring.boot.web.domain.query.Page;
import com.bear.spring.boot.web.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ description: jms tag
 * @ author: guojiang.x
 * @ created: 2018-01-26 12:36
 */
@RestController
public class TagController {
    private static Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Resource
    private TagService tagService;

    @RequestMapping("/index")
    public String index(){
        TagCriteria example = new TagCriteria();
//        List<TagEntity> list = this.tagService.selectTag(example);
        Page<TagEntity> page = this.tagService.selectPage(new TagCriteria(), 0, 2);
        LOGGER.info(String.valueOf(page.getRows().size()));
        return "index";
    }

    @RequestMapping("/add")
    public String add(){
        TagEntity save = new TagEntity();
        save.setTag("yyyffffwwvvvwfffy");
        save.setTagTimeNo("20180311");
        save.setClusterEnv(1);
        save.setStatus(1);
        save.setSource(1);
        save.setCreateTime(new Date());
        save.setUpdateTime(new Date());
        int n = this.tagService.insert(save);
        LOGGER.info(String.valueOf(n));
        return String.valueOf(n);
    }
}
