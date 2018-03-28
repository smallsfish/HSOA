package com.hassdata.hserp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

	public static boolean processUpload(HttpServletRequest request, String filePath, Integer maxUpload,
			MultipartFile file, String type) throws InterruptedException, IOException {
		boolean success = true;
		String message = null;

		//判断文件大小
		if(file.getSize() > maxUpload * 1024 * 1024) {
			message = "文件过大，请上传不大于" + maxUpload + "M的文件。";
			request.setAttribute("message", message);
			return false;
		}
		// 获取文件需要上传到的路径
		String path = request.getSession().getServletContext().getRealPath(filePath);// /xx/xx = url
		// System.out.println(path);
		// 如果此文件夹不存在，则构造此文件夹
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}

		// 构造出文件工厂，用于存放JSP页面中传递过来的文件
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置上传文件的保存路径
		factory.setRepository(f);

		// 设置缓存大小，如果文件大于缓存大小时，则先把文件放到缓存中
		factory.setSizeThreshold(1 * 1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置可以上传文件大小的上界20MB
		upload.setSizeMax(maxUpload * 1024 * 1024);
		
		//文件名
		file.getOriginalFilename();
		
		// 索引到最后一个反斜杠
		int start = file.getOriginalFilename().lastIndexOf("\\");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String filename = file.getOriginalFilename().substring(start + 1);

		if (filename != null && !filename.trim().equals("")) {
			// 如果上传的文件不是图片，那么不上传
			String allImgExt = type;
			String extName = filename.substring(filename.lastIndexOf("."), filename.length());
			if (allImgExt.indexOf(extName + "|") == -1) {
				message = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
				success = false;
				request.setAttribute("message", message);
			    return success;
			}
		    
			// 随机数产生名称
			String newName = System.currentTimeMillis() + extName;
			request.setAttribute("newName", newName);
			
			OutputStream out = new FileOutputStream(new File(path, newName));
            
			InputStream in = file.getInputStream();
			int length = 0;
			byte[] buf = new byte[1024];
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
			
			in.close();
			out.close();
		}

		request.setAttribute("message", message);
		return success;
	}
}
