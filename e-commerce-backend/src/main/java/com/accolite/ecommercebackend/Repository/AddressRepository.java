package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Address;
import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.dto.Response.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {



    List<Address> findByUserAndDeletedDateIsNull(User user);
}
