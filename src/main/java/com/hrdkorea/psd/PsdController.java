package com.hrdkorea.psd;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PsdController {
   
	@Autowired
	PsdServiceImpl Service;
	
	private String  path ="";
	private String  onlyFilename ="";
	private String  extension="";
	
	@GetMapping("/form.do")
	String  form() {		
		return "form.html";
	}
	
	@GetMapping("/list.do")
	String  list(Model model) {			
		model.addAttribute("li", Service.list());
		return "list.html";
	}
	
	@GetMapping("/delete.do")
	String  delete( PsdVO vo , HttpServletRequest request   ) {		
				
		path = request.getSession().getServletContext().getRealPath("/img/");
		vo = Service.content(vo);
		 
		 File f=new File(path+vo.getImg());
		 
         if (vo.getImg() != null ) {
	        if (!vo.getImg().equals("space.png")) {
	        		if(f.exists()) {
	   				 f.delete();
	   			 }
        	}         	 
         } 
         
		Service.delete(vo);
		
		return "redirect:list.do";
	}
	
	
	
	@GetMapping("/content.do")
	String  content(Model model, PsdVO vo) {			
		model.addAttribute("m", Service.content(vo));
		return "content.html";
	}
	
	
	@PostMapping("/formOK.do")
	String  formOK(PsdVO vo,  HttpServletRequest request ) throws Exception, IOException {	
		
		 path = request.getSession().getServletContext().getRealPath("/img/");
		 System.out.println("path : " + path);
		 
			long time = System.currentTimeMillis();
			SimpleDateFormat daytime =new SimpleDateFormat("HHmmss");
			String timeStr=daytime.format(time);
			
		    MultipartFile  updateFile = vo.getUpdateFile();
		    String fileName=updateFile.getOriginalFilename();
		    
			File f = new File(path + fileName);
			if (!updateFile.isEmpty()) { //  파일이 있으면 ...
				if (f.exists()) {
				// 기본 파일명과 중복이 되면 ...	
				 onlyFilename = fileName.substring(0, fileName.lastIndexOf("."));
				 extension=fileName.substring(fileName.lastIndexOf("."));
				 fileName =onlyFilename + "_" + timeStr +	extension;
				 updateFile.transferTo(new File(path+fileName));	
				}else {
				  // 기본 파일과 중복이 되지 않으면 ..	
				  updateFile.transferTo(new File(path+fileName));	
				}				
			}					
			vo.setImg(fileName);	
			if (fileName == null ||fileName.equals("")   ) {
				vo.setImg("space.png");
			}
			Service.insert(vo);		 
		return "redirect:list.do";
	}
	
	
	@PostMapping("/updateOK.do")
	String  updateOK(PsdVO vo,  HttpServletRequest request ) throws Exception, IOException {	
		
		path = request.getSession().getServletContext().getRealPath("/img/");
				 
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime =new SimpleDateFormat("HHmmss");
		String timeStr=daytime.format(time);
		
	    MultipartFile  updateFile = vo.getUpdateFile();
	    String fileName=updateFile.getOriginalFilename();
	   
			if (!updateFile.isEmpty()) { //  파일이 있으면 ...
				
				// 레코드에 파일이 있으면 파일 찾아서 삭제하기 
				PsdVO oldImg = Service.content(vo);			 
				 File delf=new File(path+oldImg.getImg());			 
		         if (oldImg.getImg() != null ) {
			        if (!oldImg.getImg().equals("space.png")) {
			        		if(delf.exists()) {
			        		   delf.delete();
			   			 }
		        	}         
			   
			    File newf=new File(path+fileName);	
				if (newf.exists()) {
				// 기본 파일명과 중복이 되면 ...	
				 onlyFilename = fileName.substring(0, fileName.lastIndexOf("."));
				 extension=fileName.substring(fileName.lastIndexOf("."));
				 fileName =onlyFilename + "_" + timeStr +	extension;
				 updateFile.transferTo(new File(path+fileName));	
				}else {
				  // 기본 파일과 중복이 되지 않으면 ..	
				  updateFile.transferTo(new File(path+fileName));	
				}				
			}					
			vo.setImg(fileName);	         
			Service.update1(vo);
			
	    } else {
	    	Service.update2(vo);
	    }
	    
		
	    return "redirect:list.do";
	}
}
