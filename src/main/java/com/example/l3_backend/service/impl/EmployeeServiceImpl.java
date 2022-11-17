package com.example.l3_backend.service.impl;

import com.example.l3_backend.constant.ErrorsConstant;
import com.example.l3_backend.dto.employee.EmployeeDto;
import com.example.l3_backend.dto.employee.ValidateInputEmployee;
import com.example.l3_backend.entity.Commune;
import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Employee;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.helper.ExcelHelper;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.repository.CommuneRepository;
import com.example.l3_backend.repository.DistrictRepository;
import com.example.l3_backend.repository.EmployeeRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import com.example.l3_backend.service.EmployeeService;
import com.example.l3_backend.validate.EmployeeValidate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ExcelHelper excelHelper;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private EmployeeValidate validate;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public void createEmployee(ValidateInputEmployee inputEmployee) {
        validate.validateInputEmployee(inputEmployee);
        Employee employee = mapper.map(inputEmployee, Employee.class);
        Commune commune = communeRepository.findById(inputEmployee.getCommune_id()).orElseThrow(null);
        District district = districtRepository.findById(inputEmployee.getDistrict_id()).orElseThrow(null);
        Province province = provinceRepository.findById(inputEmployee.getProvince_id()).orElseThrow(null);
        if (!Objects.equals(province, district.getProvince())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_DISTRICT_NO_BELONG_PROVINCE);
        }
        if (!Objects.equals(district, commune.getDistrict())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_COMMUNE_NO_BELONG_DISTRIC);
        }
        if (inputEmployee.getCommune_id() == null || inputEmployee.getDistrict_id() == null || inputEmployee.getProvince_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CREATE_EMPLOYEE);
        } else {
            employee.setCommune(commune);
            employee.setDistrict(district);
            employee.setProvince(province);
        }
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return listMapper.mapList(employeeList, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployee() {
        employeeRepository.deleteAll();
    }

    @Override
    public void updateEmployee(Long id, ValidateInputEmployee dto) {
        validate.validateInputEmployee(dto);
        Employee employee = employeeRepository.findById(id).orElseThrow(null);
        mapper.map(dto, employee);
        Province province = provinceRepository.findById(dto.getProvince_id()).orElseThrow(null);
        District district = districtRepository.findById(dto.getDistrict_id()).orElseThrow(null);
        Commune commune = communeRepository.findById(dto.getCommune_id()).orElseThrow(null);
        if (!Objects.equals(province, district.getProvince())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_DISTRICT_NO_BELONG_PROVINCE);
        }
        if (!Objects.equals(district, commune.getDistrict())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_COMMUNE_NO_BELONG_DISTRIC);
        }
        if (dto.getCommune_id() == null || dto.getDistrict_id() == null || dto.getProvince_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_UPDATE_EMPLOYEE);
        } else {
            employee.setProvince(province);
            employee.setDistrict(district);
            employee.setCommune(commune);
        }
        employeeRepository.save(employee);
    }

    @Override
    public void importEmployee(MultipartFile file) throws IOException {
        ExcelHelper.checkExcelFormat(file);
        List<Employee> employeeList = excelHelper.importExcelToEmployees(file.getInputStream());
        employeeRepository.saveAll(employeeList);
    }
}
