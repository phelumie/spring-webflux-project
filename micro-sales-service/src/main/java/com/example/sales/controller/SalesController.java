package com.example.sales.controller;


import com.example.sales.common.messages.BaseResponse;
import com.example.sales.dto.SalesDTO;
import com.example.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<Flux<SalesDTO>> getAllSales() {
		Flux<SalesDTO> list = salesService.findSalesList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/by-id")
	public ResponseEntity<Mono<SalesDTO>> getSalesById(@RequestParam Long id) {
		Mono<SalesDTO> list = salesService.findBySalesId(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/name/by-id")
	public ResponseEntity<Mono<SalesDTO>> getSalesNameById(@RequestParam Long id) {
		Mono<SalesDTO> list = salesService.findBySalesId(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<Mono<BaseResponse>> createOrUpdateSales(@RequestBody SalesDTO userDTO) {
		Mono<BaseResponse> response = salesService.createOrUpdateSales(userDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Mono<BaseResponse>> deleteSalesById(@PathVariable("id") Long id) {
		Mono<BaseResponse> response = salesService.deleteSales(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
