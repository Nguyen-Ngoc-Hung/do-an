package com.doantotnghiep.nvt.common;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

@Component
public class Ulti {

    @Autowired
    Sftp sftpClient;

    public String uploadMultipartFile(MultipartFile file,String maTk, String type){

		ChannelSftp channelSftp = sftpClient.createChannelSftp();

		String folder = "/upload/QLNVT/PHATHANHTHE";


		String path = folder + "/" + "120" + "/" + maTk + "/" + type;

		try {
			if (channelSftp.isConnected()) {

				path = uploadMultipleDir(channelSftp, path);

					try (InputStream is = file.getInputStream()) {
						channelSftp.put(is, path + FilenameUtils.getName(file.getOriginalFilename()));
					} catch (Exception e) {
						path = "";
						System.err.println("Không lưu được file");
					}
			} else {
				System.err.println("Không tạo được folder");
			}
		} catch (SftpException e) {
			System.err.println(e.getMessage());
		} finally {
			sftpClient.disconnectChannelSftp(channelSftp);
		}

		return path;

	}

	// xuat du lieu sinh vien de in
	public String uploadInputStream(InputStream is,String path,String maLo){

		ChannelSftp channelSftp = sftpClient.createChannelSftp();

		try {
			if (channelSftp.isConnected()) {

				path = uploadMultipleDir(channelSftp, path);

						channelSftp.put(is, path + "/du-lieu.xlsx");
			} else {
				System.err.println("Không tạo được folder");
			}
		} catch (SftpException e) {
			System.err.println(e.getMessage());
		} finally {
			sftpClient.disconnectChannelSftp(channelSftp);
		}

		return path;

	}

    public String uploadMultipleDir(ChannelSftp channelSftp, String path) throws SftpException {
		String[] folders = path.split("/");
		if (folders[0].isEmpty())
			folders[0] = "/";
		String fullPath = folders[0];
		for (int i = 1; i < folders.length; i++) {
			Vector ls = channelSftp.ls(fullPath);
			boolean isExist = false;
			for (Object o : ls) {
				if (o instanceof LsEntry) {
					LsEntry e = (LsEntry) o;
					if (e.getAttrs().isDir() && e.getFilename().equals(folders[i])) {
						isExist = true;
					}
				}
			}
			if (!isExist && !folders[i].isEmpty()) {
				channelSftp.mkdir(fullPath + folders[i]);
			}
			fullPath = fullPath + folders[i] + "/";
		}

		return fullPath;
	}

	public String currentDateTime(){
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}
}
