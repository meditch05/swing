package com.mw.enpharos.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TpsResDTO {
	private String time;
	private float tps;
	private float res;

	public TpsResDTO(String  time, BigDecimal	tps, BigDecimal	res) {
    	this.time = time;
    	this.tps = tps.floatValue();
    	this.res = res.floatValue();
    }

	@Override
	public String toString() {
		return "TpsResDTO{" + "time = " + time + ", tps=" + tps + ", res=" + res + "}";
	}
}