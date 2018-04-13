package com.bear.spring.boot.web.service.impl;

import com.bear.spring.boot.web.domain.criteria.TagCriteria;
import com.bear.spring.boot.web.domain.entity.TagEntity;
import com.bear.spring.boot.web.mapper.TagMapper;
import com.bear.spring.boot.web.service.base.AbstractBaseService;
import com.bear.spring.boot.web.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ description: 实现类
 * @ author: guojiang.x
 * @ created: 2018-03-23 18:26
 */
@Service
public class TagServiceImpl extends AbstractBaseService<TagEntity, TagCriteria, Long> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public TagEntity selectOne(Long id) {
        return this.tagMapper.selectOneTag(id);
    }

    @Override
    public int insert(TagEntity tagEntity) {
        return this.tagMapper.insertTag(tagEntity);
    }

    @Override
    public int insertBatch(List<TagEntity> list) {
        return this.tagMapper.insertTagBatch(list);
    }

    @Override
    public int update(TagEntity tagEntity) {
        return this.tagMapper.updateTag(tagEntity);
    }

    @Override
    public int updateBatch(List<TagEntity> list) {
        return this.tagMapper.updateTagBatch(list);
    }

    @Override
    public List<TagEntity> selectList(TagCriteria tagCriteria) {
        return this.tagMapper.selectTag(tagCriteria);
    }

    @Override
    public int count(TagCriteria tagCriteria) {
        return this.tagMapper.countTag(tagCriteria);
    }

}
