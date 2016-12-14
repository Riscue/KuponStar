package com.riscue.kuponstar.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {
	private static final long serialVersionUID = 5302209210187230625L;

	private String status = "SUCCESS";
	private ArrayList<Detail> details;

	public Response addError(String err) {
		System.out.println(err);

		if (details == null)
			details = new ArrayList<Detail>();

		details.add(new Detail("error", err));
		status = "FAIL";
		return this;
	}

	public Response addDetail(String name, String value) {
		Detail detail = new Detail(name, value);
		System.out.println(detail);

		if (details == null)
			details = new ArrayList<Detail>();

		details.add(detail);
		return this;
	}

	public Response addDetail(Detail detail) {
		System.out.println(detail);

		if (details == null)
			details = new ArrayList<Detail>();

		details.add(detail);
		return this;
	}

	public String getStatus() {
		return status;
	}

	public ArrayList<Detail> getDetails() {
		return details;
	}
}
