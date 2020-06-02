package com.mw.enpharos.dto;

import lombok.Data;

@Data
public class TpsResDTO {
    private String  time;
    private float	tps;
    private float	res;
    
    public TpsResDTO(String time, float tps, float res) {
    	this.time = time;
    	this.tps = tps;
    	this.res = res;
    }
    
    @Override
    public String toString() {
        return "TpsResDTO{"        		
                + "time = " + time
                + ", tps=" + tps
                + ", res=" + res
                + "}";
    }
}