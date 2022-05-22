package com.example.sales.service;

import javax.transaction.Transactional;

import com.example.sales.common.exceptions.RecordNotFoundException;
import com.example.sales.common.messages.BaseResponse;
import com.example.sales.common.messages.CustomMessage;
import com.example.sales.dto.SalesDTO;
import com.example.sales.entity.SalesEntity;
import com.example.sales.repo.SalesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sales.common.utils.Topic;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SalesService {

	@Autowired
	private SalesRepo salesRepo;

	public Flux<SalesDTO> findSalesList() {
		return salesRepo.findAll().map(this::copySalesEntityToDto);
	}

	public Mono<SalesDTO> findBySalesId(Long salesId) {
		return salesRepo.findById(salesId)
				.switchIfEmpty(Mono.error( new RecordNotFoundException("Sales id '" + salesId + "' does not exist !")))
				.map(this::copySalesEntityToDto).log();
	}

	public Mono<BaseResponse> createOrUpdateSales(SalesDTO salesDTO) {
		SalesEntity salesEntity = copySalesDtoToEntity(salesDTO);
		salesRepo.save(salesEntity);
		return Mono.just(salesDTO)
				.map(this::copySalesDtoToEntity)
				.flatMap(salesRepo::save)
				.map(m->new BaseResponse(Topic.Sales.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value()));

	}

	public Mono<Void> updateSales(SalesDTO salesDTO) {
		return Mono.just(salesDTO).map(this::copySalesDtoToEntity).map(salesRepo::save).then();
	}

	public Mono<BaseResponse> deleteSales(Long salesId) {
		return salesRepo.findById(salesId)
				.switchIfEmpty(Mono.error(new RecordNotFoundException("No record found for given id: " + salesId)))
				.flatMap(salesRepo::delete)
				.map(m->new BaseResponse(Topic.Sales.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE, HttpStatus.OK.value()));
	}


	private SalesDTO copySalesEntityToDto(SalesEntity salesEntity) {
		SalesDTO salesDTO = new SalesDTO();
		BeanUtils.copyProperties(salesEntity, salesDTO);
		return salesDTO;
	}

	private SalesEntity copySalesDtoToEntity(SalesDTO salesDTO) {
		SalesEntity userEntity = new SalesEntity();
		BeanUtils.copyProperties(salesDTO, userEntity);
		return userEntity;
	}

}
