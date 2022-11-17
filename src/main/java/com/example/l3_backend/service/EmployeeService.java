package com.example.l3_backend.service;

import com.example.l3_backend.dto.employee.EmployeeDto;
import com.example.l3_backend.dto.employee.ValidateInputEmployee;
import com.example.l3_backend.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    void createEmployee(ValidateInputEmployee inputEmployee);

    List<EmployeeDto> getEmployee();

    void deleteEmployee(Long id);

    void deleteAllEmployee();

    void updateEmployee(Long id, ValidateInputEmployee inputEmployee);

    void importEmployee(MultipartFile file) throws IOException;
}
