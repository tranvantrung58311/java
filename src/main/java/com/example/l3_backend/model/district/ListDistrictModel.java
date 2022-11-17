package com.example.l3_backend.model.district;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.dto.province.ProvinceDto;
import com.example.l3_backend.entity.Commune;
import com.example.l3_backend.entity.Province;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListDistrictModel {
    private Long id;
    private String name;
    private String code;
    private ProvinceDto province;
    private Long province_id;
    private List<CommuneDto> communes;
}
