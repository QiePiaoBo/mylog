package com.mylog.common.format.Decorator.Demo0;

import com.mylog.common.format.Decorator.SchoolReport;

/**
 * 老爸查看成绩单
 */
public class Father {
    public static void main(String[] args) {
        //把成绩单拿过来
        SchoolReport sr = new FouthGradeSchoolReport();
        //看成绩单
        sr.report();
        //签名？休想！
//        sr.sign("");
    }
}