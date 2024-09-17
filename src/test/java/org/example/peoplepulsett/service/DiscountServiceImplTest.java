package org.example.peoplepulsett.service;

import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.CouponRepository;
import org.example.peoplepulsett.service.impl.DiscountServiceImpl;
import org.example.peoplepulsett.util.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @Mock
    private Cache cache;

    @InjectMocks
    private DiscountServiceImpl discountService;

    private final List<Double> discountRates = Arrays.asList(0.1, 0.5, 0.6);

    @Test
    public void discount_ValidCoupon_ShouldApplyDiscount() {
        Long couponId = 1L;
        BigDecimal cost = BigDecimal.valueOf(100);

        when(couponRepository.isValidCoupon(couponId)).thenReturn(true);
        when(cache.getCache()).thenReturn(new HashMap<>());

        BigDecimal discountedCost = discountService.discount(couponId, cost);

        assertNotNull(discountedCost);
        assertTrue(discountedCost.compareTo(cost) < 0);
    }

    @Test
    void testDiscountWithInvalidCoupon() {
        Long couponId = 2L;
        BigDecimal cost = BigDecimal.valueOf(100);

        when(couponRepository.isValidCoupon(couponId)).thenReturn(false);

        assertThatThrownBy(() -> discountService.discount(couponId, cost))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("No such coupon " + couponId);
    }
}
