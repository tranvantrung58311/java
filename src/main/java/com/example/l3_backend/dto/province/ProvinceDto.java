package com.example.l3_backend.dto.province;

import com.example.l3_backend.entity.District;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProvinceDto {
    private Long id;
    private String name;
    private String code;
}
