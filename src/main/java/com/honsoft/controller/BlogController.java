package com.honsoft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honsoft.entity.Blog;
import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;
import com.honsoft.exception.handler.BlogExceptionHandler;
import com.honsoft.service.BlogService;

@RestController
@RequestMapping("/api/v1")
public class BlogController {
	private static Logger logger = LoggerFactory.getLogger(BlogController.class);
    private BlogService blogService;
    
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @PostMapping("/blog")
    public ResponseEntity saveBlog(@RequestBody Blog blog) throws BlogAlreadyExistsException {
        Blog savedBlog = blogService.saveBlog(blog);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }
    @GetMapping("/blogs")
    public ResponseEntity<List> getAllBlogs() throws BlogNotFoundException {
        return new ResponseEntity<List>((List) blogService.getAllBlogs(), HttpStatus.OK);
    }
    @GetMapping("blog/{id}")
    public ResponseEntity getBlogById(@PathVariable("id") int id) throws BlogNotFoundException {
        return new ResponseEntity(blogService.getBlogById(id), HttpStatus.OK);
    }
    
    @ExceptionHandler(value = BlogAlreadyExistsException.class)
    public ResponseEntity handleBlogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException) {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return new ResponseEntity("Blog already exists", HttpStatus.CONFLICT);
    }
}