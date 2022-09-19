package com.mylog.common.files.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname FileUploadDTO
 * @Description FileUploadDTO
 * @Date 9/15/2022 6:06 PM
 */
public class FileUploadDTO {

    private MultipartFile file;

    private String fileName;

    public FileUploadDTO() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
