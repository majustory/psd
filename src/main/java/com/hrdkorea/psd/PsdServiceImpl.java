package com.hrdkorea.psd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsdServiceImpl  implements   PsdService {

	@Autowired
	PsdDao  dao;
	

	public void insert(PsdVO vo) {
		dao.insert(vo);
		
	}


	public List<PsdVO> list() {
		return dao.list();
		
	}


	@Override
	public PsdVO content(PsdVO vo) {
		// TODO Auto-generated method stub
		return dao.content(vo);
	}


	@Override
	public void delete(PsdVO vo) {
		dao.delete(vo);
		
	}


	@Override
	public void update1(PsdVO vo) {
		dao.update1(vo);		
	}
	
	@Override
	public void update2(PsdVO vo) {
		dao.update2(vo);		
	}

}
