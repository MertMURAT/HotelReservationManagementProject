package com.example.roombookingapp.command;

import java.sql.Date;

public class SearchForm {
	
	private String startDate="";
	private String endDate="";
	private Long participants;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getParticipants() {
		return participants;
	}
	public void setParticipants(Long participants) {
		this.participants = participants;
	}
	

	

}
