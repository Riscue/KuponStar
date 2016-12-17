package com.riscue.kuponstar.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riscue.kuponstar.entity.Coupon;
import com.riscue.kuponstar.entity.Match;
import com.riscue.kuponstar.entity.User;
import com.riscue.kuponstar.model.PlayCouponParams;
import com.riscue.kuponstar.model.Response;
import com.riscue.kuponstar.service.KuponstarService;

@Controller
@RequestMapping(path = "/api")
public class KuponstarController {

	public static final Double DEFAULT_BALANCE = 1000.0;
	@Autowired
	KuponstarService service;

	private User getUser(User user) {
		User dbuser = service.getUserByUsername(user.getUsername());
		if (dbuser != null && user.getHash().equals(dbuser.getHash()))
			return dbuser;
		return null;
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Response signup(@RequestBody User user) {
		Response response = new Response();
		if (user.getUsername() == null || user.getUsername() == "") {
			response.addError("Username can't be empty!");
		}
		if (user.getMail() == null || user.getMail() == "") {
			response.addError("Mail can't be empty!");
		}
		if (user.getName() == null || user.getName() == "") {
			response.addError("Name can't be empty!");
		}
		if (user.getSurname() == null || user.getSurname() == "") {
			response.addError("Surname can't be empty!");
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			response.addError("Password can't be empty!");
		}

		if (response.getStatus() == "SUCCESS") {
			if (service.getUserByUsername(user.getUsername()) != null) {
				response.addError("Username already exist!");
			} else if (service.getUserByMail(user.getMail()) != null) {
				response.addError("Mail already exist!");
			} else {
				user.setTotalcoupon(0);
				user.setSuccesscoupon(0);
				user.setFailcoupon(0);
				user.setHash("");
				user.setBalance(KuponstarController.DEFAULT_BALANCE);
				user.setLastlogin(null);
				user.setSignup(new Timestamp(new Date().getTime()));

				service.saveUser(user);
			}
		}
		return response;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody User login(@RequestBody User user) {
		Response response = new Response();
		if (user.getUsername() == null || user.getUsername() == "") {
			response.addError("Username can't be empty!");
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			response.addError("Password can't be empty!");
		}

		if (response.getStatus() == "FAIL")
			return null;

		User dbuser = service.getUserByUsername(user.getUsername());
		if (dbuser == null)
			return null;

		if (!dbuser.getPassword().equals(user.getPassword()))
			return null;

		user = dbuser;
		user.setLastlogin(new Timestamp(new Date().getTime()));
		user.setHash(Utils.MD5(user.getUsername() + new Date().getTime()));
		service.saveUser(user);
		return user;
	}

	@RequestMapping(path = "/getfixture", produces = "application/json")
	public @ResponseBody List<Match> getFixture() {
		return service.getFixture();
	}

	@RequestMapping(path = "/addmatches", produces = "application/json")
	public @ResponseBody Response addMatches() {
		Match m = new Match();
		m.setCode(144);
		m.setHome("Fenerbahçe");
		m.setGuest("Galatasaray");
		m.setMS1("1.45");
		m.setMS0("2.20");
		m.setMS2("3.00");
		service.saveMatch(m);

		Match m2 = new Match();
		m2.setCode(145);
		m2.setHome("Asd");
		m2.setGuest("Qwe");
		m2.setMS1("3.45");
		m2.setMS0("2.20");
		m2.setMS2("1.50");
		service.saveMatch(m2);
		return new Response();
	}

	@RequestMapping(path = "/getcoupons", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<Coupon> getCoupon(@RequestBody User u) {
		User user = getUser(u);
		if (user == null)
			return null;
		return service.getCoupons(user.getId());
	}

	@RequestMapping(path = "/playcoupon", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Response playCoupon(@RequestBody PlayCouponParams param) {
		User user = getUser(param.getUser());
		if (user == null)
			return null;

		service.saveCoupon(reArrangeCoupon(user, param.getCoupon()));
		return new Response();
	}

	private Coupon reArrangeCoupon(User user, Coupon coupon) {
		coupon.setUserId(user.getId());
		coupon.setMaxRatio(1.0);
		coupon.setTarih(new Timestamp(new Date().getTime()));

		Match[] matches = coupon.getMatches();
		for (int i = 0; i < matches.length; i++) {
			String ratio = "";
			Match match = service.getMatch(matches[i].getCode());
			if (matches[i].getMS0() != null) {
				ratio = match.getMS0();
				matches[i].setMS0(ratio);
			} else if (matches[i].getMS1() != null) {
				ratio = match.getMS1();
				matches[i].setMS1(ratio);
			} else if (matches[i].getMS2() != null) {
				ratio = match.getMS2();
				matches[i].setMS2(ratio);
			}
			matches[i].setHome(match.getHome());
			matches[i].setGuest(match.getGuest());
			coupon.setMaxRatio(coupon.getMaxRatio() * Double.parseDouble(ratio));
		}
		coupon.setTotalOdds(coupon.getMaxRatio() * coupon.getMaxOdds());
		return coupon;
	}
}
