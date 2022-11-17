package com.example.l3_backend.model.province;

import com.example.l3_backend.dto.district.DistrictDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProvinceModel {
    private Long id;
    private String name;
    private String code;
    private List<DistrictDto> districts;
}
