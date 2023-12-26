package com.devsuperior.dsmeta.projection;

import java.time.LocalDate;

public interface SaleMinProjection {
    Long getId();
    Double getAmount();
    LocalDate getDate();
    String getName();
}
