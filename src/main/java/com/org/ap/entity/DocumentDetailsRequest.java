package com.org.ap.entity;

public class DocumentDetailsRequest {

	private long provIdentifier;
	private long subIdentifier;
	private String DocType;
	private long OCAN;
	private String subName;
	private String subDob;
	
	public DocumentDetailsRequest() {
		super();
	}
	public DocumentDetailsRequest(long provIdentifier, long subIdentifier,
			String docType, long oCAN, String subName, String subDob) {
		super();
		this.provIdentifier = provIdentifier;
		this.subIdentifier = subIdentifier;
		DocType = docType;
		OCAN = oCAN;
		this.subName = subName;
		this.subDob = subDob;
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
}
