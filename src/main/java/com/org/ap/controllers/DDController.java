package com.org.ap.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.commons.lang3.RandomStringUtils;

import com.org.ap.cassandra.CassandraConnect;
import com.org.ap.entity.DocumentDetailsRequest;
import com.org.ap.entity.DocumentDetailsResponse;
import com.org.ap.util.DocumentDetailsUtil;

@RestController
public class DDController {

	@Autowired
	private CassandraConnect cassandraConnect;
	@Autowired
	private DocumentDetailsUtil util;
	
	
	@GetMapping("/getDocumentDetails")
	@ResponseBody
	public ResponseEntity<DocumentDetailsResponse> getDocumentDetails(@RequestHeader HttpHeaders headers) throws Exception{
		boolean validatedHeders =
				util.validateHeaders(headers.getFirst("Authorization"), headers.getFirst("ClientId"), headers.getFirst("Submitter"));
		if(validatedHeders){
		DocumentDetailsResponse dd = cassandraConnect.getDocumentDetails();
		return ResponseEntity.ok().body(dd);
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
	@PostMapping("/createDocumentDetails")
	@ResponseBody
	public ResponseEntity<DocumentDetailsResponse> createDocumentDetails(@RequestBody DocumentDetailsRequest request,
			@RequestHeader HttpHeaders headers) throws Exception{
		String response = null;
		//validate headers
		DocumentDetailsResponse ddResponse = new DocumentDetailsResponse();
		boolean validatedHeders =
		util.validateHeaders(headers.getFirst("Authorization"), headers.getFirst("ClientId"), headers.getFirst("Submitter"));
		if(validatedHeders){
			int otn = Integer.parseInt(RandomStringUtils.randomNumeric(8));
			int docNum = (int)request.getOCAN();
			cassandraConnect.createDocumentDetails((int)request.getOCAN(), otn, docNum,
					request.getSubName(), request.getDocType(), (int)request.getProvIdentifier(), 
					request.getSubDob(), (int)request.getSubIdentifier());
			URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(ddResponse.getOTN())
                    .toUri();	
			ddResponse.setOTN(otn);
			ddResponse.setDocNum(docNum);
			ddResponse.setDocType(request.getDocType());
			ddResponse.setOCAN(request.getOCAN());
			ddResponse.setProvIdentifier(request.getProvIdentifier());
			ddResponse.setSubIdentifier(request.getSubIdentifier());
			ddResponse.setSubDob(request.getSubDob());
			ddResponse.setSubName(request.getSubName());
			ddResponse.setExists(false);		
			return ResponseEntity.created(location).body(ddResponse);
		}else {
			
		return ResponseEntity.badRequest().build();
		}
	}
}
