package com.example.homework_hogwards.controller;

import com.example.homework_hogwards.model.Avatar;
import com.example.homework_hogwards.service.AvatarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value ="/{studentId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadStudentAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        if (avatarService.isCorrectFileSize(avatar.getSize())) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
        }
        avatarService.upload(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}/preview")
    public ResponseEntity<byte[]> getStudentAvatar(@PathVariable Long studentId) {
        Avatar avatar = avatarService.findAvatarByStudentId(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(avatar.getMediaType()));
        headers.setContentLength(avatar.getFileSize());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/{studentId}/detail")
    public void getStudentAvatar(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatarByStudentId(studentId);

        try (
                InputStream inputStream = Files.newInputStream(Path.of(avatar.getFilePath()));
                OutputStream responseStream = response.getOutputStream()
        ) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            inputStream.transferTo(responseStream);
        }
    }
}