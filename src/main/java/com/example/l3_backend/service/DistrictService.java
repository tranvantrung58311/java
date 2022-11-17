package com.example.l3_backend.service;

import com.example.l3_backend.dto.district.DistrictDto;
import com.example.l3_backend.dto.district.ProvinceDistrictCommuneDto;
import com.example.l3_backend.model.district.CreateDistrictModel;
import com.example.l3_backend.model.district.DistrictModel;
import com.example.l3_backend.dto.district.CreateDistrictDto;
import com.example.l3_backend.dto.district.UpdateDistrictDto;
import com.example.l3_backend.model.district.ListDistrictModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DistrictService {
    void createDistrict(CreateDistrictDto createDistrictDto);

    void createDistrictAndProvinceId(CreateDistrictModel districtDto);

    void CreateProvinceDistrictCommune(ProvinceDistrictCommuneDto dto);

    List<DistrictDto> getDistrict();

    List<ListDistrictModel> getDistrictListCommune();

    List<DistrictModel> getDistrictFollowProvinceId(Long province_id);

    void deleteDistrict(Long id);

    void deleteDistrictAndCommune(Long id);

    void updateDistrict(Long id, UpdateDistrictDto updateDistrictDto);

    void updateDistrictAndProvinceId(Long id, DistrictModel districtModel);
}
