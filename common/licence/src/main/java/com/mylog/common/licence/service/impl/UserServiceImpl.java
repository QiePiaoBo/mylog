package com.mylog.common.licence.service.impl;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mylog.common.licence.enumcenter.GroupEnum;
import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.mapper.UserMapper;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.model.vo.UserVO;
import com.mylog.common.licence.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.utils.utils.PasswordService;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.entity.Person;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.vo.PersonVo;
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
    public DataResult selectUserList(MyPage page) {
        DataResult dataResult = null;
        List<User> userList = userMapper.selectUserList(page);
        List<UserVO> userVOList = new ArrayList<>();
        for (User user:userList){
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        dataResult = new DataResult.Builder(
                Status.SUCCESS.getStatus(),
                Message.SUCCESS.getMsg())
                .data(userVOList)
                .page(page.getPageNo())
                .size(page.getPageSize())
                .total(userMapper.selectUserTotal())
                .build();
        return dataResult;
    }

    /**
     * 根据id username phone mail获取用户
     * @param userDTO
     * @return
     */
    @Override
    public DataResult selectOne(UserDTO userDTO) {
        DataResult dataResult;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userDTO.getId()!=null && userDTO.getId()!=0){
            queryWrapper.eq("id", userDTO.getId());
        }
        if (userDTO.getUserName()!=null&&userDTO.getUserName().length()>0){
            queryWrapper.eq("user_name", userDTO.getUserName());
        }
        if (userDTO.getUserPhone()!=null&&userDTO.getUserPhone().length()>0){
            queryWrapper.eq("user_phone", userDTO.getUserPhone());
        }
        User user = userMapper.selectOne(queryWrapper);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        if (userVO.getId()!=null && userVO.getId() > 0){
            dataResult = new DataResult.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(userVO).build();
        }else {
            return null;
        }
        return dataResult;
    }


    /**
     * 添加用户
     * @param userDTO
     * @return
     */
    @Override
    public DataResult addUser(UserDTO userDTO){
        DataResult dataResult;
        // 设置默认用户组
        userDTO.setUserGroup(GroupEnum.USER_GROUP.getGroupId());
        // 若未传入密码，则设置默认密码为123456
        if (userDTO.getUserPassword()==null || userDTO.getUserPassword().length()==0){
            userDTO.setUserPassword(passwordService.createPassword("123456"));
        }
        if (userDTO.getUserName()==null || userDTO.getUserName().length()==0){
            dataResult = new DataResult.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
            return dataResult;
        }
        if (userDTO.getUserPhone()==null || userDTO.getUserPhone().length()==0){
            dataResult = new DataResult.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
            return dataResult;
        }
        // 判断用户是否已经存在
        if (userMapper.selectCount(
                Wrappers.query(new User())
                        .eq("user_name", userDTO.getUserName())
                        .eq("user_phone", userDTO.getUserPhone())) > 0){
            return new DataResult.Builder(Status.USER_EXISTS.getStatus(), Message.USER_EXISTS.getMsg()).data(userDTO).build();
        }
        User user = new User();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, user);
        BeanUtils.copyProperties(userDTO, userVO);
        int addResult = userMapper.insert(user);
        if (addResult>0){
            return selectOne(userDTO);
        }
        else {
            return new DataResult.Builder(Status.INSERT_ERROR.getStatus(), Message.INSERT_ERROR.getMsg()).build();
        }
    }


    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    @Override
    public DataResult deleteOne(UserDTO userDTO){
        DataResult dataResult;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userDTO.getId()!=null && userDTO.getId()>0){
            queryWrapper.eq("id", userDTO.getId());
        }
        if (userDTO.getUserName()!=null && userDTO.getUserName().length()>0){
            queryWrapper.eq("user_name",userDTO.getUserName());
        }
        List<User> list = userMapper.selectList(queryWrapper);
        if (list == null || list.size() != 1){
            dataResult = new DataResult.Builder(Status.QUERY_ERROR.getStatus(), Message.QUERY_ERROR.getMsg()).build();
        }else {
            User user = list.get(0);
            user.setDelFlag(1);
            int update = userMapper.update(user, queryWrapper);
            if (update == 0){
                dataResult = new DataResult.Builder(Status.UPDATE_ERROR.getStatus(),Message.UPDATE_ERROR.getMsg()).build();
            }
            dataResult = new DataResult.Builder().build();
        }
        return dataResult;
    }

    /**
     * 修改用户
     * @param userDTO
     * @return
     */
    @Override
    public DataResult exchange(UserDTO userDTO){
        DataResult dataResult;
        User user = new User();
        // 密码加密
        if (userDTO.getUserPassword()!=null && userDTO.getUserPassword().length()>0){
            userDTO.setUserPassword(passwordService.createPassword(userDTO.getUserPassword()));
        }
        BeanUtils.copyProperties(userDTO, user);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", userDTO.getId());

        int update = userMapper.update(user, userUpdateWrapper);
        UserVO userVO = new UserVO();
        if (update>0){
            BeanUtils.copyProperties(userDTO, userVO);
            dataResult = new DataResult.Builder().data(userVO).build();
        }
        else {
            dataResult = new DataResult.Builder(Status.UPDATE_ERROR.getStatus(), Message.UPDATE_ERROR.getMsg()).data(userDTO).build();
        }
        return dataResult;
    }

    /**
     * 登录
     * @param userDTO
     * @return
     */
    @Override
    public DataResult login(UserDTO userDTO) {
        String queryConditionName = "user_name";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (null == userMapper.selectOne(queryWrapper.eq(queryConditionName, userDTO.getUserName()))){
            return new DataResult.Builder(Status.USER_NOT_FOUND.getStatus(), Message.USER_NOT_FOUND.getMsg()).build();
        }
        User user = userMapper.selectOne(queryWrapper.eq(queryConditionName, userDTO.getUserName()));
        if (!passwordService.authenticatePassword(user.getUserPassword(), userDTO.getUserPassword())){
            return new DataResult.Builder(Status.ERROR_PASSWORD.getStatus(), Message.ERROR_PASSWORD.getMsg()).build();
        }
        // 验证通过，将当前用户存入session中
        Person p = new Person();
        BeanUtils.copyProperties(user, p);
        UserContext.putCurrentUser(p);

        return new DataResult.Builder(Status.SUCCESS.getStatus(), Message.WELCOME_TO_LOGIN.getMsg()).data(p).build();
    }

    /**
     * 登出
     * @param
     * @return
     */
    @Override
    public DataResult logout() {
        // 验证通过，将当前用户从session中删除
        UserContext.deleteCurrentUser();
        return new DataResult.Builder(Status.SUCCESS.getStatus(), Message.BYE_BYE.getMsg()).build();
    }

    /**
     * 获取当前的用户
     * @return
     */
    @Override
    public DataResult getCurrentUser(){
        PersonVo person = new PersonVo();
        try {
            person = UserContext.getCurrentUser();
        }catch (IllegalArgumentException e){
            e.fillInStackTrace();
        }
        return new DataResult.Builder().data(person).build();
    }
}
