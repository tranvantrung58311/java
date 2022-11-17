package com.example.l3_backend.repository;

import com.example.l3_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from employee",nativeQuery = true)
    List<Employee> findByCode();
}
