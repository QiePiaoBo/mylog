package com.mylog.common.format.Decorator.Demo2;

import com.mylog.common.format.Decorator.FouthGradeSchoolReport;
import com.mylog.common.format.Decorator.SchoolReport;

/**
 * 找家长签字
 */
public class Father {
    public static void main(String[] args) {
        //把成绩单拿过来
        SchoolReport sr;
        //原装的成绩单
        sr = new FouthGradeSchoolReport();
        //加了最高分说明的成绩单
        sr = new HighScoreDecorator(sr);
        //又加了成绩排名的说明
        sr = new SortDecorator(sr);
        //看成绩单
        sr.report();
        //然后老爸一看，很开心，就签名了
        sr.sign("老张");  //张三老爸老张签名
    }
}