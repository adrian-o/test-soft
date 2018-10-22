package br.com.softplan.test.custotransporte.utils;

import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

public class NumberUtils {
	
	public static BigDecimal novoBigDecimal(Integer integer) {
		return (new BigDecimal(integer)).setScale(2);
	}
	
	public static BigDecimal adicionar(BigDecimal value1, BigDecimal value2) {
		return value1.setScale(2, HALF_DOWN).add(value2.setScale(2, HALF_DOWN));
	}
	
	public static BigDecimal multiplicar(BigDecimal value1, BigDecimal value2) {
		return value1.setScale(2, HALF_DOWN).multiply(value2.setScale(2, HALF_DOWN));
	}
	
}
