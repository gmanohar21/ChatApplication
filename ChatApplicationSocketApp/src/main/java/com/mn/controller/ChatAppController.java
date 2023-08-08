package com.mn.controller;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mn.model.Member;
import com.mn.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class ChatAppController {
	@Autowired
	MemberRepository memberRepository;

	@PostMapping("/chatScreen")
	public void chatScreen(
			@RequestBody HashMap<String, Object> objRequest) throws ParseException {
		boolean parseBoolean = Boolean.parseBoolean(objRequest.get("online").toString());
		Member m=new Member();
		m.setName(objRequest.get("name").toString());
		m.setOnline(parseBoolean);
		memberRepository.save(m);
	}
	
	@GetMapping("/chatScreenDisplay")
	public ModelAndView chatScreenreturn() {
		return new ModelAndView("chatScreen");
	}
	
	
	
	@GetMapping("/chatNew")
	public ModelAndView chatNew() {
		return new ModelAndView("chatfile");
	}

}
