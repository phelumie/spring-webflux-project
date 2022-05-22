package com.example.sales.repo;


import com.example.sales.entity.SalesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SalesRepo extends ReactiveCrudRepository<SalesEntity, Long> {

	Mono<SalesEntity> findById(Long userId);
}
