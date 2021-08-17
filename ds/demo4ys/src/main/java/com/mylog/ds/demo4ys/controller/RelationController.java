package com.mylog.ds.demo4ys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.ds.demo4ys.entity.RelationEntity;
import com.mylog.ds.demo4ys.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 * @Date : 2021/7/29 - 20:25
 * @Description :
 * @Function :
 */
@RestController
@RequestMapping("relation")
public class RelationController {

    @Autowired
    RelationService relationService;


    @RequestMapping("add")
    public RelationEntity addRelation(RelationEntity entity) throws Exception {

        if (relationService.addRelation(entity) > 0){
            return entity;
        }else {
            throw new Exception("添加关系异常");
        }
    }

    @RequestMapping("del")
    public String delRelation(RelationEntity entity) throws Exception{

        if (relationService.delRelation(entity) > 0) {
            return "Deleted";
        }else {
            return "Failed";
        }
    }


    @RequestMapping("update")
    public RelationEntity updateRelation(RelationEntity entity) throws Exception {

        if (null == entity.getId()){
            throw new Exception("参数缺失 : id");
        }
        if (relationService.updateRelation(entity) > 0){
            return entity;
        }else {
            throw new Exception("修改关系异常");
        }
    }


    @RequestMapping("getOne")
    public RelationEntity getOneById(RelationEntity entity) throws Exception {
       if (null == entity.getId()){
           throw new Exception("参数缺失 : id");
       }
       return relationService.getRelationById(entity);
    }


    @RequestMapping("getAll")
    public Page<RelationEntity> getRelationPage(Page<RelationEntity> page, RelationEntity entity){
        return relationService.getRelations(page, entity);
    }
}
