package com.thoutube.controllers;

import java.util.List;

import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.model.Video;
import com.thoutube.services.VideoServices;

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
    private VideoServices videoServices;

    @GetMapping
    public ResponseEntity<List<DetailedVideoDto>> detailedIndex() {
        List<DetailedVideoDto> videos = videoServices.detailedIndex();
        return ResponseEntity.ok().body(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedVideoDto> index(@PathVariable Long id) {
        Video video = videoServices.index(id);
        return ResponseEntity.ok().body(new DetailedVideoDto(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedVideoDto> updateLikes(@PathVariable Long id) {
        Video video = videoServices.update(id, "");
        return ResponseEntity.ok().body(new DetailedVideoDto(video));
    }

    @PutMapping("/{id}/{title}")
    public ResponseEntity<DetailedVideoDto> updateTitle(@PathVariable Long id, @PathVariable(required = true) String title) {
        Video video = videoServices.update(id, title);
        return ResponseEntity.ok().body(new DetailedVideoDto(video));
    }
}