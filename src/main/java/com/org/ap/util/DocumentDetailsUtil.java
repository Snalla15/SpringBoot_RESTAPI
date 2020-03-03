package com.org.ap.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.ap.cassandra.CassandraConnect;

@Component
public class DocumentDetailsUtil {

	@Autowired
	private CassandraConnect cassandraConnect;
	
	public boolean validateHeaders(String auth, String clientID, String submitter) throws Exception{
		Map<String,String> validHeaders = new HashMap<String, String>();
		validHeaders = cassandraConnect.getConfigs();
		System.out.println("Header auth: "+ auth);
		System.out.println("DB auth: "+ validHeaders.get("auth"));
		System.out.println("Header submitter: "+ submitter);
		System.out.println("DB submitter: "+ validHeaders.get("submitter"));
		System.out.println("Header clientid: "+ clientID);
		System.out.println("DB clientID: "+ validHeaders.get("clientid"));
		if(validHeaders.get("auth").equalsIgnoreCase(auth) && validHeaders.get("submitter").equalsIgnoreCase(submitter)
				&& validHeaders.get("clientid").equalsIgnoreCase(clientID)){
			return true;
		}else{
			return false;
		}	
	}
}
