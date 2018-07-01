package com.gsitm.air.controller;

import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gsitm.air.service.AirService;
import com.gsitm.air.vo.AirArrivalsVO;
import com.gsitm.air.vo.AirDeparturesVO;
import com.gsitm.air.vo.SerchVO;
import com.gsitm.air.vo.StatsByTimelineVO;
import com.gsitm.air.vo.TimeStatVO;

/** 
* @과목명              : GS ITM 인턴사원 스프링교육
* @FileName            : AirController.java 
* @Project             : gs 
* @Date                : 2018. 5. 24. 
* @작성자              : 허빛찬샘
* @프로그램 설명       : controller 구현
*/
@Controller
public class AirController {

	private static final Logger logger = LoggerFactory.getLogger(AirController.class);
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	@Resource(name = "airService")
	private AirService airService;
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
    private ModelAndView main(ModelAndView mv) {
		mv.setViewName("/air/main");
		return mv;
	}

    @RequestMapping(value = "/getOpenApiDepartures.do", method = RequestMethod.GET)
    private void getOpenApiDepartures(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    		String str = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights/getPassengerDepartures?ServiceKey="
    				+ URLDecoder.decode("rOzbrEyd%2B1YQ3PYjghYBttQmSf%2B80QxE35loM5h9HVfVEzqAnrBfL0nNznNTrGm2rrtOnbXxlij8mt7sC6VHAw%3D%3D","UTF-8")
    				+ "&from_time=0000&to_time=2400";
    		//한글깨짐현상 잡는 코드
    		for (HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
				if (messageConverter instanceof AllEncompassingFormHttpMessageConverter) {
					((AllEncompassingFormHttpMessageConverter) messageConverter).setCharset(Charset.forName("UTF-8"));
				}
			}
    		String str2 = restTemplate.getForObject(str, String.class);
    		//logger.info(str2);
    		InputSource is = new InputSource(new StringReader(str2));
            NodeList result = null;
    		List<AirDeparturesVO> list = new ArrayList<AirDeparturesVO>();
    		
    		//document라는 문서의 구조를 만들어냄
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            //node안에 Genrestrtlunch가 담김
            Node node = doc.getDocumentElement();
            //result에는 Genrestrtlunch의 자식이 담김
            result = node.getChildNodes();
    		//logger.info("node.getNodeName="+node.getNodeName());//response

            //result에는 row2개가 담김.
            for(int i = 0; i < result.getLength(); i++) {
                Node tmpNode = result.item(i);
                if (tmpNode.getNodeName().equals("body")){
                	NodeList nodeList = tmpNode.getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                    	NodeList nodeList2 = nodeList.item(j).getChildNodes();
                    	
                    	for (int k = 0; k < nodeList2.getLength(); k++) {
                    		NodeList nodeList3 = nodeList2.item(k).getChildNodes();
                    		
                    		AirDeparturesVO vo = new AirDeparturesVO();
                    		for(int m = 0; m < nodeList3.getLength(); m++) {
                    			if("airline".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setAirline(nodeList3.item(m).getTextContent());
                    			}else if("airport".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setAirport(nodeList3.item(m).getTextContent());
                    			}else if("airportCode".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setAirportCode(nodeList3.item(m).getTextContent());
                    			}else if("chkinrange".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setChkinrange(nodeList3.item(m).getTextContent());
                    			}else if("estimatedDateTime".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setEstimatedDateTime(nodeList3.item(m).getTextContent());
                    			}else if("flightId".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setFlightId(nodeList3.item(m).getTextContent());
                    			}else if("gatenumber".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setGatenumber(nodeList3.item(m).getTextContent());
                    			}else if("remark".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setRemark(nodeList3.item(m).getTextContent());
                    			}else if("scheduleDateTime".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setScheduleDateTime(nodeList3.item(m).getTextContent());
                    			}else if("terminalId".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setTerminalId(nodeList3.item(m).getTextContent());
                    			}
                    		}
                    		if(nodeList2.item(k).getNodeType() == 1) {
                    			list.add(vo);
                    		}//end if
                    		
                    	}//end for
                    }//end for
                }//end if
            }//end for
            
            //서비스로 데이터 전송
            airService.AirDeparturesAdd(list);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }    
    @RequestMapping(value = "/getOpenApiStatsByTimeline.do", method = RequestMethod.GET)
    private void getOpenApiStatsByTimeline(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    		String str = "http://openapi.airport.kr/openapi/service/AviationStatsByTimeline/getTotalNumberOfPassenger?ServiceKey="
    				+ URLDecoder.decode("rOzbrEyd%2B1YQ3PYjghYBttQmSf%2B80QxE35loM5h9HVfVEzqAnrBfL0nNznNTrGm2rrtOnbXxlij8mt7sC6VHAw%3D%3D","UTF-8")
    				+ "&from_month=201804&to_month=201804&periodicity=0&domestic_foreign=I&passenger_type=1";
    		//한글깨짐현상 잡는 코드
    		for (HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
				if (messageConverter instanceof AllEncompassingFormHttpMessageConverter) {
					((AllEncompassingFormHttpMessageConverter) messageConverter).setCharset(Charset.forName("UTF-8"));
				}
			}
    		String str2 = restTemplate.getForObject(str, String.class);
    		//logger.info(str2);
    		InputSource is = new InputSource(new StringReader(str2));
            NodeList result = null;
    		List<StatsByTimelineVO> list = new ArrayList<StatsByTimelineVO>();
    		
    		//document라는 문서의 구조를 만들어냄
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            //node안에 Genrestrtlunch가 담김
            Node node = doc.getDocumentElement();
            //result에는 Genrestrtlunch의 자식이 담김
            result = node.getChildNodes();
    		//logger.info("node.getNodeName="+node.getNodeName());//response

            //result에는 row2개가 담김.
            for(int i = 0; i < result.getLength(); i++) {
                Node tmpNode = result.item(i);
                if (tmpNode.getNodeName().equals("body")){
                	NodeList nodeList = tmpNode.getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                    	NodeList nodeList2 = nodeList.item(j).getChildNodes();
                    	
                    	for (int k = 0; k < nodeList2.getLength(); k++) {
                    		NodeList nodeList3 = nodeList2.item(k).getChildNodes();
                    		
                    		StatsByTimelineVO vo = new StatsByTimelineVO();
                    		for(int m = 0; m < nodeList3.getLength(); m++) {
                    			if("arrPassenger".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setArrPassenger(nodeList3.item(m).getTextContent());
                    			}else if("depPassenger".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setDepPassenger(nodeList3.item(m).getTextContent());
                    			}else if("passenger".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setPassenger(nodeList3.item(m).getTextContent());
                    			}else if("time".equals(nodeList3.item(m).getNodeName())) {
                    				vo.setTime(nodeList3.item(m).getTextContent());
                    			}
                    		}
                    		if(nodeList2.item(k).getNodeType() == 1) {
                    			list.add(vo);
                    		}//end if
                    		
                    	}//end for
                    }//end for
                }//end if
            }//end for
            
            //서비스로 데이터 전송
            airService.StatsByTimelineAdd(list);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }    
    
    @RequestMapping(value = "/airArrive.do", method = RequestMethod.GET)
    private String airArrive(HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		String HH = formatter.format(new java.util.Date());
		SerchVO vo = new SerchVO();
		vo.setStartTime(HH);
		vo.setEndTime(String.valueOf(Integer.parseInt(HH)+1));
		vo.setAirline("항공사 선택");
		vo.setAirport("출발지 선택");
		vo.setTerminal("전체 터미널");
		vo.setExitnumber("입국장 출구");
    	List<AirArrivalsVO> voList = new ArrayList<AirArrivalsVO>();	//항공편 리스트
    	List<HashMap<String, String>> terminalList = new ArrayList<HashMap<String, String>>(); //터미널 리스트
    	List<HashMap<String, String>> airportList = new ArrayList<HashMap<String, String>>(); //출발지 리스트
    	List<HashMap<String, String>> airlineList = new ArrayList<HashMap<String, String>>(); //항공사 리스트
    	List<HashMap<String, String>> exitnumberList = new ArrayList<HashMap<String, String>>(); //출구 리스트
    	try {
			voList = airService.AirArriveListSerch(vo);
			terminalList = airService.terminalList();
			airportList = airService.airportList();
			airlineList = airService.airlineList();
			exitnumberList = airService.exitnumberList();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.addAttribute("voList", voList);
    	model.addAttribute("terminalList", terminalList);
    	model.addAttribute("airportList", airportList);
    	model.addAttribute("airlineList", airlineList);
    	model.addAttribute("exitnumberList", exitnumberList);
    	return "/air/arrivals";
    }
    @RequestMapping(value = "/airArrive.do", method = RequestMethod.POST)
    private String airArrivePost(HttpServletRequest request, HttpServletResponse response, Model model, SerchVO serchVO) {
    	List<AirArrivalsVO> voList = new ArrayList<AirArrivalsVO>();	//항공편 리스트
    	List<HashMap<String, String>> terminalList = new ArrayList<HashMap<String, String>>(); //터미널 리스트
    	List<HashMap<String, String>> airportList = new ArrayList<HashMap<String, String>>(); //목적지 리스트
    	List<HashMap<String, String>> airlineList = new ArrayList<HashMap<String, String>>(); //항공사 리스트
    	List<HashMap<String, String>> exitnumberList = new ArrayList<HashMap<String, String>>(); //출구 리스트
    	try {
    		voList = airService.AirArriveListSerch(serchVO);
    		terminalList = airService.terminalList();
    		airportList = airService.airportList();
    		airlineList = airService.airlineList();
    		exitnumberList = airService.exitnumberList();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	model.addAttribute("voList", voList);
    	model.addAttribute("terminalList", terminalList);
    	model.addAttribute("airportList", airportList);
    	model.addAttribute("airlineList", airlineList);
    	model.addAttribute("exitnumberList", exitnumberList);
    	return "/air/arrivals";
    }
    
    @RequestMapping(value = "/airDeparture.do", method = RequestMethod.GET)
    private String airDeparture(HttpServletRequest request, HttpServletResponse response, Model model) {
    	SimpleDateFormat formatter = new SimpleDateFormat("HH");
    	String HH = formatter.format(new java.util.Date());
    	SerchVO vo = new SerchVO();
    	vo.setStartTime(HH);
    	vo.setEndTime(String.valueOf(Integer.parseInt(HH)+1));
    	vo.setAirline("항공사 선택");
    	vo.setAirport("목적지 선택");
    	vo.setTerminal("전체 터미널");
    	List<AirDeparturesVO> voList = new ArrayList<AirDeparturesVO>();	//항공편 리스트
    	List<HashMap<String, String>> terminalList = new ArrayList<HashMap<String, String>>(); //터미널 리스트
    	List<HashMap<String, String>> airportList = new ArrayList<HashMap<String, String>>(); //목적지 리스트
    	List<HashMap<String, String>> airlineList = new ArrayList<HashMap<String, String>>(); //항공사 리스트
    	try {
    		voList = airService.AirDeparturesListSerch(vo);
    		terminalList = airService.terminalList();
    		airportList = airService.airportList();
    		airlineList = airService.airlineList();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	model.addAttribute("voList", voList);
    	model.addAttribute("terminalList", terminalList);
    	model.addAttribute("airportList", airportList);
    	model.addAttribute("airlineList", airlineList);
    	return "/air/departure";
    }
    
    @RequestMapping(value = "/airDeparture.do", method = RequestMethod.POST)
    private String airDeparturePost(HttpServletRequest request, HttpServletResponse response, Model model, SerchVO serchVO) {
    	System.out.println(serchVO.getAirline()+serchVO.getAirport()+"d"+serchVO.getStartTime()+"@"+serchVO.getEndTime()+serchVO.getTerminal());
    	List<AirDeparturesVO> voList = new ArrayList<AirDeparturesVO>();	//항공편 리스트
    	List<HashMap<String, String>> terminalList = new ArrayList<HashMap<String, String>>(); //터미널 리스트
    	List<HashMap<String, String>> airportList = new ArrayList<HashMap<String, String>>(); //목적지 리스트
    	List<HashMap<String, String>> airlineList = new ArrayList<HashMap<String, String>>(); //항공사 리스트
    	try {
    		voList = airService.AirDeparturesListSerch(serchVO);
    		terminalList = airService.terminalList();
    		airportList = airService.airportList();
    		airlineList = airService.airlineList();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	model.addAttribute("voList", voList);
    	model.addAttribute("terminalList", terminalList);
    	model.addAttribute("airportList", airportList);
    	model.addAttribute("airlineList", airlineList);
    	return "/air/departure";
    }
    
    @RequestMapping(value = "/stat.do", method = RequestMethod.GET)
    private ModelAndView stat(HttpServletRequest request, HttpServletResponse response, Model model) {
    	ModelAndView mv = new ModelAndView();
    	TimeStatVO timeVO = new TimeStatVO();
    	List<StatsByTimelineVO> timeList = new ArrayList<StatsByTimelineVO>();
    	try {
			timeVO = airService.timeStat();
			timeList = airService.statsByTimeline();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	mv.addObject("timeVO", timeVO);
    	mv.addObject("timeList", timeList);
    	mv.setViewName("/air/stat");
    	return mv;
    }
    
    @RequestMapping(value = "/contact.do", method = RequestMethod.GET)
    private ModelAndView contact(HttpServletRequest request, HttpServletResponse response, Model model) {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/air/contact");
    	return mv;
    }
    
}
