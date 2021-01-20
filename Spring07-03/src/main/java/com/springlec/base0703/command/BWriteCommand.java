package com.springlec.base0703.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.base0703.dao.BDao;

public class BWriteCommand implements BCommand {

//	private BDao dao = null;
//	
//	@Autowired
//	public void setDao(BDao dao) {
//		this.dao = dao;
//	}
	
	@Override
	public void execute(Model model) {
		
		
		

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bName = request.getParameter("bName");
		String bTel = request.getParameter("bTel");
		String bAddress = request.getParameter("bAddress");
		String bEmail = request.getParameter("bEmail");
		String bRelation = request.getParameter("bRelation");
		
		BDao dao = new BDao();
		dao.write(bName, bTel, bAddress, bEmail, bRelation);
		
		
	}

}
