package com.hrdkorea.psd;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PsdDao {
	   void  insert(PsdVO vo);
	   void  delete(PsdVO vo);
	   void  update1(PsdVO vo);
	   void  update2(PsdVO vo);
	   
	   List<PsdVO> list();
	   PsdVO content(PsdVO vo);
}
