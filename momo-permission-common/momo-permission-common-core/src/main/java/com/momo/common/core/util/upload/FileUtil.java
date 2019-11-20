/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
