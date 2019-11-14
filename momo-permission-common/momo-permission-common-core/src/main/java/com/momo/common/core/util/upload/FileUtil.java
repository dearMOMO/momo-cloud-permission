package com.momo.common.core.util.upload;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by MOMO on 2019/4/26.
 */
public class FileUtil {
    /**
     * 上传文件
     * @param file  文件对应的byte数组流   使用file.getBytes()方法可以获取
     * @param filePath  上传文件路径，不包含文件名
     * @param fileName 上传文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+File.separator+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
