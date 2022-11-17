package com.example.l3_backend.dto.commune;

import com.example.l3_backend.model.commune.CommuneModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DistrictCommuneListDto {
    private String name;
    private String code;
    private Long province_id;
    private List<CommuneModel> communeModelList;
}
