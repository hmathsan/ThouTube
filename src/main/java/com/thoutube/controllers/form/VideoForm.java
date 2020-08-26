package com.thoutube.controllers.form;

import javax.validation.constraints.NotBlank;

public class VideoForm {

    @NotBlank
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
