package com.agenor.javaagenor.service;

import com.agenor.javaagenor.model.Image;
import com.agenor.javaagenor.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile image) throws IOException {
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        Image _image = new Image(imageName, image.getContentType(), image.getBytes());

        return imageRepository.save(_image);
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllImages() {
        return imageRepository.findAll().stream();
    }
}
