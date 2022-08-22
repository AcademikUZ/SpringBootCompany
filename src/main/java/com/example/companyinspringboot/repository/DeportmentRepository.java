package com.example.companyinspringboot.repository;

import com.example.companyinspringboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeportmentRepository extends JpaRepository<Department, Long> {
}
