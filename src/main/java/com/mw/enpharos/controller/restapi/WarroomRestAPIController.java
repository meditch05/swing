package com.mw.enpharos.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mw.enpharos.dto.TpsResDTO;
import com.mw.enpharos.entity.BIZ_STAT_H;
import com.mw.enpharos.repository.BIZ_STAT_H_Mapper;
import com.mw.enpharos.service.TpsResDTOService;

import oracle.sql.TIMESTAMP;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

@RestController
@RequestMapping("/api")
public class WarroomRestAPIController {
    
    @Autowired
    private TpsResDTOService tpsresdtoservice;
    
    // BIZ_STAT_H 테이블 ( 1시간 )
 	@RequestMapping(value="/get/swing/min/tps_res/period",
 					method= RequestMethod.GET,
 					produces = "application/json;application/text;charset=utf-8")
 	public List<TpsResDTO> get_1min_tpsres_period(
 			@RequestParam(value="from") String from,
 			@RequestParam(value="to")   String to) {
 		
 		List<TpsResDTO> list = null;
 	
 		System.out.println("/get/swing/min/tps_res/period = from = " + from );
 		System.out.println("/get/swing/min/tps_res/period = to   = " + to );
 	
 		try {
 			/*
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
 			Date date = sdf.parse(yyyymmddhh);	
 			Timestamp timestamp = new Timestamp(date.getTime());
 			
 			System.out.println("date.toString() = " + date.toString());	
 			System.out.println("timestamp.toString() = " + timestamp.toString());
 			 */
 	        
 			list = tpsresdtoservice.get_1min_period_tpsres(from, to);
 			for (TpsResDTO dto : list ) {
 				dto.toString();
 			}
 			return list;
 		} catch(Exception io) {
 			System.out.println(io.toString());			
 			return list;
 		}
 	}
}
