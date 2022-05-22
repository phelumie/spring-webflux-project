package com.example.item.controller;

import javax.validation.Valid;

import com.example.item.dto.ItemDTO;
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

import com.example.item.common.messages.BaseResponse;
import com.example.item.service.ItemService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
//	,produces = MediaType.TEXT_EVENT_STREAM_VALUE

	@GetMapping(value = "/find")
	public ResponseEntity<Flux<ItemDTO>> getAllItems() {
		Flux<ItemDTO> list = itemService.findItemList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/by-id")
	public ResponseEntity<Mono<ItemDTO>> getItemById(@RequestParam Long id) {
		var result=itemService.getItems(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<Mono<BaseResponse>> createOrUpdateItem(@Valid @RequestBody ItemDTO productDTO) {
		Mono<BaseResponse> response = itemService.createOrUpdateItem(productDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Mono<BaseResponse>> deleteItemById(@PathVariable("id") Long id) {
		Mono<BaseResponse> response = itemService.deleteItem(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
