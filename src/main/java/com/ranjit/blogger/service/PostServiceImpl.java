package com.ranjit.blogger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ranjit.blogger.Exception.ResourceNotFoundException;
import com.ranjit.blogger.dto.Category;
import com.ranjit.blogger.dto.PostResponse;
import com.ranjit.blogger.dto.Post;
import com.ranjit.blogger.dto.Users;
import com.ranjit.blogger.entity.PostEntity;

import com.ranjit.blogger.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepo;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	@Override
	public Post savePost(Post user) {

		PostEntity postEntity = this.mapper.map(user, PostEntity.class);
		PostEntity savedPost = postRepo.save(postEntity);
		Post posts = this.mapper.map(savedPost, Post.class);
		return posts;
	}

	@Override
	public Post createUser(Post user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post updatePost(Post user, Integer postId) {
		PostEntity userEntity = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		userEntity = this.mapper.map(user,PostEntity.class);
		user = this.mapper.map(userEntity,Post.class);
		return user;
	}

	@Override
	public PostResponse getPostById(Integer postId) {
		PostResponse postResponse = new PostResponse();
		Users usr = null;
		Category category = null;
		PostEntity userEntity = null;
		userEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		usr = restTemplate.getForObject("http://user-service/users/getUserById/" + userEntity.getUserId(),Users.class);
		category = restTemplate.getForObject(
				"http://category-service/category/getCategoryById/" + userEntity.getCategoryId(), Category.class);
		postResponse.setPostId(userEntity.getPostId());
		postResponse.setCategory(category);
		postResponse.setUsers(usr);
		return postResponse;
	}

	@Override
	public List<Post> getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		List<PostEntity> list = postRepo.findAll();
		List<Post> listOfUser = list.stream().map(userEntity -> {
			Post post = this.mapper.map(userEntity, Post.class);
			return post;
		}).collect(Collectors.toList());
		return listOfUser;
	}

	@Override
	public void deletePostById(Integer userId) {
		 postRepo.deleteById(userId);
	}

	@Override
	public PostResponse getPostByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostResponse getPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
