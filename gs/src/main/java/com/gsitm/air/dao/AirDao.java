package com.gsitm.air.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.gsitm.air.mapper.AirMapper;
import com.gsitm.air.vo.AirArrivalsVO;
import com.gsitm.air.vo.AirDeparturesVO;
import com.gsitm.air.vo.SerchVO;
import com.gsitm.air.vo.StatsByTimelineVO;
import com.gsitm.air.vo.TimeStatVO;
import com.gsitm.air.vo.UserVO;

@Repository(value = "airDao")
public class AirDao{
	private static final Logger logger = LoggerFactory.getLogger(AirDao.class);

	@Autowired
	protected SqlSession sqlSession;
	
	public void AirDeparturesAdd(AirDeparturesVO vo) {
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			airMapper.AirDeparturesAdd(vo);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
	}
	public void AirArrivalsAdd(AirArrivalsVO vo) {
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			airMapper.AirArrivalsAdd(vo);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
	}
	public void StatsByTimelineAdd(StatsByTimelineVO vo) {
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			airMapper.StatsByTimelineAdd(vo);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
	}
	public List<AirDeparturesVO> AirDeparturesList(SerchVO vo) {
		List<AirDeparturesVO> voList = new ArrayList<AirDeparturesVO>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			voList = airMapper.AirDeparturesListSerch(vo);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return voList;
	}
	public List<AirArrivalsVO> AirArriveListSerch(SerchVO vo) {
		List<AirArrivalsVO> voList = new ArrayList<AirArrivalsVO>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			voList = airMapper.AirArriveListSerch(vo);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return voList;
	}
	public List<AirDeparturesVO> AirDeparturesList() {
		List<AirDeparturesVO> voList = new ArrayList<AirDeparturesVO>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			voList = airMapper.AirDeparturesList();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return voList;
	}
	public List<StatsByTimelineVO> statsByTimeline() {
		List<StatsByTimelineVO> voList = new ArrayList<StatsByTimelineVO>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			voList = airMapper.statsByTimeline();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return voList;
	}
	public List<HashMap<String, String>> terminalList() {
		List<HashMap<String, String>> terminalList = new ArrayList<HashMap<String, String>>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			terminalList = airMapper.terminalList();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return terminalList;
	}
	public List<HashMap<String, String>> airportList() {
		List<HashMap<String, String>> airportList = new ArrayList<HashMap<String, String>>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			airportList = airMapper.airportList();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return airportList;
	}
	public List<HashMap<String, String>> airlineList() {
		List<HashMap<String, String>> airlineList = new ArrayList<HashMap<String, String>>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			airlineList = airMapper.airlineList();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return airlineList;
	}
	public List<HashMap<String, String>> exitnumberList() {
		List<HashMap<String, String>> exitnumberList = new ArrayList<HashMap<String, String>>();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			exitnumberList = airMapper.exitnumberList();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return exitnumberList;
	}
	public TimeStatVO timeStat() {
		TimeStatVO VO = new TimeStatVO();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			VO = airMapper.timeStat();
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		return VO;
	}
	public UserDetails loadUserByUsername(String username) {
		UserVO VO = new UserVO();
		try {
			AirMapper airMapper = sqlSession.getMapper(AirMapper.class);
			VO = airMapper.loadUserByUsername(username);
		}catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(VO.getRoles()));
		UserDetails user = new User(VO.getUserName(), VO.getPassWord(), roles);
		return user;
	}
}
