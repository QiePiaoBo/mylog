package com.mylog.common.licence.model.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户名
     */
    private String userPassword;

    /**
     * 用户手机
     */
    private String userPhone;

    private Integer userGroup;

}
