package com.agenor.javaagenor.controller;


import com.agenor.javaagenor.message.ResponseImage;
import com.agenor.javaagenor.message.ResponseMessage;
import com.agenor.javaagenor.model.Image;
import com.agenor.javaagenor.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadImage(@RequestParam("image") MultipartFile image) {
        String message = "";
        try {
            Image myImage = storageService.store(image);
            message = "Image was uploaded successfully: " + image.getOriginalFilename();
            return new ResponseEntity(new ResponseMessage(message, myImage.getId()), HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the image: " + image.getOriginalFilename();
            return new ResponseEntity(new ResponseMessage(message), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("images")
    public ResponseEntity<List<ResponseImage>> getAllImages() {
        List<ResponseImage> images = storageService.getAllImages().map(dbImage -> {
            String imageDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(dbImage.getId())
                    .toUriString();

            return new ResponseImage(
                    dbImage.getName(),
                    imageDownloadUri,
                    dbImage.getType(),
                    dbImage.getData().length);
            }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Image image = storageService.getImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagename=\""+ image.getName() + "\"" )
                .body(image.getData());
    }
}
