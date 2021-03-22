package com.agenor.javaagenor.repository;

import com.agenor.javaagenor.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    //Needed??
    List<Item> findByType(String type);
}
