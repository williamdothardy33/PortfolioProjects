package com.techelevator.models;

import java.util.List;

public interface Loader <T> {
	public List<T> load(String fileName);
}
