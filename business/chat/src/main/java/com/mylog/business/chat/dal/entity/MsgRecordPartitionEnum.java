package com.mylog.business.chat.dal.entity;

/**
 * @Classname PartitionEnum
 * @Description PartitionEnum
 * @Date 6/20/2023 6:26 PM
 */
public enum MsgRecordPartitionEnum {

    P202307(202308, "P202307"),
    P202312(202401, "P202312"),
    P202406(202407, "P202406"),
    P202412(Integer.MAX_VALUE, "P202412")
    ;
    private Integer month;

    private String partitionName;

    MsgRecordPartitionEnum(Integer month, String partitionName) {
        this.month = month;
        this.partitionName = partitionName;
    }
}
