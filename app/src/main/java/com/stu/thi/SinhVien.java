package com.stu.thi;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String msv;
    private String hoTen;
    private int namSinh;

    public SinhVien(String msv, String hoTen, int namSinh) {
        this.msv = msv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return msv + " \n " + hoTen +" \n "+namSinh;
    }
}
