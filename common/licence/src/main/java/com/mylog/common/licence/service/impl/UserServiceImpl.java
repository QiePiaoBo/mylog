package com.mylog.common.licence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mylog.common.licence.EnumCenter.GroupEnum;
import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.mapper.UserMapper;
import com.mylog.common.licence.model.DTO.UserDTO;
import com.mylog.common.licence.model.VO.UserVO;
import com.mylog.common.licence.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.common.licence.service.PasswordService;
import com.mylog.common.licence.session.UserLoginContext;
import com.mylog.tools.lic.entity.Person;
import com.mylog.tools.lic.entity.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserLoginContext userContext;
    /**
     * 获取用户列表
     * @param page
     * @return
     */
    @Override
    public Result selectUserList(Page<User> page) {
        Result result = new Result();
        IPage<User> userIPage = userMapper.selectUserList(page);
        List<User> userList = userIPage.getRecords();
        List<UserVO> userVOList = new ArrayList<>();
        for (User user:userList){
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        result.put("status", 200);
        result.put("message", "查询成功");
        result.put("data", userVOList);
        result.put("pageNumber", page.getCurrent());
        result.put("pageSize", page.getSize());
        result.put("totalSize", page.getTotal());
        return result;
    }

    /**
     * 根据id username phone mail获取用户
     * @param userDTO
     * @return
     */
    @Override
    public Result selectOne(UserDTO userDTO) {
        Result result = new Result();
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
            result.put("status",200);
            result.put("msg","查询成功");
            result.put("data",userVO);
        }
        return result;
    }


    @Override
    public Result addUser(UserDTO userDTO){
        Result result = new Result();
        // 设置默认用户组
        userDTO.setUsergroup(GroupEnum.USER_GROUP.getGroupId());
        // 若未传入密码，则设置默认密码为123456
        if (userDTO.getPassword()==null || userDTO.getPassword().length()==0){
            userDTO.setPassword(passwordService.createPassword("123456"));
        }
        if (userDTO.getUsername()==null || userDTO.getUsername().length()==0){
            result.put("status", 1001).put("msg","添加失败").put("data","用户名缺失");
            return result;
        }
        if (userDTO.getPhone()==null || userDTO.getPhone().length()==0){
            result.put("status", 1001).put("msg","添加失败").put("data","手机号缺失");
            return result;
        }
        User user = new User();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, user);
        BeanUtils.copyProperties(userDTO, userVO);
        int addResult = userMapper.insert(user);
        if (addResult>0){
            return result.put("status",200).put("msg","添加成功").put("data", user);
        }
        else {
            return result.put("status",1000).put("msg","添加失败").put("data", userDTO);
        }
    }


    /**
     * 删除一个用户
     * @param userDTO
     * @return
     */
    @Override
    public Result deleteOne(UserDTO userDTO){
        Result result = new Result();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userDTO.getId()!=null && userDTO.getId()>0){
            queryWrapper.eq("id", userDTO.getId());
        }
        if (userDTO.getUsername()!=null && userDTO.getUsername().length()>0){
            queryWrapper.eq("username",userDTO.getUsername());
        }
        int resDelete = userMapper.delete(queryWrapper);
        if (resDelete > 0){
            result.put("status", 200);
            result.put("msg","删除成功");
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
        Result result = new Result();
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
            result.put("status", 200);
            result.put("msg","更新成功");
            result.put("data", userVO);
        }
        else {
            result.put("status",2222);
            result.put("msg", "更新失败");
            result.put("data",userDTO);
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
        Result result = new Result();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (null == userMapper.selectOne(queryWrapper.eq("username", userDTO.getUsername()))){
            return result.put("status", 1001).put("msg", "用户名不存在");
        }
        User user = userMapper.selectOne(queryWrapper.eq("username", userDTO.getUsername()));
        if (!passwordService.authenticatePassword(user.getPassword(), userDTO.getPassword())){
            return result.put("status", 1002).put("msg","密码校验失败");
        }
        // 验证通过，将当前用户存入session中
        Person p = new Person();
        BeanUtils.copyProperties(user, p);
        userContext.putCurrentUser(p);
        return result.put("status",200).put("msg","登陆成功");
    }

    /**
     * 登出
     * @param
     * @return
     */
    @Override
    public Result logout() {
        Result result = new Result();
        // 验证通过，将当前用户从session中删除
        userContext.deleteCurrentUser();
        return result.put("status",200).put("msg","登出成功");
    }
    /**
     * 获取当前的用户
     * @return
     */
    @Override
    public Result getCurrentUser(){
        Result result = new Result();
        Person p = userContext.getCurrentUser();
        User user = new User();
        BeanUtils.copyProperties(p, user);
        return result.put("status", 200).put("msg","获取成功").put("data", user);
    }
}
