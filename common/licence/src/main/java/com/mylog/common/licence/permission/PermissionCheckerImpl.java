package com.mylog.common.licence.permission;

import com.mylog.common.licence.service.IUserAccessService;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.vo.PersonVo;
import com.mylog.tools.utils.session.UserContext;
import com.mylog.tools.utils.utils.PermissionChecker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Classname PermissionCheckerImpl
 * @Description PermissionCheckerImpl
 * @Date 5/11/2022 6:52 PM
 */
@Service
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
        PersonVo currentUser = UserContext.getCurrentUser();
        if (Objects.isNull(currentUser)){
            return false;
        }
        HttpResult httpResult = userAccessService.hasPermission(currentUser.getId(), url);
        return Objects.equals(httpResult.getStatus(), Status.SUCCESS.getStatus());
    }
}
