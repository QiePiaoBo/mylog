package com.mylog.tools.model.model.page;


import java.util.Objects;

/**
 * @author Dylan
 * @Date : Created in 14:35 2021/3/9
 * @Description : 分页类，根据传入参数计算开始条数 提供各种参数
 * @Function :
 */
public class MyPage {
    private Integer pageNo;

    private Integer pageSize;

    private Integer startNo;

    public MyPage(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo > 0 ? pageNo : 1;
        this.pageSize = pageSize > 0 ? pageSize : 3;
        this.startNo = (getPageNo() - 1) * getPageSize();
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    /**
     * myPage对象完整
     * @return
     */
    public boolean isValid(){
        return Objects.nonNull(startNo) && Objects.nonNull(pageNo) && Objects.nonNull(pageSize);
    }
}