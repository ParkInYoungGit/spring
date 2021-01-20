package com.springlec.base0502;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.MidiDevice.Info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("index")
	public String goIndex() {
		return "index";
	}
	
	
	@RequestMapping("student") 
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMethod.GET");
		
		String name = httpServletRequest.getParameter("name");
		String age = httpServletRequest.getParameter("age");
		String grade = httpServletRequest.getParameter("grade");
		String sclass = httpServletRequest.getParameter("sclass");
	
		
		model.addAttribute("studentName", name);
		model.addAttribute("studentAge", age);
		model.addAttribute("studentGrade", grade);
		model.addAttribute("studentSclass", sclass);
		return "info/info";
	}
}
