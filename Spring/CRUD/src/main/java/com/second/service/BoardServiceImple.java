package com.second.service;

import java.util.*;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.second.domain.BoardVO;
import com.second.persistence.BoardDAO;

@Service
public class BoardServiceImple implements BoardService {
	@Inject
  	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}
}