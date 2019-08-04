package com.hlsofficesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hlsofficesystem.component.MyLocaleResolver;

@Configuration
public class MvcConfig {

	/**   
	 * @Title: webMvcConfigurerAdapter   
	 * @Description: TODO(指定默认页:将组件注册到容器中)   
	 * @param: @return      
	 * @return: WebMvcConfigurerAdapter      
	 * @throws   
	 */
	/*
	 * @Bean public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
	 * WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
	 * 
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * 
	 * registry.addViewController("/").setViewName("welcome");
	 * registry.addViewController("/index.html").setViewName("welcome");
	 * 
	 * 
	 * registry.addViewController("/login").setViewName("/template/login");
	 * registry.addViewController("/success.html").setViewName(
	 * "/template/loginsuccess");
	 * 
	 * }
	 * 
	 * }; return adapter;
	 * 
	 * }
	 */
	
	/**   
	 * @Title: localeResolver   
	 * @Description: TODO(注册国际化)   
	 * @param: @return      
	 * @return: LocaleResolver      
	 * @throws   
	 */
	@Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }
	
}
