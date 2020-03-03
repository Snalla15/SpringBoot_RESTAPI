package com.org.ap.entity;
import org.springframework.stereotype.Component;

@Component
public class DocumentDetailsResponse {

	private long provIdentifier;
	private long subIdentifier;
	private String DocType;
	private long OCAN;
	private String subName;
	private String subDob;
	private long OTN;
	private long docNum;
	private boolean isExists;
	
	public DocumentDetailsResponse() {
		super();
	}


	public DocumentDetailsResponse(long provIdentifier, long subIdentifier,
			String docType, long oCAN, String subName, String subDob, long oTN,
			long docNum, boolean isExists) {
		super();
		this.provIdentifier = provIdentifier;
		this.subIdentifier = subIdentifier;
		DocType = docType;
		OCAN = oCAN;
		this.subName = subName;
		this.subDob = subDob;
		OTN = oTN;
		this.docNum = docNum;
		this.isExists = isExists;
	}
	
	public long getProvIdentifier() {
		return provIdentifier;
	}


	public void setProvIdentifier(long provIdentifier) {
		this.provIdentifier = provIdentifier;
	}


	public long getSubIdentifier() {
		return subIdentifier;
	}


	public void setSubIdentifier(long subIdentifier) {
		this.subIdentifier = subIdentifier;
	}


	public String getDocType() {
		return DocType;
	}


	public void setDocType(String docType) {
		DocType = docType;
	}


	public long getOCAN() {
		return OCAN;
	}


	public void setOCAN(long oCAN) {
		OCAN = oCAN;
	}


	public String getSubName() {
		return subName;
	}


	public void setSubName(String subName) {
		this.subName = subName;
	}


	public String getSubDob() {
		return subDob;
	}


	public void setSubDob(String subDob) {
		this.subDob = subDob;
	}


	public long getOTN() {
		return OTN;
	}


	public void setOTN(long oTN) {
		OTN = oTN;
	}


	public long getDocNum() {
		return docNum;
	}


	public void setDocNum(long docNum) {
		this.docNum = docNum;
	}


	public boolean isExists() {
		return isExists;
	}


	public void setExists(boolean isExists) {
		this.isExists = isExists;
	}

	
}
