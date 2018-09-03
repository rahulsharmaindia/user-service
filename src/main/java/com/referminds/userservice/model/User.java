package com.referminds.userservice.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id 
    private String id;
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;
    private String city;
    private String country;
    private String email;
    private String expArea;
    private String gender;
    @Size(max=10, min=10, message="Mobile Number should be exactly 10 digits")
    private String mobile;
    private String totalExp;
    private String agreement;
    private String dob;
    
	public User(String id, String name, String city, String country, String email, String expArea, String gender, String mobile, String totalExp, String agreement, String dob) {
		super(); 
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.email = email;
        this.expArea = expArea;
        this.gender = gender;
        this.mobile = mobile;
        this.totalExp = totalExp;
        this.agreement = agreement;
        this.dob = dob;
    }
	
}
