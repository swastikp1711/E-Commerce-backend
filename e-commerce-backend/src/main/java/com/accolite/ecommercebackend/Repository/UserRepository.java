package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByEmail(String email);

	User findByEmailAndDeletedDateIsNull(String email);
	@Transactional
	@Modifying
	@Query("Update User u set u.password=:passwordEncoded, u.updatedDate=:updatedDate where u.email=:email and u.deletedDate is null")
	void updatePassword(String email, String passwordEncoded, LocalDateTime updatedDate);
}
