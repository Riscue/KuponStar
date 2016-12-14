package com.riscue.kuponstar.model;

public class Detail {
	private String name;
	private String value;

	public Detail(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (name != null ? name.hashCode() : 0);
		hash += (value != null ? value.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Detail)) {
			return false;
		}
		Detail other = (Detail) object;
		if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name)))
			return false;

		if ((this.value == null && other.value != null) || (this.value != null && !this.value.equals(other.value)))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return name + ": " + value;
	}
}
