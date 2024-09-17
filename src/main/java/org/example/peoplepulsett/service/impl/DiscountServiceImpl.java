package org.example.peoplepulsett.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.CouponRepository;
import org.example.peoplepulsett.service.DiscountService;
import org.example.peoplepulsett.util.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);
    private final CouponRepository couponRepository;
    private final Cache cache;
    private final List<Double> discountRates = Arrays.asList(0.1, 0.5, 0.6);

    @Override
    public BigDecimal discount(Long couponId, BigDecimal cash) {
        double randomDiscountRate = getRandomDiscountRate();
        String cacheKey = "discount_" + couponId + ":" + cash + ":" + randomDiscountRate;

        logger.info("Processing discount for couponId: {} and cash: {}", couponId, cash);

        if (cache.getCache().containsKey(cacheKey)) {
            logger.info("Cache hit for key: {}", cacheKey);
            return (BigDecimal) cache.getCache().get(cacheKey);
        }

        boolean isValidCoupon = couponRepository.isValidCoupon(couponId);
        if (!isValidCoupon) {
            logger.error("Invalid coupon with id: {}", couponId);
            throw new EntityNotFoundException("No such coupon " + couponId);
        }

        logger.info("Applying discount rate: {} to cash: {}", randomDiscountRate, cash);
        BigDecimal discountMultiplier = BigDecimal.valueOf(1 - randomDiscountRate);
        BigDecimal discountedCost = cash.multiply(discountMultiplier);

        logger.info("Caching discounted cash for key: {} with value: {}", cacheKey, discountedCost);
        cache.put(cacheKey, discountedCost);

        return discountedCost;
    }

    private double getRandomDiscountRate() {
        Random random = new Random();
        return discountRates.get(random.nextInt(discountRates.size()));
    }
}
