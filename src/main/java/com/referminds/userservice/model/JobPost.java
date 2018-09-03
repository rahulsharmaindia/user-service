package com.referminds.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "JobPosts")
public class JobPost {
	@Id
	String id;
	String subject;
	String profile;
	String companyName;
	String location;
	String ctc;
	String experience;
	String email;
	String description;
	String userId;

	public JobPost(String id, String subject, String profile, String companyName, String location, String ctc,
			String experience, String email, String description) {
		super();
		this.id = id;
		this.subject = subject;
		this.profile = profile;
		this.companyName = companyName;
		this.location = location;
		this.ctc = ctc;
		this.experience = experience;
		this.email = email;
		this.description = description;
	}

}
