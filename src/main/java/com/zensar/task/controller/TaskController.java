package com.zensar.task.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.task.entities.Task;

@RestController
@RequestMapping(value="/api/task")
public class TaskController {
	
	@Autowired
	   RestTemplate restTemplate;
	
		@GetMapping(value="/users")
		public List<Object> getUsers(){
			String url = "http://jsonplaceholder.typicode.com/posts";
			Object[] objects = restTemplate.getForObject(url, Object [].class);
			
			return Arrays.asList(objects);
		}

	   @GetMapping(value = "/template/users")
	   public String getUserList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange(
	    		  "http://jsonplaceholder.typicode.com/posts", HttpMethod.GET, entity, String.class).getBody();
	   }
	   
	   @PostMapping(value = "/template/users")
	   public String createUsers(@RequestBody Task task) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Task> entity = new HttpEntity<Task>(task,headers);
	      
	      return restTemplate.exchange(
	    		  "http://jsonplaceholder.typicode.com/posts", HttpMethod.POST, entity, String.class).getBody();
	   }
	   
	  

	      @PutMapping(value = "/template/users/{id}")
	      public String updateProduct(@PathVariable("id") String id, @RequestBody Task task) {
	         HttpHeaders headers = new HttpHeaders();
	         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	         HttpEntity<Task> entity = new HttpEntity<Task>(task,headers);
	         
	         return restTemplate.exchange(
	        		 "http://jsonplaceholder.typicode.com/posts"+id, HttpMethod.PUT, entity, String.class).getBody();
	      }
	  
	

	      @DeleteMapping(value = "/template/users/{id}")
	      public String deleteProduct(@PathVariable("id") String id) {
	         HttpHeaders headers = new HttpHeaders();
	         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	         HttpEntity<Task> entity = new HttpEntity<Task>(headers);
	         
	         return restTemplate.exchange(
	        		 "http://jsonplaceholder.typicode.com/posts"+id, HttpMethod.DELETE, entity, String.class).getBody();
	      }
	   
}