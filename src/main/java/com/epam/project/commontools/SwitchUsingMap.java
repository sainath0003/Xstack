package com.epam.project.commontools;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;
@Component
public class SwitchUsingMap {

	private Map<Integer, Supplier<Boolean>> switchMap = new HashMap<>();

	public boolean performSwitch(int userInput) {
		return switchMap.get(userInput).get();

	}

	public boolean addSwitchCases(int x, Supplier<Boolean> function) {

		return switchMap.put(x, function) == null;

	}

	public int getsize() {
		return switchMap.size();
	}

}
