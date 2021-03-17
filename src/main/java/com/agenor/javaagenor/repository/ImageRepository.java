package com.agenor.javaagenor.repository;

import com.agenor.javaagenor.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
