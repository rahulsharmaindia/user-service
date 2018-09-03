package com.referminds.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.referminds.userservice.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	@Override
	Optional<User> findById(String id);

	@Override
	void delete(User deleted);
}
