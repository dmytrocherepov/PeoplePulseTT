package org.example.peoplepulsett.repository;

import org.example.peoplepulsett.model.Coupon;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CouponRepository {
    private final Map<Long, Coupon> coupons = new HashMap<>();;


    public CouponRepository() {
        coupons.put(1L , new Coupon(1L));
        coupons.put(2L , new Coupon(2L));
        coupons.put(5L , new Coupon(5L));
    }

    public boolean isValidCoupon(Long id) {
        for (Coupon coupon : coupons.values()) {
            if (coupon.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
