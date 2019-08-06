package com.hlsofficesystem.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private final String PREFIX = "pages/";
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "welcome";
	}
	
	/**
	 * 登陆页
	 * @return
	 */
	@RequestMapping("/userlogin")
	public String loginPage() {
		return PREFIX+"login";
	}
	
	/**
	 * level1页面映射
	 * @param path
	 * @return
	 */
	@RequestMapping("/level1/{path}")
	public String level1(@PathVariable("path")String path) {
		return PREFIX+"level1/"+path;
	}
	
	/**
	 * level2页面映射
	 * @param path
	 * @return
	 */
	@RequestMapping("/level2/{path}")
	public String level2(@PathVariable("path")String path) {
		return PREFIX+"level2/"+path;
	}
	
	/**
	 * level3页面映射
	 * @param path
	 * @return
	 */
	@RequestMapping("/level3/{path}")
	public String level3(@PathVariable("path")String path) {
		return PREFIX+"level3/"+path;
	}

	
}
