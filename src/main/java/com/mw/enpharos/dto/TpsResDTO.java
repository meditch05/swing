package com.mw.enpharos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TpsResDTO {
    private String  time;
    private float	tps;
    private float	res;
    
    @Override
    public String toString() {
        return "TpsResDTO{"        		
                + "time = " + time
                + ", tps=" + tps
                + ", res=" + res
                + "}";
    }
}