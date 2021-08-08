package com.agenor.javaagenor.repository;

import com.agenor.javaagenor.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

    //Needed??
    List<Item> findByType(String type);
}
