package com.example.purchase.repository;


import com.example.purchase.model.Staff;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface AuthRepository extends ReactiveCrudRepository<Staff,Long> {
    Mono<Staff> findByEmail(String email);
    Mono<Staff> findByUsername(String username);
}
