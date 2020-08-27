package com.thoutube.controllers.form;

import javax.validation.constraints.NotNull;

public class PostForm {

    @NotNull
    private String title;
    @NotNull
    private String message;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
