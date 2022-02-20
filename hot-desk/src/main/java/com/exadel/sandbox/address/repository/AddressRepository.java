package com.exadel.sandbox.address.repository;

import com.exadel.sandbox.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
