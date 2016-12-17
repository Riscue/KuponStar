package com.riscue.kuponstar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.riscue.kuponstar.entity.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	List<Coupon> findAllByUserId(Long userId);

}
