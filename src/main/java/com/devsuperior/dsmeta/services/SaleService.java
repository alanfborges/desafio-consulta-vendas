package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.projection.SaleMinProjection;
import com.devsuperior.dsmeta.projection.SaleSellerMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
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

    public Page<SaleMinDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate finalDate = maxDate.equals("") ? LocalDate.ofInstant(Instant.now(),
                ZoneId.systemDefault()) : LocalDate.parse(maxDate);
        LocalDate initialDate = minDate.equals("") ? finalDate.minusYears(1L) : LocalDate.parse(minDate);

        Page<SaleMinProjection> result = repository.report(initialDate, finalDate, name, pageable);
        Page<SaleMinDTO> dto = result.map(x -> new SaleMinDTO(x));
        return dto;
    }

    public List<SaleSellerMinDTO> summary(String minDate, String maxDate) {
        LocalDate finalDate = maxDate.equals("") ? LocalDate.ofInstant(Instant.now(),
                ZoneId.systemDefault()) : LocalDate.parse(maxDate);
        LocalDate initialDate = minDate.equals("") ? finalDate.minusYears(1L) : LocalDate.parse(minDate);

        List<SaleSellerMinProjection> result = repository.summary(initialDate, finalDate);
        List<SaleSellerMinDTO> dto = result.stream().map(x -> new SaleSellerMinDTO(x.getName(), x.getTotal()))
                .collect(Collectors.toList());

        for (SaleSellerMinDTO obj: dto){
            System.out.println(obj);
        }
        return dto;
    }
}
