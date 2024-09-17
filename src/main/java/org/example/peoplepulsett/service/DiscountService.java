package org.example.peoplepulsett.service;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal discount(Long couponId , BigDecimal cash);
}
