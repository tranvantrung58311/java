package com.example.l3_backend.model.district;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDistrictModel {
    private Long id;
    private String name;
    private String code;
    private Long province_id;
}
