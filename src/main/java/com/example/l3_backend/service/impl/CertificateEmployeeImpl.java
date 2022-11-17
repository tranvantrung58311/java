package com.example.l3_backend.service.impl;

import com.example.l3_backend.constant.ErrorsConstant;
import com.example.l3_backend.dto.certificateemployee.CertificateEmployeeDto;
import com.example.l3_backend.dto.certificateemployee.CreateCertificateEmployeeDto;
import com.example.l3_backend.dto.certificateemployee.IdCertificateEmployeeDto;
import com.example.l3_backend.dto.province.ProvinceDto;
import com.example.l3_backend.entity.CertificateEmployee;
import com.example.l3_backend.entity.Employee;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.repository.CertificateEmployeeRepository;
import com.example.l3_backend.repository.EmployeeRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import com.example.l3_backend.response.CertificateEmployeeListResponse;
import com.example.l3_backend.response.CertificateEmployeeResponse;
import com.example.l3_backend.service.CertificateEmployeeService;
import com.example.l3_backend.validate.EmployeeValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CertificateEmployeeImpl implements CertificateEmployeeService {
    @Autowired
    private CertificateEmployeeRepository certificateEmployeeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeValidate validate;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;

    @Override
    public CertificateEmployeeListResponse createCertificateEmployee(CreateCertificateEmployeeDto employeeDto) {
        validate.validateCertificateEmployee(employeeDto);
        CertificateEmployeeListResponse response = new CertificateEmployeeListResponse();
        Province province = provinceRepository.findById(employeeDto.getProvince_id()).get();
        List<CertificateEmployeeDto> certificateEmployeeDtoList = employeeDto.getCertificateEmployeeDtos();
        List<CertificateEmployee> certificateEmployeeList = new ArrayList<>();
        certificateEmployeeDtoList.forEach(x -> {
            Employee employee = employeeRepository.findById(x.getEmployee_id()).get();
            CertificateEmployee certificateEmployee = mapper.map(x, CertificateEmployee.class);
            certificateEmployee.setEmployee(employee);
            certificateEmployee.setProvince(province);
            certificateEmployeeList.add(certificateEmployee);
        });
        List<CertificateEmployee> certificateEmployees = certificateEmployeeRepository.saveAll(certificateEmployeeList);
        ProvinceDto provinceDto = mapper.map(province, ProvinceDto.class);
        response.setProvince(provinceDto);
        List<CertificateEmployeeResponse> list = listMapper.mapList(certificateEmployees, CertificateEmployeeResponse.class);
        response.setDataList(list);
        return response;
    }

    @Override
    public IdCertificateEmployeeDto getCertificateEmployeeId(Long id) {
        IdCertificateEmployeeDto idCertificateEmployeeDto = new IdCertificateEmployeeDto();
        if (Objects.nonNull(id)) {
            Optional<CertificateEmployee> certificateEmployee = certificateEmployeeRepository.findById(id);
            if (certificateEmployee.isPresent()) {
                mapper.map(certificateEmployee.get(), idCertificateEmployeeDto);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_SEARCH_ID);
            }
        }
        return idCertificateEmployeeDto;
    }

    @Override
    public List<IdCertificateEmployeeDto> deleteCertificateEmployeeId(Long id) {
        List<CertificateEmployee> certificateEmployeeList = certificateEmployeeRepository.deleteByEmployeeId(id);
        return listMapper.mapList(certificateEmployeeList, IdCertificateEmployeeDto.class);
    }
}
