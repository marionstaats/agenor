package com.agenor.javaagenor.controller;

import com.agenor.javaagenor.model.Item;
import com.agenor.javaagenor.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Run command: mvnw spring-boot:run

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String title) {
        try {
            List<Item> items = new ArrayList<>();

            if (title == null)
                items.addAll(itemRepository.findAll());
            else
                items.addAll(itemRepository.findByTitleContaining(title));

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {

        try {
            Item _item = itemRepository
                    .save(new Item(
                            item.getTitle(),
                            item.getPrice(),
                            item.getDescription(),
                            item.isPublished(),
                            item.getMainImage(),
                            item.getSmallImage1(),
                            item.getSmallImage2(),
                            item.getSmallImage3(),
                            item.getType()
                            ));
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            Item _item = itemData.get();

            _item.setTitle(item.getTitle());
            _item.setPrice(item.getPrice());
            _item.setDescription(item.getDescription());
            _item.setPublished(item.isPublished());
            _item.setMainImage(item.getMainImage());
            _item.setSmallImage1(item.getSmallImage1());
            _item.setSmallImage2(item.getSmallImage2());
            _item.setSmallImage3(item.getSmallImage3());
            _item.setType(item.getType());

            return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items")
    public ResponseEntity<HttpStatus> deleteAllItems() {
        try {
            itemRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
