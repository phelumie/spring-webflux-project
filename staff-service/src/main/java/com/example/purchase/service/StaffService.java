package com.example.purchase.service;
import com.example.purchase.repository.AuthRepository;
import com.example.purchase.exceptions.NoUserExceptions;
import com.example.purchase.exceptions.UsernameExistsExceptions;
import com.example.purchase.model.Staff;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
//@RequiredArgsConstructor
public class StaffService {
   private Validator validator;
    private AuthRepository repository;


   public Mono<Staff> create(Staff staff) {
       return repository.findByUsername(staff.getUsername())
               .filter(this::validateUniqueUsername)
               .then(Mono.just(staff)
               .filter(this::validate)
                       .doOnNext(d-> d.setJoinDate(LocalDate.now()))
               .flatMap(repository::save)).log();
   }
    public Flux<Staff> getAll(){
        return repository.findAll();
    }

    public Mono<Staff> updateEmail(Staff staff, Long id){
        return Mono.just(staff).filter(this::validate)
                .then(
                repository.findById(id)
                        .switchIfEmpty(Mono.error(new NoUserExceptions("No user with id: "+id)))
                        .doOnNext(e->e.setEmail(staff.getEmail()))
                .flatMap(repository::save)
                        .doOnError(error->{
                            log.error("error "+error.getCause());
                            System.out.println(error.getCause());
                        }));
    }

    public  Mono<String> deleteStaff(Long id){
       var message=Mono.just("User with id:"+id + " deleted successfully");
       return repository.findById(id)
               .switchIfEmpty(Mono.error(new NoUserExceptions("No user with id: "+id)))
               .flatMap(repository::delete)
               .then(message).log();
    }




    private boolean validateUniqueUsername(Staff staff) {
        if (staff.equals(null))
            return true;
        throw  new UsernameExistsExceptions("Username exists");

    }

    private boolean validate(Staff staff){
        Set<ConstraintViolation<Staff>> constraintViolations=validator.validate(staff);
        if (!CollectionUtils.isEmpty(constraintViolations)){
            StringJoiner stringJoiner = new StringJoiner(" ");
            constraintViolations.forEach(
                    loginModelConstraintViolation ->
                            stringJoiner
                                    .add(loginModelConstraintViolation.getPropertyPath().toString())
                                    .add(":")
                                    .add(loginModelConstraintViolation.getMessage()));
            throw new NoUserExceptions(stringJoiner.toString());
        }

        return true;}



}
