package com.library.server.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.library.server.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByPhone(String phone);

	Page<User> findUserByName(String name, Pageable page);

	Page<User> findAll(Pageable page);

	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.amount = ?1 WHERE u.phone = ?2")
	int updateAmount(Integer amount, String phone);
	
	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = ?1, u.sex = ?2 WHERE u.phone = ?3")
	int updateInfo(String name, Integer sex, String phone);

	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.phone = ?1 WHERE u.phone = ?2")
	int updatePhone(String newPhone, String oldPhone);

	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = ?1 WHERE u.phone = ?2")
	int updatePassword(String password, String phone);
}
