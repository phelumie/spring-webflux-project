package com.example.purchase.Controller;

import com.example.purchase.model.Staff;
import com.example.purchase.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

@RestController
@RequestMapping("api")
//@Validated
public class Controller {
    @Autowired
    private StaffService service;

    @Autowired
    private Validator validator;
    @GetMapping("getAll")
    public ResponseEntity<Flux<Staff>> getAll(){
         return new ResponseEntity<>( service.getAll(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Mono<Staff>> create(@RequestBody Staff staff){
        return new ResponseEntity<>(service.create(staff),HttpStatus.ACCEPTED);
    }
    @PostMapping("update")
    public  ResponseEntity<Mono<Staff>> update(@RequestBody Staff staff,  @RequestParam("id")  Long id){
        return new ResponseEntity<>(service.updateEmail(staff,id),HttpStatus.OK);
    }

    @PostMapping("delete")
    public  ResponseEntity<Mono<String>> deleteStaff( @RequestParam("id")  Long id){
        return new ResponseEntity<>(service.deleteStaff(id),HttpStatus.OK);
    }

}
