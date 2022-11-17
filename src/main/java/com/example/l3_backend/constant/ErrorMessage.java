package com.example.l3_backend.constant;

public enum ErrorMessage {
    ERROR_CODE_DUPLICATE(1, "Code không được trùng"),
    ERROR_CODE_SPACE(2, "Không có dấu cách"),
    ERROR_CODE_LENGTH(3, "Dài tối thiểu 6 ký tự tối đa 10 ký tự"),
    ERROR_NAME_NULL(4, "Tên bắt buộc nhập"),
    ERROR_EMAIL(5, "Email chưa đúng định dạng"),
    ERROR_PHONE(6, "Số điện thoại chỉ gồm số không dài quá 11 ký tự"),
    ERROR_AGE(7, "Tuổi không được âm"),
    SUCCESS(200, "Thành công");

    private Integer key;
    private String value;

    ErrorMessage(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
