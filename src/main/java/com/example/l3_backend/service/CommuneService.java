package com.example.l3_backend.service;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.dto.commune.CreateCommuneDto;
import com.example.l3_backend.dto.commune.DistrictCommuneListDto;
import com.example.l3_backend.dto.commune.UpdateCommuneDto;
import com.example.l3_backend.dto.province.UpdateProvinceAndDistrictDto;
import com.example.l3_backend.model.commune.CommuneModel;
import com.example.l3_backend.model.commune.CreateCommuneModel;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CommuneService {
    void createCommune(CreateCommuneDto createCommuneDto);

    void createCommuneAndDistrictId(CreateCommuneModel createCommuneModel);

    void createDistrictAndListCommune(DistrictCommuneListDto dto);
    List<CommuneDto> getCommune();

    List<CommuneModel> getCommuneFollowDistrictId(Long district_id);

    void deleteCommune(Long id);

    void updateCommune(Long id, UpdateCommuneDto updateCommuneDto);

    void updateCommuneAndDistrictId(Long id, CommuneModel communeModel);

    void updateDistrictAndListCommune(Long id, DistrictCommuneListDto dto);
}
