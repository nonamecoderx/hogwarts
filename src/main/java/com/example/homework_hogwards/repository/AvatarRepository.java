package com.example.homework_hogwards.repository;

import com.example.homework_hogwards.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}