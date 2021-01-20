package com.springlec.base0703.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlec.base0703.command.BCommand;
import com.springlec.base0703.command.BContentCommand;
import com.springlec.base0703.command.BDeleteCommand;
import com.springlec.base0703.command.BListCommand;
import com.springlec.base0703.command.BModifyCommand;
import com.springlec.base0703.command.BWriteCommand;
import com.springlec.base0703.util.Constant;

@Controller
public class BController {

	BCommand command = null;
	
	BCommand listcommand = null;
	BCommand writecommand = null;
	BCommand contentcommand = null;
	BCommand modifycommand = null;
	BCommand deletecommand = null;
	
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	@Autowired
	public void auto(BCommand list, BCommand write, BCommand content, BCommand modify, BCommand delete) {
		this.listcommand = list;
		this.writecommand = write;
		this.contentcommand = content;
		this.modifycommand = modify;
		this.deletecommand = delete;
	}
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
//		command = new BListCommand();
//		command.execute(model);
		listcommand.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
//		command = new BWriteCommand();
//		command.execute(model);
		writecommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
//		command = new BContentCommand();
//		command.execute(model);
		contentcommand.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST )
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify()");
		
		model.addAttribute("request", request);
//		command = new BModifyCommand();
//		command.execute(model);
		modifycommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
//		command = new BDeleteCommand();
//		command.execute(model);
		deletecommand.execute(model);
		
		return "redirect:list";
	}
	
	
}
