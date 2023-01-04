package com.example.homework_hogwards.service;

import com.example.homework_hogwards.exception.NotFoundException;
import com.example.homework_hogwards.exception.UnsupportedMediaType;
import com.example.homework_hogwards.model.Avatar;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.repository.AvatarRepository;
import com.example.homework_hogwards.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private static final int SIZE_LIMIT = 1048576;

    @Value("${path.to.avatars.folder}")
    private String uploadDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void upload(Long studentId, MultipartFile uploadedAvatar) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow(NotFoundException::new);
        Path filePath = Path.of(
                uploadDir,
                student.getId() + "." + getExtension(uploadedAvatar.getContentType())
        );

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream fromIS = uploadedAvatar.getInputStream();
                OutputStream toIS = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bufFromIS = new BufferedInputStream(fromIS, 1024);
                BufferedOutputStream bufToIS = new BufferedOutputStream(toIS, 1024)
        ) {
            bufFromIS.transferTo(bufToIS);
        }

        Avatar avatar = findAvatarByStudentId(student.getId())
                .setFilePath(filePath.toString())
                .setFileSize(uploadedAvatar.getSize())
                .setData(uploadedAvatar.getBytes())
                .setMediaType(uploadedAvatar.getContentType())
                ;

        student.setAvatar(avatar);

        avatarRepository.save(avatar);
        studentRepository.save(student);
    }

    public Avatar findAvatarByStudentId(Long id) {
        Avatar avatar = studentRepository.findById(id).orElseThrow(NotFoundException::new).getAvatar();
        return null != avatar ? avatar : new Avatar();
    }

    private String getExtension(String contentType) {

        switch (contentType) {
            case MediaType.IMAGE_GIF_VALUE:
                return "gif";
            case MediaType.IMAGE_JPEG_VALUE:
                return "jpeg";
            case MediaType.IMAGE_PNG_VALUE:
                return "png";
        }

        throw new UnsupportedMediaType();
    }
    public Page<Avatar> getAvatars(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return avatarRepository.findAll(pageRequest);
    }
    public boolean isCorrectFileSize(long size) {
        return size > SIZE_LIMIT;
    }
}