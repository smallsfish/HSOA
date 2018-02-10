package com.hassdata.hserp.util;

public class FileUploadType {

    private String imgType;
    private String compressionType;
    private String excleType;
    private String docType;

    public static String getImgType() {
        return ".jpg|.jpeg|.gif|.bmp|.png|";
    }

    public static String getCompressionType() {
        return ".zip|.rar|";
    }

    public static String getExcleType() {
        return ".xls|.xlsx|";
    }

    public static String getDocType() {
        return ".doc|.docx|.word|.wps|";
    }
}
