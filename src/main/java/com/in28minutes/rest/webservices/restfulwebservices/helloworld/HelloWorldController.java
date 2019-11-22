package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	
//	@RequestMapping(method=RequestMethod.GET, path = "/hello-world")
	@GetMapping("/hello-world")
	public List<String> helloWorld() {
		return Arrays.asList("Hello World");
	}
	
	@GetMapping("/hello-world-bean")
	public List<HelloWorldBean> helloWorldBean() {
		return Arrays.asList(new HelloWorldBean("Hello World"));
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public List<HelloWorldBean> helloWorldPathVariable(@PathVariable String name) {
		return Arrays.asList(new HelloWorldBean(String.format("Hello World, %s", name)));
	}
	
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message",null ,LocaleContextHolder.getLocale());
	}
}
