package com.mylog.ds.demo4ys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.ds.demo4ys.entity.RelationEntity;
import com.mylog.ds.demo4ys.mapper.RelationMapper;
import com.mylog.ds.demo4ys.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * @author Dylan
 * @Date : 2021/7/29 - 20:25
 * @Description :
 * @Function :
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationMapper relationMapper;

    @Override
    public int addRelation(RelationEntity entity){

        return relationMapper.insert(entity);
    }

    @Override
    public int delRelation(RelationEntity entity){

        return relationMapper.delete(Wrappers.query(entity));
    }

    @Override
    public int updateRelation(RelationEntity entity){

        return relationMapper.updateById(entity);
    }

    @Override
    public RelationEntity getRelationById(RelationEntity entity){

        return relationMapper.selectById(entity.getId());
    }

    @Override
    public Page<RelationEntity> getRelations(Page<RelationEntity> page, RelationEntity entity){

        return relationMapper.selectPage(page, Wrappers.query(entity));
    }
}
