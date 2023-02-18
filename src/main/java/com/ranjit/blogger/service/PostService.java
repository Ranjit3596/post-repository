package com.ranjit.blogger.service;

import java.util.List;

import com.ranjit.blogger.dto.Post;
import com.ranjit.blogger.dto.PostResponse;


public interface PostService {

	public Post savePost(Post user);
	public Post createUser(Post user);
	public List<Post> getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	public Post updatePost(Post user,Integer postId);
	public PostResponse getPostById(Integer postId);
	public PostResponse getPostByCategoryId(Integer categoryId);
	public PostResponse getPostByUserId(Integer userId);
	public void deletePostById(Integer userId);
	public List<Post> searchPosts(String keyword);
	
	
}
