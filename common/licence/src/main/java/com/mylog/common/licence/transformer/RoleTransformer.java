package com.mylog.common.licence.transformer;

import com.mylog.common.licence.entity.Role;
import com.mylog.common.licence.model.dto.RoleDTO;
import com.mylog.common.licence.model.vo.RoleVO;
import com.mylog.tools.utils.utils.Safes;

/**
 * @Classname RoleTransformer
 * @Description RoleTransformer
 * @Date 5/9/2022 11:08 AM
 */
public class RoleTransformer {

    /**
     * roleDTO -> role
     * @param roleDTO
     * @return
     */
    public static Role roleDTO2Role(RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleCode(roleDTO.getRoleCode());
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleDescription(roleDTO.getRoleDescription());
        role.setPid(roleDTO.getPid());
        role.setRoleStatus(roleDTO.getRoleStatus());
        role.setRoleSort(roleDTO.getRoleSort());
        return role;
    }

    /**
     * role -> roleVO
     * @param role
     * @return
     */
    public static RoleVO role2RoleVO(Role role){
        RoleVO roleVO = new RoleVO();
        roleVO.setId(role.getId());
        roleVO.setPid(role.getPid());
        roleVO.setRoleCode(Safes.of(role.getRoleCode()));
        roleVO.setRoleName(Safes.of(role.getRoleName()));
        roleVO.setRoleDescription(Safes.of(role.getRoleDescription()));
        roleVO.setRoleSort(role.getRoleSort());
        roleVO.setRoleStatus(role.getRoleStatus());
        return roleVO;
    }


}
