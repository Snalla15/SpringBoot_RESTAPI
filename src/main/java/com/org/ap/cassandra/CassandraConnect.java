package com.org.ap.cassandra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.org.ap.entity.DocumentDetailsResponse;

@Component
public class CassandraConnect {
	private String serverIP = "127.0.0.1";
	private Session session = null;
	private Cluster cluster = null;
	private String getDocumentDetails = "select * from dd.DocumentDetails";
	private String getConfigs = "Select auth, submitter, client_id from dd.configuration";
	private String createDocDetails = "insert into dd.documentdetails (ocan,otn,docnum,subname,"
										+ "doctype,providentifier,subdob,subidentifie) values(?,?,?,?,?,?,?,?)";
	private long provIdentifier;
	private long subIdentifier;
	private String DocType;
	private long OCAN;
	private String subName;
	private String subDob;
	private long OTN;
	private long docNum;
	private boolean isExists;

	private Session cassandraSession() throws Exception {
		try{
		cluster = Cluster.builder().addContactPoints(serverIP)
				.withoutJMXReporting().build();
		session = cluster.connect();
		}catch(Exception e){
			System.out.println("Error occured while creating Cassandra connection: "+ e.getMessage());
			throw new Exception(e);
		}
		return session;
	}

	public DocumentDetailsResponse getDocumentDetails() throws Exception {
		DocumentDetailsResponse dd = new DocumentDetailsResponse();
		Session cassandraSession = null;

		try {
			cassandraSession = cassandraSession();
			SimpleStatement statement = new SimpleStatement(getDocumentDetails);
			ResultSet results = cassandraSession.execute(statement);
			for (Row row : results) {
				provIdentifier = row.getInt("provIdentifier");
				subIdentifier = row.getInt("subIdentifie");
				DocType = row.getString("DocType");
				OCAN = row.getInt("OCAN");
				subName = row.getString("subName");
				subDob = row.getString("subDob");
				OTN = row.getInt("OTN");
				docNum = row.getInt("docNum");
				dd.setDocNum(docNum);
				dd.setDocType(DocType);
				dd.setOCAN(OCAN);
				dd.setOTN(OTN);
				dd.setProvIdentifier(provIdentifier);
				dd.setSubDob(subDob);
				dd.setSubIdentifier(subIdentifier);
				dd.setSubName(subName);
			}
		} catch (Exception e) {
			System.out.println("Error occured while retriving document details: "
					+ e.getMessage());
			throw new Exception(e);
		} finally {
			if(cluster!=null ){
				cluster.close();
				}
			if(cassandraSession!=null ){
			cassandraSession.close();
			}
		}
		return dd;
	}
	public Map<String,String> getConfigs() throws Exception{
		Session cassandraSession = null;
		Map<String,String> configs = new HashMap<String, String>();
		try {
			cassandraSession = cassandraSession();
			SimpleStatement statement = new SimpleStatement(getConfigs);
			ResultSet results = cassandraSession.execute(statement);
			for (Row row : results) {
				configs.put("auth", row.getString("auth"));
				configs.put("submitter", row.getString("submitter"));
				configs.put("clientid", row.getString("client_id"));
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting configs: "
					+ e.getMessage());
			throw new Exception(e);
		} finally {
			if(cluster!=null ){
				cluster.close();
				}
			if(cassandraSession!=null ){
			cassandraSession.close();
			}
		}
		return configs;
		
	}
	public void createDocumentDetails(int ocan, int otn, int docnum, String subname,
			String doctype,int providentifier,String subdob, int subidentifier) throws Exception{
		Session cassandraSession = null;
		try {
			cassandraSession = cassandraSession();
			PreparedStatement queryStmt = cassandraSession.prepare(createDocDetails);
			cassandraSession.execute(queryStmt.bind(ocan,otn,docnum,subname,
					doctype,providentifier,subdob,subidentifier));
			
		} catch (Exception e) {
			System.out.println("Error occured while creating document details: "
					+ e.getMessage());
			throw new Exception(e);
		} finally {
			if(cluster!=null ){
				cluster.close();
				}
			if(cassandraSession!=null ){
			cassandraSession.close();
			}
		}	
	}
}
