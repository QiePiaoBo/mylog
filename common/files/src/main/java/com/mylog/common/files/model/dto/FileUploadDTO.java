package com.mylog.common.files.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname FileUploadDTO
 * @Description FileUploadDTO
 * @Date 9/15/2022 6:06 PM
 */
public class FileUploadDTO {

    private MultipartFile file;


    public FileUploadDTO() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
