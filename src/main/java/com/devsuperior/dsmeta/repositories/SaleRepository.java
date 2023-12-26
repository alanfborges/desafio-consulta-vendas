package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.SaleMinProjection;
import com.devsuperior.dsmeta.projection.SaleSellerMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "Select tb_sales.id as id, tb_sales.date as date, tb_sales.amount as amount, tb_seller.name as name " +
            "From tb_sales " +
            "INNER JOIN tb_seller " +
            "ON tb_seller.id = tb_sales.seller_id " +
            "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
            "AND tb_seller.name LIKE CONCAT('%', :name, '%') " +
            "GROUP BY tb_sales.id " +
            "ORDER BY SUM(tb_sales.amount)")
    Page<SaleMinProjection> report(@Param("minDate")LocalDate minDate,
                                    @Param("maxDate")LocalDate maxDate,
                                    @Param("name") String name, Pageable pageable);


    @Query("SELECT v.id as id, v.date as date, v.amount as amount, v.seller.name as name " +
            "FROM Sale v " +
            "WHERE v.date >= :dataInicio AND v.date <= :dataFim " +
            "AND v.seller.name LIKE CONCAT('%', :name, '%')" +
            "ORDER BY SUM(v.amount)")
    Page<SaleMinProjection> report2(@Param("dataInicio")LocalDate dataInicio,
                                   @Param("dataFim")LocalDate dataFim,
                                   @Param("name") String name, Pageable pageable);

    @Query(nativeQuery = true,value = "Select SUM(tb_sales.amount) as total, tb_seller.name as name " +
            "From tb_sales " +
            "INNER JOIN tb_seller " +
            "ON tb_seller.id = tb_sales.seller_id " +
            "WHERE tb_sales.date BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY tb_seller.name " +
            "ORDER BY tb_seller.name")
    List<SaleSellerMinProjection> summary(@Param("dataInicio")LocalDate dataInicio,
                                         @Param("dataFim")LocalDate dataFim);

}
