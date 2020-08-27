package com.thoutube.controllers;

import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.model.Video;
import com.thoutube.services.VideoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    private VideoServices videoServices;

    @GetMapping
    public ResponseEntity<Page<DetailedVideoDto>> detailedIndex(@RequestParam int page, 
        @RequestParam int pageSize, @RequestParam String orderBy) {
        Page<DetailedVideoDto> videos = videoServices.detailedIndex(page, pageSize, orderBy);
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