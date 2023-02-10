package com.hrdkorea.psd;

import java.util.List;

public interface PsdService {
	   void  insert(PsdVO vo);
	   void  delete(PsdVO vo);
	   void  update1(PsdVO vo);
	   void  update2(PsdVO vo);
	   
	   List<PsdVO> list();
	   PsdVO content(PsdVO vo);
	   
}
