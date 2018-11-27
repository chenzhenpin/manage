package org.fkit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.fkit.flieManage.Download;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class FileDownloadController {
	@RequestMapping(value="load")
	public ResponseEntity<byte[]>  load(HttpServletRequest request,
			@RequestParam("webPath") String webPath
			) throws IOException{
		System.out.println(webPath);
		Download load=new Download(webPath);
		return load.toLoad();	
	}
}
