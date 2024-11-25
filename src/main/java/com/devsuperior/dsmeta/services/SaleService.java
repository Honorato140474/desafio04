package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSumProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findAll(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate dtInicio;
		LocalDate dtFim;

		if (maxDate.isEmpty()) {
			maxDate = LocalDate.now().toString();
			dtFim = LocalDate.parse(maxDate);
		} else {
			dtFim = LocalDate.parse(maxDate);
		}

		if (minDate.isEmpty()) {
			dtInicio = dtFim.minusYears(1L);
		} else {
			dtInicio = LocalDate.parse(minDate);
		}

		Page<Sale> result = repository.search(dtInicio, dtFim, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	public List<SaleSumDTO> findAllSumary(String minDate, String maxDate, String name) {

		LocalDate dtInicio;
		LocalDate dtFim;

		if (maxDate.isEmpty()) {
			maxDate = LocalDate.now().toString();
			dtFim = LocalDate.parse(maxDate);
		} else {
			dtFim = LocalDate.parse(maxDate);
		}

		if (minDate.isEmpty()) {
			dtInicio = dtFim.minusYears(1L);
		} else {
			dtInicio = LocalDate.parse(minDate);
		}

		List<SaleSumProjection> list = repository.search2(dtInicio, dtFim, name);
		List<SaleSumDTO> result = list.stream().map(x -> new SaleSumDTO(x)).collect(Collectors.toList());

		return result;

	}
	
	public List<SaleSumDTO> findAllSumaryJPQL(String minDate, String maxDate, String name) {

		LocalDate dtInicio;
		LocalDate dtFim;

		if (maxDate.isEmpty()) {
			maxDate = LocalDate.now().toString();
			dtFim = LocalDate.parse(maxDate);
		} else {
			dtFim = LocalDate.parse(maxDate);
		}

		if (minDate.isEmpty()) {
			dtInicio = dtFim.minusYears(1L);
		} else {
			dtInicio = LocalDate.parse(minDate);
		}
		
		
		List<SaleSumDTO> list = repository.search3(dtInicio,dtFim,name);
		

		return list;

	}

}
