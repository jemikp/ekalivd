package com.csr.csrnavani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csr.csrnavani.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByIdOrWhatsApp(int id,String whatsApp);
}
