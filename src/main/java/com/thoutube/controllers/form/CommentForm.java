package com.thoutube.controllers.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentForm {

    @NotNull
    private Long postId;
    @NotBlank
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


}
