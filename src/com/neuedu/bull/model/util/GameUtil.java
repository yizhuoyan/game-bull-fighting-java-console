package com.neuedu.bull.model.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameUtil {

	public static int randomAB(int a, int b) {
		return (int) (Math.floor(Math.random() * Math.abs(a - b)) + Math.min(a, b));
	}

	public static String prompt(String tip) {
		try {
			System.out.println(tip);
			return new BufferedReader(new InputStreamReader(System.in, "utf-8")).readLine();
		} catch (Exception e) {
			return null;
		}
	}

	public static int promptInt(String tip) {
		do {
			try {
				String input = prompt(tip);
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("请输入整数");
			}
		} while (true);
	}
}
