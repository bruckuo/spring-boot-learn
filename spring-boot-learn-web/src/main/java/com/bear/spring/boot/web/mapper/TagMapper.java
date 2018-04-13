package com.bear.spring.boot.web.mapper;

import com.bear.spring.boot.web.domain.criteria.TagCriteria;
import com.bear.spring.boot.web.domain.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ description: 数据访问接口
 * @ author: guojiang.x
 * @ created: 2018-03-23 18:14
 */
@Mapper
public interface TagMapper {

    /**
     * 根据ID查询
     * @param id 查询条件
     * @return 实体
     */
    TagEntity selectOneTag(Long id);

    /**
     * 条件查询
     * @param criteria 条件
     * @return 集合
     */
    List<TagEntity> selectTag(TagCriteria criteria);

    /**
     * 插入数据
     * @param entity 实体
     * @return int
     */
    int insertTag(TagEntity entity);

    /**
     * 批量插入
     * @param jmsTags 集合
     * @return int
     */
    int insertTagBatch(List<TagEntity> jmsTags);

    /**
     * 计算条数
     * @param criteria 查询条件
     * @return int
     */
    int countTag(TagCriteria criteria);

    /**
     * 更新数
     * @param entity 实体
     * @return int
     */
    int updateTag(TagEntity entity);

    /**
     * 批量更新
     * @param jmsTags 集合
     * @return int
     */
    int updateTagBatch(List<TagEntity> jmsTags);

}
