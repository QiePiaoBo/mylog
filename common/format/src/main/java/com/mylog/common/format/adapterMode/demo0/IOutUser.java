package com.mylog.common.format.adapterMode.demo0;

import java.util.Map;

/**
 * 外包公司员工信息接口
 */
public interface IOutUser {

    // 基本信息
    public Map getuserBaseInfo();

    // 工作区域信息
    public Map getuserOfficeInfo();

    // 用户的家庭信息
    public Map getUserHomeInfo();

}
