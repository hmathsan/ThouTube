package com.thoutube.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.controllers.dto.PostDto;
import com.thoutube.controllers.form.CommentForm;
import com.thoutube.controllers.form.PasswordForm;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.controllers.form.UserForm;
import com.thoutube.model.Post;
import com.thoutube.model.PostComments;
import com.thoutube.model.User;
import com.thoutube.model.Video;
import com.thoutube.model.VideoComments;
import com.thoutube.repositories.PostCommentsRepository;
import com.thoutube.repositories.PostRepository;
import com.thoutube.repositories.UserRepository;
import com.thoutube.repositories.VideoCommentsRepository;
import com.thoutube.repositories.VideoRepository;
import com.thoutube.services.exceptions.ObjectNotFoundException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServices {

    @Autowired
    private ImageServices imageServices;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private PostCommentsRepository postCommentsRepository;
    @Autowired
    private VideoCommentsRepository videoCommentsRepository;

    public Page<DetailedUserDto> detailedIndex(int page, int size, String orderBy) {
        Pageable pagination = PageRequest.of(page, size, Direction.ASC, orderBy);

        Page<User> user = userRepository.findAll(pagination);
        return DetailedUserDto.convert(user);
    }

    @Transactional
    public DetailedUserDto create(UserForm form) {
        User user = new User(form.getName(), form.getEmail(), form.getPassword());

        user.setImageUrl("https://thoutube.s3-sa-east-1.amazonaws.com/default-profile-icon.png");
        userRepository.save(user);

        return new DetailedUserDto(user);
    }

    public User index(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));
    }

    public Page<DetailedPostDto> getPostByUserId(Long id, int page, int size, String orderBy) {
        Pageable pagination = PageRequest.of(page, size, Direction.ASC, orderBy);
        Page<Post> posts = postRepository.findByAuthorId(id, pagination);

        return DetailedPostDto.convert(posts);
    }

    public Page<DetailedVideoDto> getVideoByUserId(Long id, int page, int size, String orderBy) {
        Pageable pagination = PageRequest.of(page, size, Direction.ASC, orderBy);
        Page<Video> videos = videoRepository.findByAuthorId(id, pagination);

        return DetailedVideoDto.convert(videos);
    }

    @Transactional
    public DetailedUserDto newComment(Long id, String type, CommentForm form) {
        Optional<User> userObj = userRepository.findById(id);
        Optional<Post> postObj = postRepository.findById(form.getPostId());
        Optional<Video> videoObj = videoRepository.findById(form.getPostId());

        User user = userObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        if (type.equals("POST")) {
            Post post = postObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(form.getPostId())));

            PostComments comment = new PostComments(form, user, post);
            postCommentsRepository.save(comment);

        } else if (type.equals("VIDEO")) {
            Video video = videoObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(form.getPostId())));

            VideoComments comment = new VideoComments(form, user, video);
            videoCommentsRepository.save(comment);
        }

        return new DetailedUserDto(user);
    }

    @Transactional
    public DetailedVideoDto uploadVideo(Long id, String title, MultipartFile videoFile, MultipartFile thumbFile) {
        Optional<User> userObj = userRepository.findById(id);
        User user = userObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        
        Video video = new Video(title, "", "", user);
        videoRepository.save(video);
        
        String videoName = "video" + video.getId() + "." + FilenameUtils.getExtension(videoFile.getOriginalFilename());
        String thumbName = "videoThumbnail" + video.getId() + ".jpg";

        BufferedImage thumbJpg = imageServices.getJpgImageFromFile(thumbFile);
        URI videoUri = s3Service.uploadFile(videoFile, videoName);
        URI thumbUri = s3Service.uploadFile(imageServices.getInputStream(thumbJpg, "jpg"), thumbName, "image");

        video.setVideoUrl(videoUri.toString());
        video.setThumbUrl(thumbUri.toString());

        videoRepository.save(video);

        return new DetailedVideoDto(video);
    }

    @Transactional
    public PostDto newPost(Long id, PostForm form) {
        Optional<User> userObj = userRepository.findById(id);
        User user = userObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        Post post = new Post(form.getTitle(), form.getMessage(), user);
        postRepository.save(post);

        return new PostDto(post);
    }

    @Transactional
    public DetailedUserDto updateUserPassword(Long id, PasswordForm password) {
        Optional<User> userObj = userRepository.findById(id);
        User user = userObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        User updatedUser = new User(user, password.getPassword(), "");
        userRepository.save(updatedUser);

        return new DetailedUserDto(updatedUser);
    }

    @Transactional
    public URI uploadProfilePic(Long id, MultipartFile file) {
        Optional<User> userObj = userRepository.findById(id);
        User user = userObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        BufferedImage jpgImage = imageServices.getJpgImageFromFile(file);
        String fileName = "user" + user.getId() + ".jpg";

        return s3Service.uploadFile(imageServices.getInputStream(jpgImage, "jpg"), fileName, "image");
    }

    public String exceptionMsg(Long id) {
        return ("Object of ID " + id + " not found");
    }
}