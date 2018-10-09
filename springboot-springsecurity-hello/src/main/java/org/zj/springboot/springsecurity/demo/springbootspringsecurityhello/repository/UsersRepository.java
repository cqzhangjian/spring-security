package org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByUserName(String userName);
}
