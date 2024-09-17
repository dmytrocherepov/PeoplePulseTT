package org.example.peoplepulsett.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.service.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/check/{couponId}")
    public BigDecimal checkCoupon(
            @PathVariable @Positive Long couponId,
            @RequestParam @Positive BigDecimal disc) {
        return discountService.discount(couponId, disc);
    }
}
