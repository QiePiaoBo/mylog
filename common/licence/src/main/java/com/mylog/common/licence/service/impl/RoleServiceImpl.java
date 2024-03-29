package com.mylog.common.licence.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mylog.common.licence.entity.Role;
import com.mylog.common.licence.mapper.RoleMapper;
import com.mylog.common.licence.model.dto.RoleDTO;
import com.mylog.common.licence.model.vo.RoleVO;
import com.mylog.common.licence.service.IRoleService;
import com.mylog.common.licence.transformer.RoleTransformer;
import com.mylog.tools.model.model.exception.MyException;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname RoleServiceImpl
 * @Description RoleServiceImpl
 * @Date 5/9/2022 11:26 AM
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper mapper;

    /**
     * 分页查询角色
     * @param myPage
     * @return
     */
    @Override
    public HttpResult selectRoleList(MyPage myPage) {
        myPage.checkValid();
        List<Role> roles = mapper.selectRoleList(myPage);
        List<RoleVO> roleVOS = new ArrayList<>();
        Safes.of(roles).forEach(m -> roleVOS.add(RoleTransformer.role2RoleVO(m)));

        return PageDataResult
                .success()
                .data(roleVOS)
                .page(myPage.getPageNo())
                .size(myPage.getPageSize())
                .total(mapper.selectRoleTotal())
                .build();
    }

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    @Override
    public HttpResult create(RoleDTO roleDTO) {
        Role role = RoleTransformer.roleDTO2Role(roleDTO);
        if (Objects.nonNull(role.getId())){
            throw new MyException("Error, found id in insert object.");
        }
        if (mapper.selectCount(Wrappers.query(new Role()).eq("role_code", role.getRoleCode())) > 0){
            throw new MyException("Duplicate error, record already exists.");
        }
        int insert = mapper.insert(role);
        if (insert <= 0){
            log.error("Error insert {}", roleDTO);
        }
        Role inserted = mapper.selectOne(Wrappers.query(new Role()).eq("role_code", role.getRoleCode()));
        return DataResult.getBuilder().data(RoleTransformer.role2RoleVO(inserted)).build();
    }

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    @Override
    public HttpResult getById(Integer id) {
        if (Objects.isNull(id)){
            throw new MyException("Error, id in getById must not be null.");
        }
        Role role = mapper.selectById(id);
        return DataResult
                .success()
                .data(RoleTransformer.role2RoleVO(role))
                .build();
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @Override
    public HttpResult deleteById(Integer id) {
        if (Objects.isNull(id)){
            throw new MyException("Error, id in deleteById must not be null.");
        }
        mapper.logicalDeletionById(id);
        Role role = mapper.selectById(id);
        return DataResult
                .success()
                .data(RoleTransformer.role2RoleVO(role))
                .build();
    }

    /**
     * 根据id更新角色
     *
     * @param roleDTO
     * @return
     */
    @Override
    public HttpResult updateById(RoleDTO roleDTO) {
        if (Objects.isNull(roleDTO.getId())){
            throw new MyException("Error, id in updateById must not be null.");
        }
        Role role = RoleTransformer.roleDTO2Role(roleDTO);
        mapper.updateById(role);
        Role updated = mapper.selectById(roleDTO.getId());
        return DataResult
                .success()
                .data(RoleTransformer.role2RoleVO(updated))
                .build();
    }

    /**
     * 根据id列表获取角色列表
     *
     * @param ids
     * @return
     */
    @Override
    public HttpResult selectRoleListByIds(List<Integer> ids) {
        List<Role> roles = mapper.selectRoleListByIds(ids);
        List<RoleVO> roleVOS = Safes.of(roles)
                .stream()
                .map(RoleTransformer::role2RoleVO)
                .collect(Collectors.toList());
        return DataResult
                .success()
                .data(roleVOS)
                .build();
    }
}
