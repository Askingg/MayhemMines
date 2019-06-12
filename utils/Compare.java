package me.askingg.mayhem.utils;

import java.util.Comparator;
import java.util.HashMap;

public class Compare implements Comparator<String> {

	HashMap<String, Double> base;

	public Compare(HashMap<String, Double> map) {
		this.base = map;
	}

	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}
