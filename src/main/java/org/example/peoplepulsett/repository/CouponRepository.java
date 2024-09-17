package org.example.peoplepulsett.repository;

import org.example.peoplepulsett.model.Coupon;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CouponRepository {
    private Set<Long> coupons;


    public CouponRepository() {
        coupons = new HashSet<>();
        coupons.add(1L);
        coupons.add(2L);
        coupons.add(5L);
    }

    public boolean isValidCoupon(Long id) {
        return coupons.contains(id);
    }
}
