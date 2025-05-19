package com.ferrara.tool.file;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.file.upload.pictures-output-path}")
    private String fileUploadPath;

    public String saveFile(@NotNull MultipartFile sourceFile,  @NotNull String userId, String toolName) {
        final String fileUploadSubPath = "assets" + File.separator + "images";
        return uploadFile(sourceFile, fileUploadSubPath,toolName);
    }

    private String uploadFile(@NotNull MultipartFile sourceFile, @NotNull String fileUploadSubPath, String toolName) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("FAILED TO CREATE THE TARGET FOLDER");
                return null;
            }
        }

        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + toolName.replace(" ", "") + "." +fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try{
            Files.write(targetPath, sourceFile.getBytes());
            log.info("FILE SAVED TO THE TARGET LOCATION: "+targetFilePath);
        }catch (IOException e){
            log.error("FILE WAS NOT SAVED", e);
        }
        return targetFilePath;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) return "";
        int lastDotIndex = fileName.lastIndexOf(".");
        if(lastDotIndex == -1) return "";

        return fileName.substring(lastDotIndex+1).toLowerCase();
    }
}
