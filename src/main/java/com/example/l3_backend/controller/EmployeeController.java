package com.example.l3_backend.controller;

import com.example.l3_backend.common.DataResponse;
import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.employee.EmployeeDto;
import com.example.l3_backend.dto.employee.ValidateInputEmployee;
import com.example.l3_backend.helper.ExcelHelper;
import com.example.l3_backend.service.EmployeeService;
import com.example.l3_backend.util.ExportExcelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Api/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ExcelHelper excelHelper;

    @PostMapping
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody ValidateInputEmployee inputEmployee) {
        employeeService.createEmployee(inputEmployee);
        return DataResponse.setDataCustom(null, AppConstant.SUCCESS_CREATE, "200", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getEmployee() {
        return DataResponse.setDataCustom(employeeService.getEmployee(), AppConstant.SUCCESS_SEARCH, "200", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return DataResponse.setDataCustom(null, AppConstant.SUCCESS_DELETE, "200", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllEmployee() {
        employeeService.deleteAllEmployee();
        return DataResponse.setDataCustom(null, AppConstant.SUCCESS_DELETE, "200", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDistrict(@PathVariable Long id, @RequestBody ValidateInputEmployee dto) {
        employeeService.updateEmployee(id, dto);
        return DataResponse.setDataCustom(null, AppConstant.SUCCESS_UPDATE, "200", HttpStatus.OK);
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee.xlsx";
        response.setHeader(headerKey, headerValue);

        List<EmployeeDto> listOfStudents = employeeService.getEmployee();
        ExportExcelGenerator generator = new ExportExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }

    @PostMapping("/import")
    public ResponseEntity<Object> importEmployee(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.importEmployee(file);
        return DataResponse.setDataCustom("Uploaded the file successfully", "Uploaded the file successfully: " + file.getOriginalFilename(), "200", HttpStatus.OK);
    }
}
