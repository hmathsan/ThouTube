package com.thoutube.controllers.form;

import javax.validation.constraints.NotBlank;

public class PasswordForm {

    @NotBlank
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}