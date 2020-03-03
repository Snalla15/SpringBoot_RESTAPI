package com.org.ap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.ap.cassandra.CassandraConnect;
import com.org.ap.entity.DocumentDetails;

@RestController
public class DDController {

	@Autowired
	private CassandraConnect cassandraConnect;
	
	@GetMapping("/getDocumentDetails")
	@ResponseBody
	public ResponseEntity<DocumentDetails> getDocumentDetails(){
		
		DocumentDetails dd = cassandraConnect.getDocumentDetails();
		return new ResponseEntity<DocumentDetails>(dd, HttpStatus.OK);
	}
}
