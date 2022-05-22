package com.example.item.repo;


import com.example.item.entity.ItemEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepo extends ReactiveCrudRepository<ItemEntity, Long> {

}
