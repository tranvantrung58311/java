package com.example.l3_backend.repository;

import com.example.l3_backend.dto.certificateemployee.IdCertificateEmployeeDto;
import com.example.l3_backend.entity.CertificateEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateEmployeeRepository extends JpaRepository<CertificateEmployee, Long> {
    @Modifying
    @Query(value = "delete from certificate_employee where employee_id = :id", nativeQuery = true)
    List<CertificateEmployee> deleteByEmployeeId(Long id);

    List<CertificateEmployee> findByEmployeeId(Long id);
}
