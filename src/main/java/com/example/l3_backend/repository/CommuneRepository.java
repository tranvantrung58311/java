package com.example.l3_backend.repository;

import com.example.l3_backend.entity.Commune;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    @Modifying
    @Query(value = "Select * from commune where district_id = :district_id", nativeQuery = true)
    List<Commune> findByIdProvince(@Param("district_id") long district_id);

    Commune findByName(String name);
}
