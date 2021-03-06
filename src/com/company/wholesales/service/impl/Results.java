package com.company.wholesales.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jal.wholesales.model.AbstractValueObject;

public class Results<T> extends AbstractValueObject {

	private List<T> data = null;
	private int total = 0;

	public Results() {
		this.data = new ArrayList<T>();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}