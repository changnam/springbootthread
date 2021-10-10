package com.honsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.honsoft.entity.Blog;
import com.honsoft.exception.BlogAlreadyExistsException;
import com.honsoft.exception.BlogNotFoundException;
import com.honsoft.exception.MyException;
import com.honsoft.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    @Override
    public Blog saveBlog(Blog blog) {
        if (blogRepository.existsById(blog.getBlogId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"duplicate blog", new BlogAlreadyExistsException("blog already exists."));
        }
        Blog savedBlog = blogRepository.save(blog);
        return savedBlog;
    }
    @Override
    public List getAllBlogs() {
        return (List) blogRepository.findAll();
    }
    @Override
    public Blog getBlogById(int id) throws BlogNotFoundException {
        Blog blog;
        //int result = 3 / 0;
        if (blogRepository.findById(id).isEmpty()) {
            throw new BlogNotFoundException(id+" blog not found");
        } else {
            blog = blogRepository.findById(id).get();
        }
        return blog;
    }
	@Override
	public Blog getException() throws MyException {
		Blog blog = new Blog();
		throw new MyException();
	}
}