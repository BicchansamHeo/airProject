package com.gsitm.air.batch;

import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gsitm.air.controller.AirController;
import com.gsitm.air.dao.AirDao;
import com.gsitm.air.service.AirService;
import com.gsitm.air.vo.AirArrivalsVO;
import com.gsitm.air.vo.AirDeparturesVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ExBatchJob {
	
	@Resource(name = "airDao")
	private AirDao airDao;
	@Resource(name = "airService")
	private AirService airService;
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(ExBatchJob.class);

	public void doBatch() {
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
            logger.info("실시간 데이터 입력완료.");
            
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            str = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights/getPassengerArrivals?ServiceKey="
            		+ URLDecoder.decode("rOzbrEyd%2B1YQ3PYjghYBttQmSf%2B80QxE35loM5h9HVfVEzqAnrBfL0nNznNTrGm2rrtOnbXxlij8mt7sC6VHAw%3D%3D","UTF-8")
            		+ "&from_time=0000&to_time=2400";
            //한글깨짐현상 잡는 코드
            for (HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
            	if (messageConverter instanceof AllEncompassingFormHttpMessageConverter) {
            		((AllEncompassingFormHttpMessageConverter) messageConverter).setCharset(Charset.forName("UTF-8"));
            	}
            }
            str2 = restTemplate.getForObject(str, String.class);
            //logger.info(str2);
            is = new InputSource(new StringReader(str2));
            result = null;
            List<AirArrivalsVO> listArrival = new ArrayList<AirArrivalsVO>();
            
            //document라는 문서의 구조를 만들어냄
            Document doc2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            //node안에 Genrestrtlunch가 담김
            Node node2 = doc2.getDocumentElement();
            //result에는 Genrestrtlunch의 자식이 담김
            result = node2.getChildNodes();
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
            				
            				AirArrivalsVO vo = new AirArrivalsVO();
            				for(int m = 0; m < nodeList3.getLength(); m++) {
            					if("airline".equals(nodeList3.item(m).getNodeName())) {
            						vo.setAirline(nodeList3.item(m).getTextContent());
            					}else if("airport".equals(nodeList3.item(m).getNodeName())) {
            						vo.setAirport(nodeList3.item(m).getTextContent());
            					}else if("airportCode".equals(nodeList3.item(m).getNodeName())) {
            						vo.setAirportcode(nodeList3.item(m).getTextContent());
            					}else if("carousel".equals(nodeList3.item(m).getNodeName())) {
            						vo.setCarousel(nodeList3.item(m).getTextContent());
            					}else if("estimatedDateTime".equals(nodeList3.item(m).getNodeName())) {
            						vo.setEstimateddatetime(nodeList3.item(m).getTextContent());
            					}else if("exitnumber".equals(nodeList3.item(m).getNodeName())) {
            						vo.setExitnumber(nodeList3.item(m).getTextContent());
            					}else if("flightId".equals(nodeList3.item(m).getNodeName())) {
            						vo.setFlightid(nodeList3.item(m).getTextContent());
            					}else if("gatenumber".equals(nodeList3.item(m).getNodeName())) {
            						vo.setGatenumber(nodeList3.item(m).getTextContent());
            					}else if("remark".equals(nodeList3.item(m).getNodeName())) {
            						vo.setRemark(nodeList3.item(m).getTextContent());
            					}else if("scheduleDateTime".equals(nodeList3.item(m).getNodeName())) {
            						vo.setScheduledatetime(nodeList3.item(m).getTextContent());
            					}else if("terminalId".equals(nodeList3.item(m).getNodeName())) {
            						vo.setTerminalid(nodeList3.item(m).getTextContent());
            					}
            				}
            				if(nodeList2.item(k).getNodeType() == 1) {
            					listArrival.add(vo);
            				}//end if
            				
            			}//end for
            		}//end for
            	}//end if
            }//end for
            
            //서비스로 데이터 전송
            airService.AirArrivalsAdd(listArrival);
            logger.info("실시간 데이터 입력완료.");
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExBatchJob proc = new ExBatchJob();
		proc.doBatch();
	}
}
