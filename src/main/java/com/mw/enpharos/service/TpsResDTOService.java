package com.mw.enpharos.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import com.mw.enpharos.dto.TpsResDTO;

@Service
public class TpsResDTOService {
	
    ////////////////////////////////////////////////////
	// Table List ( pharos_mw / sktngm12! 계정 )
	////////////////////////////////////////////////////
	// BIZ_STAT_D       ( GUI + MCA + EAI )
	// BIZ_STAT_H       ( GUI + MCA + EAI )
	// BIZ_STAT_10MIN   ( GUI + MCA + EAI )
	// BIZ_STAT_1MIN    ( GUI + MCA + EAI )
	// PJ_URI_STAT_D
	// PT_CT_SVC_STAT_D ( 피연동 포함 )
	// TEMP_PEAK_TPS_D  ( biz_stat_1min		기준 Daily MAX - 전세 TPS, GUI, MCA, EAI )
	// TEMP_PT_TPS_D    ( pj_uri_stat_1min	기준 PT단 성능 내부/외부 )
	// TEMP_AP_STAT_DN  ( biz_stat_1min		기준 Swing 성능용량 보고서 작성시, 등록/조회 TP 구분하여 TPS 추출
	
	@PersistenceContext
	EntityManager em;
    
    public List<TpsResDTO> get_1hour_tpsres(String yyyymmddhh) {    	
    	// QLRM 사용 - JPaResultMApper => DTO 맵핑
    	String sql =	"SELECT TO_CHAR(TIMESLICE, 'YYYY-MM-DD HH24:MI') AS TIME\n" + 
    					"		, ROUND(SUM(COUNT)/(60*60),2) AS TPS\n" + 
    					"		, ROUND(SUM(DURATION)/SUM(COUNT)/1000000,3) AS RESP\n" + 
    					"FROM BIZ_STAT_H\n" + 
    					"WHERE CATEGORY_TYPE = '101'\n" + 
    					"  AND TIMESLICE = TO_TIMESTAMP(:value1,'YYYYMMDDHH24')\n" + 
    					"  AND TX_CODE like 'Z%_TR%'\n" + 
    					"GROUP BY TIMESLICE";
    	
    	JpaResultMapper result = new JpaResultMapper();
    	Query query = em.createNativeQuery(sql)
    			.setParameter("value1", yyyymmddhh);
    	List<TpsResDTO> list = result.list(query,  TpsResDTO.class);
    	
    	return list;
    }
    
    public List<TpsResDTO> get_1h_period_tpsres(String from, String to) {    	
    	// QLRM 사용 - JPaResultMApper => DTO 맵핑
    	String sql =	"SELECT TO_CHAR(TIMESLICE, 'YYYY-MM-DD HH24:MI') AS TIME\n" + 
    					"		, ROUND(SUM(COUNT)/(60*60),2) AS TPS\n" + 
    					"		, ROUND(SUM(DURATION)/SUM(COUNT)/1000000,3) AS RESP\n" + 
    					"FROM BIZ_STAT_H\n" + 
    					"WHERE CATEGORY_TYPE = '101'\n" + 
    					"  AND TIMESLICE BETWEEN TO_TIMESTAMP(:value1,'YYYYMMDDHH24')\n" +
    					"  AND                   TO_TIMESTAMP(:value2,'YYYYMMDDHH24')\n" +
    					"  AND TX_CODE like 'Z%_TR%'\n" + 
    					"GROUP BY TIMESLICE";
    	
    	JpaResultMapper result = new JpaResultMapper();
    	Query query = em.createNativeQuery(sql)
    			.setParameter("value1", from)
    			.setParameter("value2", to);
    	List<TpsResDTO> list = result.list(query,  TpsResDTO.class);
    	
    	return list;
    }
    
    public List<TpsResDTO> get_1min_period_tpsres(String from, String to) {    	
    	// QLRM 사용 - JPaResultMApper => DTO 맵핑
    	String sql =	"SELECT TO_CHAR(TIMESLICE, 'YYYY-MM-DD HH24:MI') AS TIME\n" + 
    					"		, ROUND(SUM(COUNT)/(60*60),2) AS TPS\n" + 
    					"		, ROUND(SUM(DURATION)/SUM(COUNT)/1000000,3) AS RESP\n" + 
    					"FROM BIZ_STAT_1MIN\n" + 
    					"WHERE CATEGORY_TYPE = '101'\n" + 
    					"  AND TIMESLICE BETWEEN TO_TIMESTAMP(:value1,'YYYYMMDDHH24MI')\n" +
    					"  AND                   TO_TIMESTAMP(:value2,'YYYYMMDDHH24MI')\n" +
    					"  AND TX_CODE like 'Z%_TR%'\n" + 
    					"GROUP BY TIMESLICE";
    	
    	JpaResultMapper result = new JpaResultMapper();
    	Query query = em.createNativeQuery(sql)
    			.setParameter("value1", from)
    			.setParameter("value2", to);
    	List<TpsResDTO> list = result.list(query,  TpsResDTO.class);
    	
    	return list;
    }
}