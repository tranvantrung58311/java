package com.example.l3_backend.dto.district;

import com.example.l3_backend.entity.Province;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDto {
    private Long id;
    private String name;
    private String code;
}