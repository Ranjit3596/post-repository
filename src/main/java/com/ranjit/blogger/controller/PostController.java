package com.ranjit.blogger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranjit.blogger.AppConstants;
import com.ranjit.blogger.dto.ApiResponse;
import com.ranjit.blogger.dto.PostResponse;
import com.ranjit.blogger.dto.Post;
import com.ranjit.blogger.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	 
	@Autowired
	PostService postService;
	
	@GetMapping("/getPostById/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable("id")String id) {
		PostResponse getPostById = postService.getPostById(Integer.parseInt(id));
		return new ResponseEntity<PostResponse>(getPostById,HttpStatus.OK);
		
	}
	@GetMapping("/getAllPost")
	public ResponseEntity<List<Post>> getAllUser(
			@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
			) {
		List<Post>lsitOfUsers=postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<List<Post>>(lsitOfUsers,HttpStatus.OK);
	}
	
	@PostMapping("/savePost")
	public ResponseEntity<Post> savePost (@RequestBody Post posts){
		posts = postService.savePost(posts);
		return new ResponseEntity<Post>(posts,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletePostById/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") String id) {
		postService.deletePostById(Integer.parseInt(id));
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully : "+id, true),HttpStatus.OK);
	}
	
	@PutMapping("/updatePostById")
	public ResponseEntity<Post> updateUserById(@RequestBody Post posts) {
		posts = postService.updatePost(posts, posts.getPostId());
		return new ResponseEntity<Post>(posts,HttpStatus.OK);
	}
}
