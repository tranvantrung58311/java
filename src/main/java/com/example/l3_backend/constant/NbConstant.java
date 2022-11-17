package com.example.l3_backend.constant;

public interface NbConstant {
    int MIN_LENGTH = 6;
    int MAX_LENGTH = 10;
    String CHECK_EMAIL = "^[\\w]+@+[\\w]+[.]+[\\w]+{2,3}$";
    String CHECK_PHONE = "^0*(9|8|3)*[0-9]{1,10}$";
    String CHECK_SPACE ="\\s";
}
