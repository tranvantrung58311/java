package com.example.l3_backend.repository;

import com.example.l3_backend.entity.District;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    @Modifying
    @Query(value = "Select * from district d where d.province_id = :province_id", nativeQuery = true)
    List<District> findByIdProvince(@Param("province_id") long province_id);

    District findByName(String name);
}
