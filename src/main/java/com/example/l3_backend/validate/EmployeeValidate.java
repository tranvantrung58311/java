package com.example.l3_backend.validate;

import com.example.l3_backend.constant.ErrorsConstant;
import com.example.l3_backend.constant.NbConstant;
import com.example.l3_backend.dto.certificateemployee.CertificateEmployeeDto;
import com.example.l3_backend.dto.certificateemployee.CreateCertificateEmployeeDto;
import com.example.l3_backend.dto.employee.ValidateInputEmployee;
import com.example.l3_backend.entity.CertificateEmployee;
import com.example.l3_backend.entity.Employee;
import com.example.l3_backend.repository.CertificateEmployeeRepository;
import com.example.l3_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EmployeeValidate {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CertificateEmployeeRepository certificateEmployeeRepository;

    public void validateInputEmployee(ValidateInputEmployee inputEmployee) {
        List<Employee> employeeList = employeeRepository.findByCode();
        employeeList.forEach(x -> {
            if (Objects.equals(x.getCode(), inputEmployee.getCode())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CODE_DUPLICATE);
            }
        });
        int employeeInt = inputEmployee.getCode().length();
        if (employeeInt < NbConstant.MIN_LENGTH || employeeInt > NbConstant.MAX_LENGTH) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CODE_SIZE);
        }
        if (inputEmployee.getCode().matches(NbConstant.CHECK_SPACE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CODE_SPACE);
        }
        if (Objects.equals(inputEmployee.getName(), null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_NAME_NULL);
        }
        if (inputEmployee.getAge() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_AGE);
        }
        if (!inputEmployee.getEmail().matches(NbConstant.CHECK_EMAIL)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_EMAIL_FORMAT);
        }
        if (!inputEmployee.getPhone().matches(NbConstant.CHECK_PHONE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_PHONE);
        }
        if (inputEmployee.getCommune_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_COMMUNE_ID_EMPLOYEE);
        }
        if (inputEmployee.getDistrict_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_DISTRICT_ID_EMPLOYEE);
        }
        if (inputEmployee.getProvince_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_PROVINCE_ID_EMPLOYEE);
        }
    }

    public void validateCertificateEmployee(CreateCertificateEmployeeDto employeeDto) {
        Long input_province_id = employeeDto.getProvince_id();
        List<CertificateEmployeeDto> certificateEmployeeDto = employeeDto.getCertificateEmployeeDtos();
        certificateEmployeeDto.forEach(x -> {
            if (x.getEndDay().isBefore(LocalDate.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CERTIFICATE_OUTOFDATE);
            }
            AtomicInteger count = new AtomicInteger();
            List<CertificateEmployee> certificateEmployee = certificateEmployeeRepository.findByEmployeeId(x.getEmployee_id());
            certificateEmployee.forEach(x1 -> {
                if (Objects.equals(x1.getCode(), x.getCode())) {
                    if (x1.getEndDay().isAfter(LocalDate.now())) {
                        count.getAndIncrement();
                        if (input_province_id.equals(x1.getProvince().getId())) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_CERTIFICATE_EMPLOYEE);
                        }
                        if (count.get() >= 3) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_THAN_THREE_CERTIFICATE);
                        }
                    }
                }
            });
        });
    }
}


















