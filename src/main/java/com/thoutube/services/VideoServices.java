package com.thoutube.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.model.Video;
import com.thoutube.repositories.VideoRepository;
import com.thoutube.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class VideoServices {
    
    @Autowired
    private VideoRepository videoRepository;

    public Page<DetailedVideoDto> detailedIndex(int page, int size, String properties) {
        Pageable pagination = PageRequest.of(page, size, Direction.ASC, properties);
        Page<Video> videos = videoRepository.findAll(pagination);
        return DetailedVideoDto.convert(videos);
    }

    public Video index(Long id) {
        Optional<Video> videoObj = videoRepository.findById(id);
        return videoObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));
    }

    @Transactional
    public Video update(Long id, String title) {
        Optional<Video> videoObj = videoRepository.findById(id);
        Video video = videoObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        Video updatedVideo = new Video(video, title);
        videoRepository.save(updatedVideo);

        return updatedVideo;
    }

    public String exceptionMsg(Long id) {
        return ("Object of ID " + id + " not found");
    }
}