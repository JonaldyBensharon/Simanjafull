package com.simanja.backend.dto;

public class RegisterRequest {

    private String nama;
    private String email;
    private String password;
    private String konfirmPassword;

    public RegisterRequest() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKonfirmPassword() {
        return konfirmPassword;
    }

    public void setKonfirmPassword(String konfirmPassword) {
        this.konfirmPassword = konfirmPassword;
    }
}