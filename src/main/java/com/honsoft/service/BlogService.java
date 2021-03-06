package com.honsoft.service;

import java.util.List;

import com.honsoft.entity.Blog;
import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;
import com.honsoft.exception.MyException;

public interface BlogService {
    Blog saveBlog(Blog blog) throws BlogAlreadyExistsException;
    List getAllBlogs() throws BlogNotFoundException;
    Blog getBlogById(int id) throws BlogNotFoundException;
    Blog getException() throws MyException;
}