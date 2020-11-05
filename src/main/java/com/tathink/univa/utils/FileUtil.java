package com.tathink.univa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void FileWrite(MultipartFile file, String savePath) {
		try {
			InputStream fileStream = file.getInputStream();
			//Path mPath = Paths.get(savePath+file.getOriginalFilename());
			String originExt = StringUtil.getExtension(file.getOriginalFilename()).orElse(".jpg");
			Path mPath = Paths.get(savePath+"img"+originExt);
			Files.copy(fileStream, mPath);
		} catch (IOException e) {
			throw new RuntimeException("Could not store the file. Err: "+e.getMessage());
		}
	}
}
