package com.mn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChatAppController {

	@GetMapping("/chatScreen")
	public ModelAndView chatScreen() {
		return new ModelAndView("chatScreen");
	}

}
