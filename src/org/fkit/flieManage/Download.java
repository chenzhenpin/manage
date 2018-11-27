package org.fkit.flieManage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Download {
	private String path;
	private String saveName;
	private String loadFileName;
	public Download(String path){
		this.path=path;
	}
	public Download(String path,String loadFileName){
		this.path=path;
		this.loadFileName=loadFileName;
	}
	public ResponseEntity<byte[]> toLoad() throws IOException{
		 ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 String rootPath=attrs.getRequest().getSession().getServletContext().getRealPath("/");
		 File file=new File(rootPath,File.separator+this.path);
		 String fileName;
		 if(this.loadFileName==null){
			fileName=file.getName();
		 }else{
			 String ext=this.path.substring(this.path.lastIndexOf(".")+1);
			fileName=this.loadFileName+"."+ext;
		 }

		 HttpHeaders headers= new HttpHeaders();
		 String downloadFile= new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		 headers.setContentDispositionFormData("attachment", downloadFile);
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
}
