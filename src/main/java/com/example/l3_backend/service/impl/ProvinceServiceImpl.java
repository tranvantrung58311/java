package com.example.l3_backend.service.impl;

import com.example.l3_backend.dto.district.DistrictDto;
import com.example.l3_backend.dto.province.*;
import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.model.district.DistrictModel;
import com.example.l3_backend.model.province.ProvinceModel;
import com.example.l3_backend.repository.DistrictRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import com.example.l3_backend.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ListMapper listMapper;


    @Override
    public void createProvince(CreateProvinceDto createProvinceDto) {
        Province province = new Province();
        province.setName(createProvinceDto.getName());
        province.setCode(createProvinceDto.getCode());
        provinceRepository.save(province);
    }

    @Override
    public List<ProvinceDto> getProvince() {
        List<Province> provinceList = provinceRepository.findAll();
        return listMapper.mapList(provinceList, ProvinceDto.class);
    }

    @Override
    public List<ProvinceModel> getProvinceListDistrict() {
        List<Province> provinceList = provinceRepository.findAll();
        return listMapper.mapList(provinceList,ProvinceModel.class);
    }

    @Override
    public void deleteProvince(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public void deleteProvinceAndDistrict(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public void updateProvince(Long id, UpdateProvinceDto updateProvinceDto) {
        Province province = provinceRepository.findById(id).orElseThrow(null);
        province.setName(updateProvinceDto.getName());
        province.setCode(updateProvinceDto.getCode());
        provinceRepository.save(province);
    }

    @Override
    public void createProvinceAndListDistrict(CreateProvinceAndDistrictDto dto) {
        Province pro = new Province();
        pro.setName(dto.getName());
        pro.setCode(dto.getCode());
        Province provinceOne = provinceRepository.save(pro);
        List<DistrictDto> districtList = dto.getDistricts();
        districtList.forEach(districtDto -> {
            District district = mapper.map(districtDto, District.class);
            district.setProvince(provinceOne);
            districtRepository.save(district);
        });
    }

    @Override
    public void updateProvinceAndListDistrict(Long id, UpdateProvinceAndDistrictDto dto) {
        Province province = provinceRepository.findById(id).orElseThrow(null);
        province.setName(dto.getName());
        province.setCode(dto.getCode());
        provinceRepository.save(province);
        List<DistrictModel> districtList = dto.getDistricts();
        districtList.forEach(x -> {
            District district = districtRepository.findById(x.getId()).orElseThrow(null);
            district.setName(x.getName());
            district.setCode(x.getCode());
            Province pro = provinceRepository.findById(x.getProvince_id()).orElseThrow(null);
            district.setProvince(pro);
            districtRepository.save(district);
        });
    }
}
