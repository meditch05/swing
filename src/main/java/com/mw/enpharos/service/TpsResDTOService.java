package com.mw.enpharos.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import com.mw.enpharos.dto.TpsResDTO;

import lombok.Getter;
import lombok.Setter;

@Service
public class TpsResDTOService {
	
	@PersistenceContext
	EntityManager em;
    
    public List<TpsResDTO> getList(String yyyymmddhh) {    	
    	// QLRM 사용 - JPaResultMApper => DTO 맵핑
    	String sql =	"SELECT TO_CHAR(TIMESLICE, 'YYYY-MM-DD HH24:MI') AS TIME\n" + 
    					"		, ROUND(SUM(COUNT)/(60*60),2) AS TPS\n" + 
    					"		, ROUND(SUM(DURATION)/SUM(COUNT)/1000000,3) AS RESP\n" + 
    					"FROM BIZ_STAT_H\n" + 
    					"WHERE CATEGORY_TYPE = '101'\n" + 
    					"  AND TIMESLICE = TO_TIMESTAMP(:value1,'YYYYMMDDHH')\n" + 
    					"  AND TX_CODE like 'Z%_TR%'\n" + 
    					"GROUP BY TIMESLICE";
    	
    	JpaResultMapper result = new JpaResultMapper();
    	Query query = em.createNativeQuery(sql)
    			.setParameter("value1", yyyymmddhh);
    	List<TpsResDTO> list = result.list(query,  TpsResDTO.class);
    	
    	return list;
    }
}