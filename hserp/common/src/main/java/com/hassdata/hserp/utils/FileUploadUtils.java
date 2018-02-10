package com.hassdata.hserp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class FileUploadUtils {

	public static boolean uploadSingleFile(String savePath,String fileName,InputStream in) {
		FileOutputStream out = null;
		File file= new File(savePath);
		if(!file.exists()){
			file.mkdirs();
		}
		boolean b=false;
		try {
			out=new FileOutputStream(savePath+"/"+fileName);
			byte[] bt=new byte[1024];
			int len=0;
			while((len=in.read(bt))!=-1) {
				out.write(bt,0,len);
			}
			b=true;
		} catch (Exception e) {
			e.printStackTrace();
			b=false;
		}finally {

				try {
					if(in!=null)
					in.close();
					if(out!=null)
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
		return b;
	}
}
