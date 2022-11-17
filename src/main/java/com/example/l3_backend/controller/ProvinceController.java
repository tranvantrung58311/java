package com.example.l3_backend.controller;

import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.province.CreateProvinceAndDistrictDto;
import com.example.l3_backend.dto.province.CreateProvinceDto;
import com.example.l3_backend.dto.province.UpdateProvinceAndDistrictDto;
import com.example.l3_backend.dto.province.UpdateProvinceDto;
import com.example.l3_backend.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<Object> createProvince(@RequestBody CreateProvinceDto createProvinceDto) {
        provinceService.createProvince(createProvinceDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreateProvinceAndDistrictDto dto) {
        provinceService.createProvinceAndListDistrict(dto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getProvince() {
        return new ResponseEntity<>(provinceService.getProvince(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getListProvince() {
        return new ResponseEntity<>(provinceService.getProvinceListDistrict(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProvinceAndDistrict(@PathVariable Long id) {
        provinceService.deleteProvinceAndDistrict(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProvince(@PathVariable Long id, @RequestBody UpdateProvinceDto updateProvinceDto) {
        provinceService.updateProvince(id, updateProvinceDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProvinceAndListDistrict(@PathVariable Long id, @RequestBody UpdateProvinceAndDistrictDto dto) {
        provinceService.updateProvinceAndListDistrict(id, dto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }
}
