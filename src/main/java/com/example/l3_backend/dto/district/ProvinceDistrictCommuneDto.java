package com.example.l3_backend.dto.district;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.model.commune.CommuneModel;
import com.example.l3_backend.model.district.DistrictModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinceDistrictCommuneDto {
    private String name;
    private String code;
    private DistrictDto districtDto;
    private CommuneDto communeDto;
}
