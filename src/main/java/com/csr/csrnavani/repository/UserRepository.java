package com.csr.csrnavani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csr.csrnavani.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
