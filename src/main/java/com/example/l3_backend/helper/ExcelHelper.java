package com.example.l3_backend.helper;

import com.example.l3_backend.constant.ErrorsConstant;
import com.example.l3_backend.constant.NbConstant;
import com.example.l3_backend.entity.Commune;
import com.example.l3_backend.entity.District;
import com.example.l3_backend.entity.Employee;
import com.example.l3_backend.entity.Province;
import com.example.l3_backend.repository.CommuneRepository;
import com.example.l3_backend.repository.DistrictRepository;
import com.example.l3_backend.repository.ProvinceRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Component
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Sheet1";
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    public static void checkExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_FILE_ILLEGAL);
        }
    }

    public List<Employee> importExcelToEmployees(InputStream is) throws IOException {
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet(SHEET);
        Iterator<Row> rows = sheet.iterator();
        List<Employee> employeeList = new ArrayList<>();
        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }
            Iterator<Cell> cellsInRow = currentRow.iterator();
            Employee employee = new Employee();
            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                switch (cellIdx) {
                    case 1:
                        String clName = getColume(cellIdx);
                        int lineName = currentRow.getRowNum() + 1;
                        if (Objects.equals(currentCell.getStringCellValue(), null)) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lỗi tên null: Dòng " + lineName + ", Cột " + clName);
                        }
                        employee.setName(currentCell.getStringCellValue());
                        break;
                    case 2:
                        String clCode = getColume(cellIdx);
                        int lineCode = currentRow.getRowNum() + 1;
                        int checkCode = currentCell.getStringCellValue().length();
                        if (checkCode < NbConstant.MIN_LENGTH || checkCode > NbConstant.MAX_LENGTH) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code dài tối thiểu 6 ký tự tối đa 10 ký tự: Dòng " + lineCode + ", Cột " + clCode);
                        }
                        employee.setCode(currentCell.getStringCellValue());
                        break;
                    case 3:
                        String clAge = getColume(cellIdx);
                        int lineAge = currentRow.getRowNum() + 1;
                        int checkAge = (int) currentCell.getNumericCellValue();
                        if (checkAge < 0) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tuổi không được âm: Dòng " + lineAge + ", Cột " + clAge);
                        }
                        employee.setAge((int) currentCell.getNumericCellValue());
                        break;
                    case 4:
                        boolean checkEmail = currentCell.getStringCellValue().matches(NbConstant.CHECK_EMAIL);
                        String clEmail = getColume(cellIdx);
                        int lineEmail = currentRow.getRowNum() + 1;
                        if (!checkEmail) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email chưa đúng định dạng: Dòng " + lineEmail + ", Cột " + clEmail);
                        }
                        employee.setEmail(currentCell.getStringCellValue());
                        break;
                    case 5:
                        String phone = currentCell.getStringCellValue();
                        String clPhone = getColume(cellIdx);
                        int linePhone = currentRow.getRowNum() + 1;
                        if (phone.length() != 10) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lỗi phone: Dòng " + linePhone + ", Cột " + clPhone);
                        }
                        employee.setPhone(phone);
                        break;
                    case 6:
                        String communeCell = currentCell.getStringCellValue();
                        Commune commune = communeRepository.findByName(communeCell);
                        employee.setCommune(commune);
                        break;
                    case 7:
                        String districtCell = currentCell.getStringCellValue();
                        District district = districtRepository.findByName(districtCell);
                        employee.setDistrict(district);
                        break;
                    case 8:
                        String provinceCell = currentCell.getStringCellValue();
                        Province province = provinceRepository.findByName(provinceCell);
                        employee.setProvince(province);
                        break;
                    default:
                        break;
                }
                cellIdx++;
            }
            employeeList.add(employee);
        }
        workbook.close();
        return employeeList;
    }

    private String getColume(int column) {
        if (column == 1) {
            return "B";
        } else if (column == 2) {
            return "C";
        } else if (column == 3) {
            return "D";
        } else if (column == 4) {
            return "E";
        } else if (column == 5) {
            return "F";
        } else if (column == 6) {
            return "G";
        } else if (column == 7) {
            return "H";
        } else if (column == 8) {
            return "I";
        }
        return "No colume";
    }
}
