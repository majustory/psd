package com.hrdkorea.psd;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PsdVO {
   private  int  idx;
   private  String  sname;
   private  String  img;
   private  MultipartFile  updateFile; // 실제파일 
   private  Date  today = new Date();   
}
