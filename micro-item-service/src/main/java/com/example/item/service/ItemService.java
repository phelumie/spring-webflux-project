package com.example.item.service;

import javax.transaction.Transactional;

import com.example.item.dto.SalesDTO;
import com.example.item.dto.ItemDTO;
import com.example.item.entity.ItemEntity;
import com.example.item.repo.ItemRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.item.common.exceptions.RecordNotFoundException;
import com.example.item.common.messages.BaseResponse;
import com.example.item.common.messages.CustomMessage;
import com.example.item.common.utils.Topic;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ItemService {

	@Autowired
	private WebClient.Builder webClient;


	@Autowired
	private ItemRepo itemRepo;

	public Flux<ItemDTO> findItemList() {
		return itemRepo.findAll().map(this::copyItemEntityToDto).log();
	}

	public Mono<ItemDTO> findByItemId(Long itemId) {
	return itemRepo.findById(itemId)
				.switchIfEmpty(Mono.error(new RecordNotFoundException("item id '" + itemId + "' does not exist !")))
				.map(this::copyItemEntityToDto).log();
	}

	public Mono<BaseResponse> createOrUpdateItem(ItemDTO itemDTO) {
	 	return Mono.just(itemDTO).map(this::copyItemDtoToEntity)
				.flatMap(itemRepo::save)
				.map(m-> new BaseResponse(Topic.Item.getName()
						+ CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value())).log();
	}

	public Mono<BaseResponse> deleteItem(Long itemId) {
		return itemRepo.findById(itemId)
				.flatMap(itemRepo::delete)
				.map(m-> new BaseResponse(Topic.Item.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE, HttpStatus.OK.value()))
				.doOnError(e-> new RecordNotFoundException("No record found for given id: " + itemId)).log();
	}

	public Mono<ItemDTO> getItems(Long id){
		String url = "http://sales/sales/find/name/by-id?id=" + id;


	return  webClient.build().get().uri(url)
				.retrieve()
				.bodyToMono(SalesDTO.class).log()// this calls the sales microservice
				.flatMap(salesDTO -> {
					var sales=salesDTO; // this collects the data so as not to get lost since this is reactive programming
					return findByItemId(id)
							.doOnNext(x->x.setSales(sales.getPrice()));
				})
				.log();

	}

	private ItemDTO copyItemEntityToDto(ItemEntity itemEntity) {
		ItemDTO itemDTO = new ItemDTO();
		BeanUtils.copyProperties(itemEntity, itemDTO);
		return itemDTO;
	}

	private ItemEntity copyItemDtoToEntity(ItemDTO itemDTO) {
		ItemEntity itemEntity = new ItemEntity();
		BeanUtils.copyProperties(itemDTO, itemEntity);
		return itemEntity;
	}

}
