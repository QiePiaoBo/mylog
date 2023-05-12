package com.mylog.business.chat.dal;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Classname HBaseUtil
 * @Description HBaseUtil
 * @Date 5/12/2023 3:08 PM
 */
@Component
public class HBaseService implements InitializingBean {

    private final MyLogger logger = MyLoggerFactory.getLogger(HBaseService.class);

    @Value("${lgc.hbase.logicer.name:hbase-lgc}")
    private String logicerName;
    @Value("${lgc.hbase.server:logicer.top}")
    private String server;
    @Value("${lgc.hbase.port:16010}")
    private Integer port;

    private Connection connection;
    private Admin admin;


    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(logicerName, server + port);
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     *
     * @param tableName 表名
     * @param family    列族
     * @throws IOException
     */
    public void createTable(String tableName, String family) throws IOException {
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) {
            logger.info("table exists!");
        } else {
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tn);
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
            ColumnFamilyDescriptor columnFamilyDescriptor = columnFamilyDescriptorBuilder.build();
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
            TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
            admin.createTable(tableDescriptor);
            logger.info("create table success!");
        }
    }

    /**
     * 插入数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param family    列族
     * @param qualifier 列名
     * @param value     值
     * @throws IOException
     */
    public void insert(String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        table.put(put);
        logger.info("insert data success!");
    }

    /**
     * 根据rowKey查询数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param family    列族
     * @param qualifier 列名
     * @return
     * @throws IOException
     */
    public String queryByRowKey(String tableName, String rowKey, String family, String qualifier) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        byte[] value = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        return Bytes.toString(value);
    }

    /**
     * 删除表
     *
     * @param tableName 表名
     * @throws IOException
     */
    public void deleteTable(String tableName) throws IOException {
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) {
            admin.disableTable(tn);
            admin.deleteTable(tn);
            logger.info("delete table success!");
        } else {
            logger.info("table not exists!");
        }
    }
}