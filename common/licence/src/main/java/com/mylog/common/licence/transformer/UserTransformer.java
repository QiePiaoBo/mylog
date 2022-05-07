package com.mylog.common.licence.transformer;

import com.mylog.common.licence.entity.User;
import com.mylog.common.licence.model.dto.UserDTO;
import com.mylog.common.licence.model.vo.UserVO;
import com.mylog.tools.model.model.entity.Person;
import com.mylog.tools.model.model.vo.PersonVo;
import com.mylog.tools.utils.utils.Safes;

/**
 * @Classname UserTransformer
 * @Description User实体类转换器
 * @Date 5/6/2022 5:54 PM
 */
public class UserTransformer {

    /**
     * user -> userVo
     * @param user
     * @return
     */
    public static UserVO user2UserVo(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserGroup(user.getUserGroup());
        userVO.setUserName(Safes.of(user.getUserName()));
        userVO.setUserPhone(Safes.of(user.getUserPhone()));
        return userVO;
    }

    /**
     * user -> person
     * @param user
     * @return
     */
    public static Person user2Person(User user){
        Person person = new Person();
        person.setId(user.getId());
        person.setUserGroup(user.getUserGroup());
        person.setUserPassword(Safes.of(user.getUserPassword()));
        person.setUserName(Safes.of(user.getUserName()));
        person.setUserPhone(Safes.of(user.getUserPhone()));
        return person;
    }

    /**
     * userDTO -> user
     * @param userDTO
     * @return
     */
    public static User userDTO2User(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserPhone(userDTO.getUserPhone());
        user.setUserGroup(userDTO.getUserGroup());
        user.setId(userDTO.getId());
        return user;
    }

    /**
     * userDTO -> userVO
     * @param userDTO
     * @return
     */
    public static UserVO userDTO2UserVO(UserDTO userDTO){
        UserVO userVO = new UserVO();
        userVO.setId(userDTO.getId());
        userVO.setUserGroup(userDTO.getUserGroup());
        userVO.setUserName(Safes.of(userDTO.getUserName()));
        userVO.setUserPhone(Safes.of(userDTO.getUserPhone()));
        return userVO;
    }

    /**
     * person -> personVO
     * @param person
     * @return
     */
    public static PersonVo person2PersonVO(Person person){
        PersonVo personVo = new PersonVo();
        personVo.setId(person.getId());
        personVo.setUserGroup(person.getUserGroup());
        personVo.setUserName(Safes.of(person.getUserName()));
        personVo.setUserPhone(Safes.of(person.getUserPhone()));
        return personVo;
    }

    /**
     * PersonVo -> UserVo
     * @param personVo
     * @return
     */
    public static UserVO personVO2UserVO(PersonVo personVo){
        UserVO userVO = new UserVO();
        userVO.setId(personVo.getId());
        userVO.setUserGroup(personVo.getUserGroup());
        userVO.setUserPhone(Safes.of(personVo.getUserPhone()));
        userVO.setUserName(Safes.of(personVo.getUserName()));
        return userVO;
    }

}