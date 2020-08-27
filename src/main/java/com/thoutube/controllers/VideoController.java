package com.thoutube.controllers;

import java.util.List;
import java.util.Optional;

import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.model.Video;
import com.thoutube.repositories.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<DetailedVideoDto> detailedIndex() {
        List<Video> videos = videoRepository.findAll();
        return DetailedVideoDto.convert(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedVideoDto> index(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);

        if(video.isPresent()) {
            return ResponseEntity.ok(new DetailedVideoDto(video.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedVideoDto> updateLikes(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);

        if(video.isPresent()) {
            Video updatedVideo = new Video(video.get(), "");
            videoRepository.save(updatedVideo);
            return ResponseEntity.ok(new DetailedVideoDto(updatedVideo));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/{title}")
    public ResponseEntity<DetailedVideoDto> updateTitle(@PathVariable Long id, @PathVariable String title) {
        Optional<Video> video = videoRepository.findById(id);

        if(video.isPresent()) {
            Video updatedVideo = new Video(video.get(), title);
            videoRepository.save(updatedVideo);
            return ResponseEntity.ok(new DetailedVideoDto(updatedVideo));
        }

        return ResponseEntity.notFound().build();
    }
}