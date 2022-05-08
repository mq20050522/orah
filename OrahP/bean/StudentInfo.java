package com.cym.modules.bean;

public class StudentInfo {
	private String f1;
	
	private String names;
	
	private String grades;
	
	private String pfp;

	@Override
	public String toString() {
		return "StudentInfo [f1=" + f1 + ", names=" + names + ", grades=" + grades + ", pfp=" + pfp + "]";
	}

	public String getF1() {
		return f1;
	}

	public StudentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentInfo(String f1, String names, String grades, String pfp) {
		super();
		this.f1 = f1;
		this.names = names;
		this.grades = grades;
		this.pfp = pfp;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getPfp() {
		return pfp;
	}

	public void setPfp(String pfp) {
		this.pfp = pfp;
	}
}
