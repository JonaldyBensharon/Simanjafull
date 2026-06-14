package com.simanja.dto;

public class RegisterRequest {

    private String nama;
    private String email;
    private String password;
    private String konfirmPassword;

    public RegisterRequest() {
    }

    public RegisterRequest(
            String nama,
            String email,
            String password,
            String konfirmPassword
    ) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.konfirmPassword = konfirmPassword;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(
            String nama
    ) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email
    ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password
    ) {
        this.password = password;
    }

    public String getKonfirmPassword() {
        return konfirmPassword;
    }

    public void setKonfirmPassword(
            String konfirmPassword
    ) {
        this.konfirmPassword = konfirmPassword;
    }
}