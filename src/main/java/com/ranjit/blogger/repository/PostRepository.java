package com.ranjit.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjit.blogger.dto.Category;
import com.ranjit.blogger.dto.Post;
import com.ranjit.blogger.dto.Users;
import com.ranjit.blogger.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
	//List<Post> findByUser(Users user);
	//List<Post> findByCategory(Category category);
	//List<Post> findByTitleContaining(String title);

}
