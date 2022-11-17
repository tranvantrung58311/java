package com.example.l3_backend.controller;

import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.district.ProvinceDistrictCommuneDto;
import com.example.l3_backend.model.district.CreateDistrictModel;
import com.example.l3_backend.model.district.DistrictModel;
import com.example.l3_backend.dto.district.CreateDistrictDto;
import com.example.l3_backend.dto.district.UpdateDistrictDto;
import com.example.l3_backend.model.district.ListDistrictModel;
import com.example.l3_backend.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/District")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<Object> createDistrict(@RequestBody CreateDistrictDto createDistrictDto) {
        districtService.createDistrict(createDistrictDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @PostMapping("/district")
    public ResponseEntity<Object> createDistrictProvinceId(@RequestBody CreateDistrictModel createDistrictModel) {
        districtService.createDistrictAndProvinceId(createDistrictModel);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @PostMapping("/province/district/commune")
    public ResponseEntity<Object> CreateProvinceDistrictCommune(@RequestBody ProvinceDistrictCommuneDto dto) {
        districtService.CreateProvinceDistrictCommune(dto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getDistrict() {
        return new ResponseEntity<>(districtService.getDistrict(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getDistrictIdProvince(@RequestParam Long province_id) {
        List<DistrictModel> districtList = districtService.getDistrictFollowProvinceId(province_id);
        return new ResponseEntity<>(districtList, HttpStatus.OK);
    }

    @GetMapping("/list/get")
    public ResponseEntity<Object> getListDistrict() {
        List<ListDistrictModel> districtList = districtService.getDistrictListCommune();
        return new ResponseEntity<>(districtList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDistrictAndCommune(@PathVariable Long id) {
        districtService.deleteDistrictAndCommune(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDistrict(@PathVariable Long id, @RequestBody UpdateDistrictDto updateDistrictDto) {
        districtService.updateDistrict(id, updateDistrictDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDistrictAndProvinceId(@PathVariable Long id, @RequestBody DistrictModel districtModel) {
        districtService.updateDistrictAndProvinceId(id, districtModel);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }
}
