package com.mylog.entitys.entitys.page;


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

    private Long totalSize;

    public MyPage(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.startNo = (pageNo - 1) * pageSize;
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

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
}
