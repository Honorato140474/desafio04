package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSumProjection;


public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller "
			+ " WHERE UPPER(obj.seller.name) "
			+ " LIKE UPPER(CONCAT('%', :name, '%')) "
			+ " AND obj.date BETWEEN :minDate AND :maxDate",
			countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
	public Page<Sale> search (LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT TB_SELLER.NAME, SUM(TB_SALES.AMOUNT) as SOMA "
			+ "FROM TB_SALES "
			+ "INNER JOIN TB_SELLER ON TB_SELLER.ID = TB_SALES.SELLER_ID "
			+ "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(TB_SELLER.NAME) LIKE UPPER(CONCAT('%', :name, '%')) "
			+ "GROUP BY TB_SELLER.NAME ")
	public List<SaleSumProjection> search2(LocalDate minDate, LocalDate maxDate, String name); 
	
	
	
	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(obj.seller.name, SUM(obj.amount)) "
			+ " FROM Sale obj "
			+ " WHERE (obj.date BETWEEN :minDate AND :maxDate) "
			+ " AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) "
			+ " GROUP BY obj.seller.name")
	public List<SaleSumDTO> search3 (LocalDate minDate, LocalDate maxDate, String name);
	

	
//	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(obj.seller.name, SUM(obj.amount)) "
//			+ " FROM Sale obj "
//			+ " WHERE (obj.date BETWEEN :minDate AND :maxDate) "
//			+ " GROUP BY obj.seller.name")
//	public List<SaleSumDTO> search3 (LocalDate minDate, LocalDate maxDate);
	

}
