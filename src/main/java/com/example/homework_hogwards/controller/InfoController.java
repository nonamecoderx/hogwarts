package com.example.homework_hogwards.controller;

import com.example.homework_hogwards.dto.ServerPortResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/getPort")
    public ServerPortResponseDto getPort() {
        return (new ServerPortResponseDto(serverPort));
    }
}