package com.cym.modules.bean;

public class Record {
	private String ID;
	
	private String STUDENT_NAME;
	
	private String RECORD_TYPE;
	
	private String RECORD_CONTENT;
	
	private String TEACHER_NAME;
	
	private String CREATE_TIME;
	
	private String UPDATE_TIME;

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Record(String iD, String sTUDENT_NAME, String rECORD_TYPE, String rECORD_CONTENT, String tEACHER_NAME,
			String cREATE_TIME, String uPDATE_TIME) {
		super();
		ID = iD;
		STUDENT_NAME = sTUDENT_NAME;
		RECORD_TYPE = rECORD_TYPE;
		RECORD_CONTENT = rECORD_CONTENT;
		TEACHER_NAME = tEACHER_NAME;
		CREATE_TIME = cREATE_TIME;
		UPDATE_TIME = uPDATE_TIME;
	}

	@Override
	public String toString() {
		return "Record [ID=" + ID + ", STUDENT_NAME=" + STUDENT_NAME + ", RECORD_TYPE=" + RECORD_TYPE
				+ ", RECORD_CONTENT=" + RECORD_CONTENT + ", TEACHER_NAME=" + TEACHER_NAME + ", CREATE_TIME="
				+ CREATE_TIME + ", UPDATE_TIME=" + UPDATE_TIME + "]";
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSTUDENT_NAME() {
		return STUDENT_NAME;
	}

	public void setSTUDENT_NAME(String sTUDENT_NAME) {
		STUDENT_NAME = sTUDENT_NAME;
	}

	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}

	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}

	public String getRECORD_CONTENT() {
		return RECORD_CONTENT;
	}

	public void setRECORD_CONTENT(String rECORD_CONTENT) {
		RECORD_CONTENT = rECORD_CONTENT;
	}

	public String getTEACHER_NAME() {
		return TEACHER_NAME;
	}

	public void setTEACHER_NAME(String tEACHER_NAME) {
		TEACHER_NAME = tEACHER_NAME;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

}