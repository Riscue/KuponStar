package com.riscue.kuponstar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.riscue.kuponstar.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	// @Query(value = "SELECT * FROM `USERS` WHERE `adSoyad` = :adSoyad")
	List<User> findUserById(int id);
	List<User> findUserByMail(String mail);
	List<User> findUserByUsername(String username);
}
