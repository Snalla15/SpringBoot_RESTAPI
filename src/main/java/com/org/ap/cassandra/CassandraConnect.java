package com.org.ap.cassandra;

import java.util.List;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.org.ap.entity.DocumentDetails;

@Component
public class CassandraConnect {
	private String serverIP = "127.0.0.1";
	private String keyspace = "DD";
	private Session session = null;
	private Cluster cluster = null;
	private String getDocumentDetails = "select * from dd.DocumentDetails";
	private long provIdentifier;
	private long subIdentifier;
	private String DocType;
	private long OCAN;
	private String subName;
	private String subDob;
	private long OTN;
	private long docNum;
	private boolean isExists;
	private Session cassandraSession() {
		cluster = Cluster.builder().addContactPoints(serverIP).build();
		session = cluster.connect(keyspace);
		return session;
	}
	public DocumentDetails getDocumentDetails(){
		DocumentDetails dd = new DocumentDetails();
	    Session cassandraSession = null;

		try{

			 PreparedStatement queryStmt = cassandraSession.prepare(getDocumentDetails);
				ResultSet results = cassandraSession.execute(getDocumentDetails);
			   // ResultSet result2 = session.execute(queryStmt.bind(name2));
				for (Row row : results){
					 provIdentifier = row.getInt("provIdentifier");
					 subIdentifier = row.getInt("subIdentifier");
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
		}catch(Exception e){
			System.out.println("Error occured while connecting to cassandra: "+ e.getMessage());
		}finally {
			cassandraSession.close();
		}
		return dd;
	}
}
