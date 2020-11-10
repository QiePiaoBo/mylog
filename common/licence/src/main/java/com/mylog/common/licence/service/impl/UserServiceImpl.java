package com.mylog.common.licence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.common.licence.enumcenter.GroupEnum;
import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.mapper.UserMapper;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.model.vo.UserVO;
import com.mylog.common.licence.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.common.licence.service.PasswordService;
import com.mylog.entitys.entitys.entity.Message;
import com.mylog.entitys.entitys.entity.Person;
import com.mylog.entitys.entitys.entity.Result;
import com.mylog.entitys.entitys.entity.Status;
import com.mylog.entitys.entitys.vo.PersonVo;
import com.mylog.tools.utils.session.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  用户服务实现类
 * </p>
 * @author Dylan
 * @since 2020-05-24
 */
@Service
@RefreshScope
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordService passwordService;


    /**
     * 获取用户列表
     * @param page
     * @return
     */
    @Override
    public Result selectUserList(Page<User> page) {
        Result result = null;
        IPage<User> userPage = userMapper.selectUserList(page);
        List<User> userList = userPage.getRecords();
        List<UserVO> userVOList = new ArrayList<>();
        for (User user:userList){
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        result = new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(userVOList).page(page.getCurrent()).size(page.getSize()).total(page.getTotal()).build();
        return result;
    }

    /**
     * 根据id username phone mail获取用户
     * @param userDTO
     * @return
     */
    @Override
    public Result selectOne(UserDTO userDTO) {
        Result result;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userDTO.getId()!=null && userDTO.getId()!=0){
            queryWrapper.eq("id", userDTO.getId());
        }
        if (userDTO.getUsername()!=null&&userDTO.getUsername().length()>0){
            queryWrapper.eq("username", userDTO.getUsername());
        }
        if (userDTO.getPhone()!=null&&userDTO.getPhone().length()>0){
            queryWrapper.eq("phone", userDTO.getPhone());
        }
        if (userDTO.getMail()!=null&&userDTO.getMail().length()>0){
            queryWrapper.eq("mail", userDTO.getMail());
        }
        User user = userMapper.selectOne(queryWrapper);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        if (userVO.getId()!=null && userVO.getId() > 0){
            result = new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(userVO).build();
        }else {
            return null;
        }
        return result;
    }


    /**
     * 添加用户
     * @param userDTO
     * @return
     */
    @Override
    public Result addUser(UserDTO userDTO){
        Result result;
        // 设置默认用户组
        userDTO.setUsergroup(GroupEnum.USER_GROUP.getGroupId());
        // 若未传入密码，则设置默认密码为123456
        if (userDTO.getPassword()==null || userDTO.getPassword().length()==0){
            userDTO.setPassword(passwordService.createPassword("123456"));
        }
        if (userDTO.getUsername()==null || userDTO.getUsername().length()==0){
            result = new Result.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
            return result;
        }
        if (userDTO.getPhone()==null || userDTO.getPhone().length()==0){
            result = new Result.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
            return result;
        }
        User user = new User();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, user);
        BeanUtils.copyProperties(userDTO, userVO);
        int addResult = userMapper.insert(user);
        if (addResult>0){
            return new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(user).build();
        }
        else {
            return new Result.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
        }
    }


    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    @Override
    public Result deleteOne(UserDTO userDTO){
        Result result;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userDTO.getId()!=null && userDTO.getId()>0){
            queryWrapper.eq("id", userDTO.getId());
        }
        if (userDTO.getUsername()!=null && userDTO.getUsername().length()>0){
            queryWrapper.eq("username",userDTO.getUsername());
        }
        List<User> list = userMapper.selectList(queryWrapper);
        if (list == null || list.size() != 1){
            result = new Result.Builder(Status.QUERY_ERROR.getStatus(), Message.QUERY_ERROR.getMsg()).build();
        }else {
            User user = list.get(0);
            user.setDel(true);
            int update = userMapper.update(user, queryWrapper);
            if (update == 0){
                result = new Result.Builder(Status.UPDATE_ERROR.getStatus(),Message.UPDATE_ERROR.getMsg()).build();
            }
            result = new Result.Builder().build();
        }
        return result;
    }

    /**
     * 修改用户
     * @param userDTO
     * @return
     */
    @Override
    public Result exchange(UserDTO userDTO){
        Result result;
        User user = new User();
        // 密码加密
        if (userDTO.getPassword()!=null && userDTO.getPassword().length()>0){
            userDTO.setPassword(passwordService.createPassword(userDTO.getPassword()));
        }
        BeanUtils.copyProperties(userDTO, user);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", userDTO.getId());

        int update = userMapper.update(user, userUpdateWrapper);
        UserVO userVO = new UserVO();
        if (update>0){
            BeanUtils.copyProperties(userDTO, userVO);
            result = new Result.Builder().data(userVO).build();
        }
        else {
            result = new Result.Builder(Status.UPDATE_ERROR.getStatus(), Message.UPDATE_ERROR.getMsg()).data(userDTO).build();
        }
        return result;
    }

    /**
     * 登录
     * @param userDTO
     * @return
     */
    @Override
    public Result login(UserDTO userDTO) {
        Result result;
        String queryConditionName = "username";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (null == userMapper.selectOne(queryWrapper.eq(queryConditionName, userDTO.getUsername()))){
            return new Result.Builder(Status.USER_NOT_FOUND.getStatus(), Message.USER_NOT_FOUND.getMsg()).build();
        }
        User user = userMapper.selectOne(queryWrapper.eq(queryConditionName, userDTO.getUsername()));
        if (!passwordService.authenticatePassword(user.getPassword(), userDTO.getPassword())){
            return new Result.Builder(Status.ERROR_PASSWORD.getStatus(), Message.ERROR_PASSWORD.getMsg()).build();
        }
        // 验证通过，将当前用户存入session中
        Person p = new Person();
        BeanUtils.copyProperties(user, p);
        UserContext.putCurrentUser(p);
        return new Result.Builder(Status.SUCCESS.getStatus(), Message.WELCOME_TO_LOGIN.getMsg()).data(p).build();
    }

    /**
     * 登出
     * @param
     * @return
     */
    @Override
    public Result logout() {
        Result result;
        // 验证通过，将当前用户从session中删除
        UserContext.deleteCurrentUser();
        return new Result.Builder(Status.SUCCESS.getStatus(), Message.BYE_BYE.getMsg()).build();
    }

    /**
     * 获取当前的用户
     * @return
     */
    @Override
    public Result getCurrentUser(){
        PersonVo person = new PersonVo();
        try {
            person = UserContext.getCurrentUser();
        }catch (IllegalArgumentException e){
            e.fillInStackTrace();
        }
        return new Result.Builder().data(person).build();
    }
}
