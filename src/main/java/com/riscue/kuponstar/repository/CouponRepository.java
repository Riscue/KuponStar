package com.riscue.kuponstar.repository;

import org.springframework.data.repository.CrudRepository;

import com.riscue.kuponstar.entity.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

}
