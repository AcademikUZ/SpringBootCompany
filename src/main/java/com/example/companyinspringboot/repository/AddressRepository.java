package com.example.companyinspringboot.repository;

import com.example.companyinspringboot.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
