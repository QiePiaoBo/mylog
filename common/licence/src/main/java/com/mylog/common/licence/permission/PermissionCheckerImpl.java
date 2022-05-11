package com.mylog.common.licence.permission;

import com.mylog.common.licence.service.IUserAccessService;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.vo.PersonVo;
import com.mylog.tools.utils.session.UserContext;
import com.mylog.tools.utils.utils.PermissionChecker;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Classname PermissionCheckerImpl
 * @Description PermissionCheckerImpl
 * @Date 5/11/2022 6:52 PM
 */
@Component
public class PermissionCheckerImpl implements PermissionChecker {

    @Resource
    private IUserAccessService userAccessService;

    /**
     * 当前登录用户是否有type url的权限
     *
     * @param type
     * @param url
     * @return
     */
    @Override
    public boolean hasPermission(Integer type, String url) {
        /**
         * 需要同时满足
         * 1. 接口所需的权限 >= 用户的userType
         * 2. 当前用户的权限列表中存在该url
         */
        PersonVo currentUser = UserContext.getCurrentUser();
        if (Objects.isNull(currentUser)){
            return false;
        }
        if (type < currentUser.getUserType()){
            return false;
        }
        HttpResult httpResult = userAccessService.hasPermission(currentUser.getId(), url);
        return Objects.equals(httpResult.getStatus(), Status.SUCCESS.getStatus());
    }
}
