package com.mylog.common.files.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.common.files.model.entity.FileUpload;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname FileUploadMapper
 * @Description FileUploadMapper
 * @Date 9/19/2022 6:48 PM
 */
@Mapper
@Component
public interface FileUploadMapper extends BaseMapper<FileUpload> {
}
