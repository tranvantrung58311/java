package com.example.l3_backend.controller;

import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.commune.CreateCommuneDto;
import com.example.l3_backend.dto.commune.DistrictCommuneListDto;
import com.example.l3_backend.dto.commune.UpdateCommuneDto;
import com.example.l3_backend.model.commune.CommuneModel;
import com.example.l3_backend.model.commune.CreateCommuneModel;
import com.example.l3_backend.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Commune")

public class CommuneController {
    @Autowired
    private CommuneService communeService;

    @PostMapping
    public ResponseEntity<Object> createCommune(@RequestBody CreateCommuneDto createCommuneDto) {
        communeService.createCommune(createCommuneDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCommuneAndDistrictId(@RequestBody CreateCommuneModel createCommuneModel) {
        communeService.createCommuneAndDistrictId(createCommuneModel);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @PostMapping("/create/list")
    public ResponseEntity<Object> createDistrictAndListCommune(@RequestBody DistrictCommuneListDto dto) {
        communeService.createDistrictAndListCommune(dto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getCommune() {
        return new ResponseEntity<>(communeService.getCommune(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getCommuneFollowDistrictId(@RequestParam Long district_id) {
        return new ResponseEntity<>(communeService.getCommuneFollowDistrictId(district_id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCommune(@PathVariable Long id) {
        communeService.deleteCommune(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCommune(@PathVariable Long id, @RequestBody UpdateCommuneDto updateCommuneDto) {
        communeService.updateCommune(id, updateCommuneDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCommuneAndDistrictId(@PathVariable Long id, @RequestBody CommuneModel communeModel) {
        communeService.updateCommuneAndDistrictId(id, communeModel);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }

    @PutMapping("/update/list/{id}")
    public ResponseEntity<Object> updateDistrictAndListCommune(@PathVariable Long id, @RequestBody DistrictCommuneListDto dto) {
        communeService.updateDistrictAndListCommune(id, dto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }
}
