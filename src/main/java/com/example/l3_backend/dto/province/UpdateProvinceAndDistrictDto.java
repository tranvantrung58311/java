package com.example.l3_backend.dto.province;

import com.example.l3_backend.model.district.DistrictModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UpdateProvinceAndDistrictDto {
    private String name;
    private String code;
    private List<DistrictModel> districts;
}
