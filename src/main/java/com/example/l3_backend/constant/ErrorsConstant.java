package com.example.l3_backend.constant;

public interface ErrorsConstant {
    String ERROR_CODE_DUPLICATE = "Code không được trùng";
    String ERROR_CODE_SIZE = "Code dài tối thiểu 6 ký tự tối đa 10 ký tự";
    String ERROR_CODE_SPACE = "Code không có dấu cách";
    String ERROR_NAME_NULL = "Tên bắt buộc nhập";
    String ERROR_EMAIL_FORMAT = "Email chưa đúng định dạng";
    String ERROR_AGE = "Tuổi không được âm";
    String ERROR_PHONE = "Số điện thoại chỉ gồm số không dài quá 11 ký tự";
    String ERROR_SYSTEM = "Lỗi hệ thống";
    String ERROR_UPDATE_EMPLOYEE = "Phải gửi thông tin cả 3 trường mới được sửa";
    String ERROR_CREATE_EMPLOYEE = "Thêm mới Employee phải có cả 3 trường: Tỉnh, Huyện, Xã";
    String ERROR_COMMUNE_ID_EMPLOYEE = "Chưa thêm mới trường commune_id";
    String ERROR_DISTRICT_ID_EMPLOYEE = "Chưa thêm mới trường district_id";
    String ERROR_PROVINCE_ID_EMPLOYEE = "Chưa thêm mới trường province_id";
    String ERROR_COMMUNE_NO_BELONG_DISTRIC = "commune không thuộc district";
    String ERROR_DISTRICT_NO_BELONG_PROVINCE = "district không thuộc province";
    String ERROR_CERTIFICATE_EMPLOYEE = "Văn bằng quá hạn";
    String ERROR_SEARCH_ID = "Không có id bạn muốn tìm ! Vui lòng nhập lại id";
    String ERROR_CERTIFICATE_OUTOFDATE = "Văn bằng quá hạn, Vui lòng nhập lại văn bằng mới";

    String ERROR_THAN_THREE_CERTIFICATE ="Nhân viên không được có quá 3 văn bằng cùng loại còn hiệu lực";
    String ERROR_FILE_ILLEGAL ="file không hợp lệ";
}
