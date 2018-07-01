package com.gsitm.air.vo;

public class AirDeparturesVO {
	private String airline; //항공사
	private String airport; //도착지공항
	private String airportCode; //도착지공항코드
	private String chkinrange; //체크인카운터
	private String estimatedDateTime; //출발변경시간
	private String flightId; //항공편명
	private String gatenumber;	//탑승구번호
	private String remark; //현황
	private String scheduleDateTime; //출발 예정 시간
	private String terminalId; //터미널 번호
	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getChkinrange() {
		return chkinrange;
	}
	public void setChkinrange(String chkinrange) {
		this.chkinrange = chkinrange;
	}
	public String getEstimatedDateTime() {
		return estimatedDateTime;
	}
	public void setEstimatedDateTime(String estimatedDateTime) {
		this.estimatedDateTime = estimatedDateTime;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getGatenumber() {
		return gatenumber;
	}
	public void setGatenumber(String gatenumber) {
		this.gatenumber = gatenumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
}
