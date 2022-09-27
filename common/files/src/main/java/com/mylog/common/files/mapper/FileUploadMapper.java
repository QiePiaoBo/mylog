package com.mylog.common.files.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.common.files.model.FileUploadModel;
import com.mylog.common.files.model.entity.FileUploadEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname FileUploadMapper
 * @Description FileUploadMapper
 * @Date 9/19/2022 6:48 PM
 */
@Mapper
@Component
public interface FileUploadMapper extends BaseMapper<FileUploadEntity> {


    int insertUploads(List<FileUploadModel> fileUploadModels);


}
