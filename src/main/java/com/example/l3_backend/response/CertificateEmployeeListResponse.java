package com.example.l3_backend.response;

import com.example.l3_backend.dto.province.ProvinceDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CertificateEmployeeListResponse {
    private ProvinceDto province;
    private List<CertificateEmployeeResponse> dataList;
}
