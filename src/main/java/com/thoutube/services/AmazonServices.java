package com.thoutube.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonServices {

    @Autowired
    private S3Service s3Service;
    
    public URI uploadProfilePic(MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }
}