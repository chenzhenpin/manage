package org.fkit.flieManage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	private String savepath;
	private MultipartFile file;
	private FileInfo info;
	
	
	public UploadFile(MultipartFile file,String savepath) {
		super();
		this.file=file;
		this.savepath=savepath;
	}
	public UploadFile(MultipartFile file,String savepath,FileInfo info){
		super();
		this.file=file;
		this.savepath=savepath;
		this.info=info;
	}

	public FileInfo saveFile(){
		
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request上下文");
           
        }
        String rootPath=attrs.getRequest().getSession().getServletContext().getRealPath("/WEB-INF/upload/");
        System.out.print(rootPath);
		String oriName=file.getOriginalFilename();
	
		String ext=oriName.substring(oriName.lastIndexOf(".")+1);
		String name=oriName.substring(0,oriName.lastIndexOf(".")-1);
		String size=Long.toString(file.getSize());
		
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(this.info == null){
			this.info=new FileInfo(); 
		}
 		if(this.info.getSaveName()==null){
 			this.info.setSaveName(oriName);
 		}else{
 			this.info.setSaveName(this.info.getSaveName()+"."+ext);
 		}
 		try{
	 		File filepath=new File(rootPath+File.separator+this.savepath,this.info.getSaveName());
	 		if(!filepath.getParentFile().exists()){
		 		filepath.getParentFile().mkdirs();	
		 	}
	 		
	 		System.out.println(filepath);
	 		file.transferTo(filepath);
	 		this.info.setOrigName(oriName);
	 		this.info.setSize(size);
	 		this.info.setSaveName(this.info.getSaveName());
	 		this.info.setUploaDateTime(dateFormat.format(date));
	 		this.info.setRealPath(filepath.getPath());
	 		this.info.setWebPath("/WEB-INF/upload/"+this.savepath+"/"+this.info.getSaveName());
	 		this.info.setExt(ext);
	 	}catch(Exception e){
	 		
	 		System.out.println("文件上传异常");
	 		e.printStackTrace();
	 	}

		return this.info;
	}
	

	
	
}