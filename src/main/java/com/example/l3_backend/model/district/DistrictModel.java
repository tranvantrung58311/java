package com.example.l3_backend.model.district;

import com.example.l3_backend.entity.Province;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictModel {
    private Long id;
    private String name;
    private String code;
    private Long province_id;
}
