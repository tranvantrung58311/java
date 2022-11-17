package com.example.l3_backend.service.impl;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.dto.district.DistrictDto;
import com.example.l3_backend.dto.district.ProvinceDistrictCommuneDto;
import com.example.l3_backend.entity.Commune;
import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.model.district.CreateDistrictModel;
import com.example.l3_backend.model.district.DistrictModel;
import com.example.l3_backend.model.district.ListDistrictModel;
import com.example.l3_backend.repository.CommuneRepository;
import com.example.l3_backend.repository.DistrictRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import com.example.l3_backend.dto.district.CreateDistrictDto;
import com.example.l3_backend.dto.district.UpdateDistrictDto;
import com.example.l3_backend.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;

    @Override
    public void createDistrict(CreateDistrictDto createDistrictDto) {
        District district = mapper.map(createDistrictDto, District.class);
        districtRepository.save(district);
    }

    @Override
    public void createDistrictAndProvinceId(CreateDistrictModel createDistrictModel) {
        Long id = createDistrictModel.getProvince_id();
        District district = new District();
        Province province = provinceRepository.findById(id).orElseThrow(null);
        district.setName(createDistrictModel.getName());
        district.setCode(createDistrictModel.getCode());
        district.setProvince(province);
        districtRepository.save(district);
    }

    @Override
    public void CreateProvinceDistrictCommune(ProvinceDistrictCommuneDto dto) {
        Province province = mapper.map(dto, Province.class);
        Province provinceOne = provinceRepository.save(province);

        DistrictDto districtDto = dto.getDistrictDto();
        District district = mapper.map(districtDto, District.class);
        district.setProvince(provinceOne);
        District districtOne = districtRepository.save(district);

        CommuneDto communeDto = dto.getCommuneDto();
        Commune commune = mapper.map(communeDto, Commune.class);
        commune.setDistrict(districtOne);
        communeRepository.save(commune);
    }

    @Override
    public List<DistrictDto> getDistrict() {
        List<District> districtList = districtRepository.findAll();
        return listMapper.mapList(districtList, DistrictDto.class);
    }

    @Override
    public List<ListDistrictModel> getDistrictListCommune() {
        List<District> district = districtRepository.findAll();
        return listMapper.mapList(district, ListDistrictModel.class);
    }

    @Override
    public List<DistrictModel> getDistrictFollowProvinceId(Long province_id) {
        List<DistrictModel> districtModels = new ArrayList<>();
        List<District> districts = districtRepository.findByIdProvince(province_id);
        districts.forEach(district -> {
            DistrictModel districtModel = mapper.map(district, DistrictModel.class);
            districtModel.setProvince_id(district.getProvince().getId());
            districtModels.add(districtModel);
        });
        return districtModels;
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public void deleteDistrictAndCommune(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public void updateDistrict(Long id, UpdateDistrictDto updateDistrictDto) {
        District district = districtRepository.findById(id).orElseThrow(null);
        district.setName(updateDistrictDto.getName());
        district.setCode(updateDistrictDto.getCode());
        districtRepository.save(district);
    }

    @Override
    public void updateDistrictAndProvinceId(Long id, DistrictModel districtModel) {
        District district = districtRepository.findById(id).orElseThrow(null);
        district.setName(districtModel.getName());
        district.setCode(districtModel.getCode());
        Province province = provinceRepository.findById(districtModel.getProvince_id()).orElseThrow(null);
        district.setProvince(province);
        districtRepository.save(district);
    }
}
