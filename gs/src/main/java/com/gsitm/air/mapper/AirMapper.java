package com.gsitm.air.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.gsitm.air.vo.AirArrivalsVO;
import com.gsitm.air.vo.AirDeparturesVO;
import com.gsitm.air.vo.SerchVO;
import com.gsitm.air.vo.StatsByTimelineVO;
import com.gsitm.air.vo.TimeStatVO;
import com.gsitm.air.vo.UserVO;

public interface AirMapper {
	void AirDeparturesAdd(AirDeparturesVO vo);
	void AirArrivalsAdd(AirArrivalsVO vo);
	void StatsByTimelineAdd(StatsByTimelineVO vo);
	List<AirDeparturesVO> AirDeparturesList();
	List<AirDeparturesVO> AirDeparturesListSerch(SerchVO vo);
	List<AirArrivalsVO> AirArriveListSerch(SerchVO vo);
	List<StatsByTimelineVO> statsByTimeline();
	List<HashMap<String, String>> terminalList();
	List<HashMap<String, String>> airportList();
	List<HashMap<String, String>> airlineList();
	List<HashMap<String, String>> exitnumberList();
	TimeStatVO timeStat();
	UserVO loadUserByUsername(String username);
}
