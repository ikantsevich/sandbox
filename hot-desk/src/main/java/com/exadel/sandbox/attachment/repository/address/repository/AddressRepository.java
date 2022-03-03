package com.exadel.sandbox.attachment.repository.address.repository;

import com.exadel.sandbox.attachment.repository.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
