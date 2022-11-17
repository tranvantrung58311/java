package com.example.l3_backend.repository;

import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Province findByName(String name);
}
