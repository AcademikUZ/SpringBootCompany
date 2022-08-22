package com.example.companyinspringboot.repository;

import com.example.companyinspringboot.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
