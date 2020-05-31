package com.mylog.common.format.Adapter;

import com.mylog.common.format.Adapter.demo2.IOuterUserBaseInfo;
import com.mylog.common.format.Adapter.demo2.IOuterUserHomeInfo;
import com.mylog.common.format.Adapter.demo2.IOuterUserOfficeInfo;
import com.mylog.common.format.Adapter.demo2.IUserInfo;
import com.mylog.common.format.Adapter.demo2.impl.OuterUserBaseInfo;
import com.mylog.common.format.Adapter.demo2.impl.OuterUserHomeInfo;
import com.mylog.common.format.Adapter.demo2.impl.OuterUserInfo;
import com.mylog.common.format.Adapter.demo2.impl.OuterUserOfficeInfo;

public class Client {
    //     无外部系统连接
//    public static void main(String[] args) {
//        IUserInfo youngGirl = new UserInfoImpl();
//        // 从数据库中查到了10个
//        for (int i = 0; i < 10; i++) {
//            youngGirl.getUserName();
//            youngGirl.getMobileNumber();
//        }
//    }
    // 与外部系统连接
//    public static void main(String[] args) {
//        IUserInfo youngGirl = new OuterUserInfo();
//        // 查到101个
//        for (int i = 0; i < 101; i++){
//            youngGirl.getUserName();
//            youngGirl.getMobileNumber();
//        }
//    }
    // 定义
//    public static void main(String[] args) {
//        // 原有的业务逻辑
//        Target target = new TargetImpl();
//        target.request();
//        // 增加了适配器角色后的业务逻辑
//        Target target1 = new Adapter();
//        target1.request();
//    }
    // 复杂
    public static void main(String[] args) {
        // 外系统的人员信息
        IOuterUserBaseInfo baseInfo = new OuterUserBaseInfo();
        IOuterUserHomeInfo homeInfo = new OuterUserHomeInfo();
        IOuterUserOfficeInfo officeInfo = new OuterUserOfficeInfo();

        // 传递三个对象
        IUserInfo youngGirl = new OuterUserInfo(baseInfo, homeInfo, officeInfo);

        for (int i = 0; i < 101; i++){
            youngGirl.getMobileNumber();
        }
    }
}
