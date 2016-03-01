package com.ekita.hackaton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "public/template/login", method = RequestMethod.GET)
	public String indexPage() {
		return "login";
	}

	@RequestMapping(value = "public/template/registration", method = RequestMethod.GET)
	public String registratePage() {
		return "registration";
	}

	@RequestMapping(value = "user/template/home", method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

}
