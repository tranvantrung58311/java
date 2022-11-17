package com.example.l3_backend.service.impl;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.dto.commune.DistrictCommuneListDto;
import com.example.l3_backend.entity.Commune;
import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.model.commune.CommuneModel;
import com.example.l3_backend.model.commune.CreateCommuneModel;
import com.example.l3_backend.repository.CommuneRepository;
import com.example.l3_backend.dto.commune.CreateCommuneDto;
import com.example.l3_backend.dto.commune.UpdateCommuneDto;
import com.example.l3_backend.repository.DistrictRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import com.example.l3_backend.service.CommuneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommuneServiceImpl implements CommuneService {
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;

    @Override
    public void createCommune(CreateCommuneDto createCommuneDto) {
        Commune commune = mapper.map(createCommuneDto, Commune.class);
        communeRepository.save(commune);
    }

    @Override
    public void createCommuneAndDistrictId(CreateCommuneModel createCommuneModel) {
        Long id = createCommuneModel.getDistrict_id();
        District district = districtRepository.findById(id).orElseThrow(null);
        Commune commune = mapper.map(createCommuneModel, Commune.class);
        commune.setDistrict(district);
        communeRepository.save(commune);
    }

    @Override
    public void createDistrictAndListCommune(DistrictCommuneListDto dto) {
        District district = mapper.map(dto, District.class);
        Province province = provinceRepository.findById(dto.getProvince_id()).orElseThrow(null);
        district.setProvince(province);
        districtRepository.save(district);
        List<CommuneModel> communeModel = dto.getCommuneModelList();
        communeModel.forEach(x -> {
            Commune commune = mapper.map(x, Commune.class);
            District districtOne = districtRepository.findById(x.getDistrict_id()).orElseThrow(null);
            commune.setDistrict(districtOne);
            communeRepository.save(commune);
        });
    }


    @Override
    public List<CommuneDto> getCommune() {
        List<Commune> communeList = communeRepository.findAll();
        return listMapper.mapList(communeList, CommuneDto.class);
    }

    @Override
    public List<CommuneModel> getCommuneFollowDistrictId(Long district_id) {
        List<CommuneModel> communeModelList = new ArrayList<>();
        List<Commune> communeList = communeRepository.findByIdProvince(district_id);
        communeList.forEach(commune -> {
            CommuneModel communeModel = mapper.map(commune, CommuneModel.class);
            communeModel.setDistrict_id(commune.getDistrict().getId());
            communeModelList.add(communeModel);
        });
        return communeModelList;
    }

    @Override
    public void deleteCommune(Long id) {
        communeRepository.deleteById(id);
    }

    @Override
    public void updateCommune(Long id, UpdateCommuneDto updateCommuneDto) {
        Commune commune = communeRepository.findById(id).orElseThrow(null);
        commune.setName(updateCommuneDto.getName());
        commune.setCode(updateCommuneDto.getCode());
        communeRepository.save(commune);
    }

    @Override
    public void updateCommuneAndDistrictId(Long id, CommuneModel communeModel) {
        Commune commune = communeRepository.findById(id).orElseThrow(null);
        commune.setName(communeModel.getName());
        commune.setCode(communeModel.getCode());
        District district = districtRepository.findById(communeModel.getDistrict_id()).get();
        commune.setDistrict(district);
        communeRepository.save(commune);
    }

    @Override
    public void updateDistrictAndListCommune(Long id, DistrictCommuneListDto dto) {
        District district = districtRepository.findById(id).orElseThrow(null);
        Province province = provinceRepository.findById(dto.getProvince_id()).orElseThrow(null);
        district.setName(dto.getName());
        district.setCode(dto.getCode());
        district.setProvince(province);
        districtRepository.save(district);
        List<CommuneModel> communeModel = dto.getCommuneModelList();
        communeModel.forEach(x -> {
            Commune commune = communeRepository.findById(x.getId()).orElseThrow(null);
            commune.setName(dto.getName());
            commune.setCode(dto.getCode());
            District districtOne = districtRepository.findById(x.getDistrict_id()).orElseThrow(null);
            commune.setDistrict(districtOne);
            communeRepository.save(commune);
        });
    }
}
