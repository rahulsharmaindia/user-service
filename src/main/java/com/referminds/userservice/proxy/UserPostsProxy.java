package com.referminds.userservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.referminds.userservice.model.JobPost;

	//@FeignClient(name="user-post-service", url="localhost:8200")
	//@FeignClient(name="user-post-service")
	@FeignClient(name="referminds-netflix-zuul-api-gateway-server")
	@RibbonClient(name="user-post-service")
	public interface UserPostsProxy {
		//@GetMapping("/users/{userId}/job-posts")
		@GetMapping("user-post-service/users/{userId}/job-posts")
		public Iterable<JobPost> getUserPosts(@PathVariable("userId") String userId);
}
