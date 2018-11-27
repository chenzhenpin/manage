package org.fkit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.fkit.flieManage.FileInfo;
import org.fkit.flieManage.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@RequestMapping(value="upload")
	public String upload(HttpServletRequest request,
			@RequestParam(value="description",required=false) String description,
			@RequestParam("file") MultipartFile file
			) throws IOException{
			System.out.print(description);
			/*if(request.getMethod()=="GET"){
				return "uploadForm";
			}*/
		 	if(!file.isEmpty()){
		 		
		 		FileInfo info= new FileInfo();
		 		info.setSaveName("ff");
		 		UploadFile upload=new UploadFile(file,"img",info);
		 		FileInfo info1=(FileInfo)upload.saveFile();
		 		return "success";
		 	}else{

		 		return "uploadForm";
		 	}
		 	
			
	}
}
