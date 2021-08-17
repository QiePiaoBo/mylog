package com.mylog.ds.demo4ys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.ds.demo4ys.entity.RelationEntity;

public interface RelationService {

    int addRelation(RelationEntity entity);

    int delRelation(RelationEntity entity);

    int updateRelation(RelationEntity entity);

    RelationEntity getRelationById(RelationEntity entity);

    Page<RelationEntity> getRelations(Page<RelationEntity> page, RelationEntity entity);
}
