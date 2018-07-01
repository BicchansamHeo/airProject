package com.gsitm.air.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gsitm.air.dao.AirDao;
import com.gsitm.air.vo.AirArrivalsVO;
import com.gsitm.air.vo.AirDeparturesVO;
import com.gsitm.air.vo.SerchVO;
import com.gsitm.air.vo.StatsByTimelineVO;
import com.gsitm.air.vo.TimeStatVO;

@Service
public class AirService {
	
	@Resource(name="airDao")
	private AirDao airDao;

	public void AirDeparturesAdd(List<AirDeparturesVO> list) throws Exception{
		//받은 리스트를 하나씩 실행시켜줌
		for(AirDeparturesVO vo : list) {
			airDao.AirDeparturesAdd(vo);
		}//end for
	}
	public void AirArrivalsAdd(List<AirArrivalsVO> list) throws Exception{
		//받은 리스트를 하나씩 실행시켜줌
		for(AirArrivalsVO vo : list) {
			airDao.AirArrivalsAdd(vo);
		}//end for
	}
	public void StatsByTimelineAdd(List<StatsByTimelineVO> list) throws Exception{
		//받은 리스트를 하나씩 실행시켜줌
		for(StatsByTimelineVO vo : list) {
			airDao.StatsByTimelineAdd(vo);
		}//end for
	}
	
	public List<AirDeparturesVO> AirDeparturesList() throws Exception{
		return airDao.AirDeparturesList();
	}
	public List<AirDeparturesVO> AirDeparturesListSerch(SerchVO vo) throws Exception{
		return airDao.AirDeparturesList(vo);
	}
	public List<AirArrivalsVO> AirArriveListSerch(SerchVO vo) throws Exception{
		return airDao.AirArriveListSerch(vo);
	}
	public List<StatsByTimelineVO> statsByTimeline() throws Exception{
		return airDao.statsByTimeline();
	}
	public List<HashMap<String, String>> terminalList() throws Exception{
		return airDao.terminalList();
	}
	public List<HashMap<String, String>> airportList() throws Exception{
		return airDao.airportList();
	}
	public List<HashMap<String, String>> airlineList() throws Exception{
		return airDao.airlineList();
	}
	public List<HashMap<String, String>> exitnumberList() throws Exception{
		return airDao.exitnumberList();
	}
	public TimeStatVO timeStat() throws Exception{
		return airDao.timeStat();
	}
}