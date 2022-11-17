package com.example.l3_backend.service;

import com.example.l3_backend.dto.province.*;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.model.province.ProvinceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProvinceService {
    void createProvince(CreateProvinceDto createProvinceDto);

    List<ProvinceDto> getProvince();

    List<ProvinceModel> getProvinceListDistrict();

    void deleteProvince(Long id);

    void deleteProvinceAndDistrict(Long id);

    void updateProvince(Long id, UpdateProvinceDto updateProvinceDto);

    void createProvinceAndListDistrict(CreateProvinceAndDistrictDto dto);

    void updateProvinceAndListDistrict(Long id, UpdateProvinceAndDistrictDto dto);
}
