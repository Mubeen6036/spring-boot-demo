package com.example.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldContainer {
	@Autowired
	private MessageSource messageSource;
	
	//GET
	//URI
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello-world2")
	public String helloWorld2() {
		return "Hello World 2";
	}
	
	@GetMapping("hello-world-bean")
	public HellWorldBean getHelloWorldBean(@PathVariable String name) {
		return new HellWorldBean(name);
	}
	
	@GetMapping("hello-world-bean/path-variables/{name}")
	public HellWorldBean getHelloWorldBeanPath(@PathVariable String name) {
		return new HellWorldBean(name);
	}
	
	@GetMapping("/hello-world-internatinoalized")
	public String helloWorldInternationlized(
//			@RequestHeader(name="Accept-Language", required = false) Locale locale
			) {
//		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
	}

	@Override
	public String toString() {
		return "HelloWorldContainer []";
	}
}
