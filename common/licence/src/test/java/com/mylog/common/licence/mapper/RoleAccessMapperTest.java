package com.mylog.common.licence.mapper;

import com.mylog.common.licence.entity.Access;
import com.mylog.tools.utils.utils.PermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname UserMapperTest
 * @Description UserMapperTest
 * @Date 5/11/2022 2:56 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleAccessMapperTest {

    @Resource
    private RoleAccessMapper roleAccessMapper;

    @Resource
    private PermissionChecker permissionChecker;

    @Test
    public void getRoleId4UserFromGroup(){
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(1);
        List<Access> accesses4RoleIds = roleAccessMapper.getAccesses4RoleIds(roleIds);
        log.info("accesses for roleIds is : {}", accesses4RoleIds);
    }

    @Test
    public void permissionCheckerTest(){
        permissionChecker.hasPermission(1, "1");
    }


}
