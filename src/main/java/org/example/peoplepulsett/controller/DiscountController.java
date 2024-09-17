package org.example.peoplepulsett.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.service.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Discount management", description = "endpoint for discount")
@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/{couponId}")
    @Operation(
            summary = "Feature to get a discount ",
            description = "Gets a discount if coupon is valid"
    )
    public BigDecimal checkCoupon(
            @PathVariable @Positive Long couponId,
            @RequestParam @Positive BigDecimal cash) {
        return discountService.discount(couponId, cash);
    }
}
