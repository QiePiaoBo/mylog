package com.mylog.common.licence.transformer;

import com.mylog.common.licence.entity.Access;
import com.mylog.common.licence.model.dto.AccessDTO;
import com.mylog.common.licence.model.vo.AccessVO;

/**
 * @author Dylan
 * @Date : 2022/5/9 - 23:30
 */
public class AccessTransformer {

    /**
     * accessDTO -> access
     * @param accessDTO
     * @return
     */
    public static Access accessDTO2Access(AccessDTO accessDTO){
        Access access = new Access();
        access.setId(accessDTO.getId());
        access.setAccessCode(accessDTO.getAccessCode());
        access.setAccessName(accessDTO.getAccessName());
        access.setAccessType(accessDTO.getAccessType());
        access.setAccessUri(accessDTO.getAccessUri());
        access.setAccessDescription(accessDTO.getAccessDescription());
        access.setAccessStatus(accessDTO.getAccessStatus());
        return access;
    }

    /**
     * access -> accessVO
     * @param access
     * @return
     */
    public static AccessVO access2AccessVO(Access access){
        AccessVO accessVO = new AccessVO();
        accessVO.setId(access.getId());
        accessVO.setAccessCode(access.getAccessCode());
        accessVO.setAccessName(access.getAccessName());
        accessVO.setAccessType(access.getAccessType());
        accessVO.setAccessUri(access.getAccessUri());
        accessVO.setAccessDescription(access.getAccessDescription());
        accessVO.setAccessStatus(access.getAccessStatus());
        return accessVO;
    }


}
