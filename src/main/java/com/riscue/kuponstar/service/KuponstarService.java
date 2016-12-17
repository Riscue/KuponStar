package com.riscue.kuponstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riscue.kuponstar.entity.Coupon;
import com.riscue.kuponstar.entity.Match;
import com.riscue.kuponstar.entity.User;
import com.riscue.kuponstar.repository.CouponRepository;
import com.riscue.kuponstar.repository.MatchRepository;
import com.riscue.kuponstar.repository.UserRepository;

@Service
public class KuponstarService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MatchRepository matchRepository;
	@Autowired
	CouponRepository couponRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public List<User> getUserList() {
		return (List<User>) userRepository.findAll();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User getUserByUsername(String username) {
		List<User> list = userRepository.findUserByUsername(username);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public User getUserByMail(String mail) {
		List<User> list = userRepository.findUserByMail(mail);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public List<Match> getFixture() {
		return (List<Match>) matchRepository.findAll();
	}

	public Match getMatch(int code) {
		return matchRepository.findMatchByCode(code);
	}
	

	public void saveCoupon(Coupon coupon) {
		couponRepository.save(coupon);
	}

	public void saveMatch(Match m) {
		matchRepository.save(m);
	}

	public List<Coupon> getCoupons(Long userId) {
		return (List<Coupon>) couponRepository.findAllByUserId(userId);
	}
}