package com.toratax.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RemoteFileToLocalFile {

    public static boolean multipartFileToFile(MultipartFile file, String filePath) throws IOException {
        File convFile = new File(filePath);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return true;
    }
    public static boolean createDir(String path, String directory){
        File theDir = new File(path+File.separator+directory);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        return true;
    }
}
