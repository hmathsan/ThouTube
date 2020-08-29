package com.thoutube.controllers.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenDto {
    private String token;
    private LocalDateTime willExpireAt;
    private String type;

    public TokenDto(String token, Date willExpireAt, String type) {
        this.token = token;
        this.willExpireAt = convertToLocalDateTime(willExpireAt);
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getWillExpireAt() {
        return this.willExpireAt;
    }

    public LocalDateTime setWillExpireAt(LocalDateTime willExpireAt) {
        this.willExpireAt = willExpireAt;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
