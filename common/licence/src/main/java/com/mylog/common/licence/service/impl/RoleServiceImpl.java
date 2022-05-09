package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Role;
import com.mylog.common.licence.mapper.RoleMapper;
import com.mylog.common.licence.model.vo.RoleVO;
import com.mylog.common.licence.service.IRoleService;
import com.mylog.common.licence.transformer.RoleTransformer;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RoleServiceImpl
 * @Description RoleServiceImpl
 * @Date 5/9/2022 11:26 AM
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper mapper;

    @Override
    public HttpResult selectRoleList(MyPage myPage) {
        if (!myPage.isValid()) {
            return PageDataResult.failed().data(new ArrayList<RoleVO>()).build();
        }
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
}
