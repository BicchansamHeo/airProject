<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*, java.text.*"  %>
<%
 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy.MM.dd");
 java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat("HH");
 String today = formatter.format(new java.util.Date());
 String HH = formatter2.format(new java.util.Date());
%>
<html>
	<head>
		<meta charset="utf-8">
		<title>GS Air에 오신것을 환영합니다.</title>
		<style type="text/css">
		
		.th-like {
		  display: table-cell !important;
		  padding: 18px 0 16px !important;
		  border-right: 1px solid #474a5b !important;
		  background: #323648 !important;
		  font-size: 1rem !important;
		  font-weight: 400 !important;
		  color: #fff !important;
		  text-align: center !important;
		  vertical-align: middle !important;
		}
		.update {
		   margin-bottom: 10px;
		   text-align: left;
		   overflow: hidden;
		 }
		 .update-refresh {
		    float: right;
		    width: 26px;
		    padding: 0;
		    line-height: inherit;
		    background-image: url('images/spr_ico_global.png') no-repeat -68px -98px;
		  }
		  .flight-wrap {
  				margin-top: 10px;
			}
		</style>
		<script type="text/javascript">
	       	$(document).ready(function () {
	       		$("#startTime").val(Number($("#hour").val()));
	       		$("#endTime").val(Number($("#hour").val())+1);
	       		$(document).on("click", "#kakao-link-btn", function(){
	       			Kakao.Link.sendDefault({
	    			    objectType: 'feed',
	    			    content: {
	    			      title: '출발지:'+$(this).attr("airport"),
	    		    description: '#도착시간:'+$(this).attr("time")+', #항공사:'+$(this).attr("airline")+', #현황:'+$(this).attr("remark"),
	    	           imageUrl: 'https://s-i.huffpost.com/gen/2961236/thumbs/o-TURBULENCE-570.jpg?2',
	    		  	       link: {
	    			        mobileWebUrl: 'http://localhost:8085/ex01/airDeparture.do',
	    			        webUrl: 'http://localhost:8085/ex01/airDeparture.do'
	    			      }
	    			    },
	    			    buttons: [
	    			      {
	    			        title: '웹으로 보기',
	    			        link: {
	    			          mobileWebUrl: 'http://localhost:8085/ex01/airDeparture.do',
	    			          webUrl: 'http://localhost:8085/ex01/airDeparture.do'
	    			        }
	    			      }
	    			    ]
	    			  });
	       		});
		  	})
		  	function poi(){
	       		location.href="poi.do";
	       	}
	        //<![CDATA[
	        // // 사용할 앱의 JavaScript 키를 설정해 주세요.
	        Kakao.init('291795bfc47de493eac5797cdeeb8623');
	        // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
		  //]]>
    	</script>
	</head> 
<body data-spy="scroll" data-target=".navbar-collapse">
	<input id="hour" type="hidden" value="<%=HH%>"/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
   <!-- Home Section -->
        <section id="home" class="home">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                    	<!-- 검색 -->
                    	<form action="airArrive.do" method="post">
	                    	<div>
	                    		<select class="form-control" style="width: 13%" name="terminal">
	                    			<option>전체 터미널</option>
	                    			<c:forEach var="terminal" items="${terminalList }">
	                    				<option value="${terminal.TERMINALID }">${terminal.TERMINALID }</option>
	                    			</c:forEach>
	                    		</select>	 
	                    		<select class="form-control" style="width: 13%" name="exitnumber">
	                    			<option>입국장 출구</option>
	                    			<c:forEach var="exitnumber" items="${exitnumberList }">
	                    				<option value="${exitnumber.EXITNUMBER }">${exitnumber.EXITNUMBER }</option>
	                    			</c:forEach>
	                    		</select>	 
	                    		<select class="form-control" style="width: 13%">
	                    			<option><%=today %></option>
	                    		</select>
	                    		<select class="form-control" style="width: 9%" name="startTime" id="startTime">
	                    			<c:forEach begin="00" end="23" varStatus="i">
	                    				<option value="${i.index }">${i.index }:00</option>
	                    			</c:forEach>
	                    		</select>
	                    		<span>~</span>
	                    		<select class="form-control" style="width: 9%" name="endTime" id="endTime">
	                    			<c:forEach begin="00" end="23" varStatus="i">
	                    				<option value="${i.index }">${i.index }:59</option>
	                    			</c:forEach>
	                    		</select>
	                    		<select class="form-control" style="width: 15%" name="airport">
	                    			<option>출발지 선택</option>
	                    			<c:forEach var="airport" items="${airportList }">
	                    				<option value="${airport.AIRPORT }">${airport.AIRPORT }</option>
	                    			</c:forEach>
	                    		</select>
	                    		<select class="form-control" style="width: 17%" name="airline">
	                    			<option>항공사 선택</option>
	                    			<c:forEach var="airline" items="${airlineList }">
	                    				<option value="${airline.AIRLINE }">${airline.AIRLINE }</option>
	                    			</c:forEach>
	                    		</select>
	                    		<input type="submit" class="btn btn-default" value="검색"/>
	                    	</div>
                    	</form>
                    	<!-- 검색 -->
						<br/>	                    	
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th class="th-like" style="width: 159px">도착시간</th>
										<th class="th-like" style="width: 204px">출발지</th>
										<th class="th-like" style="width: 265px">항공사/운항편명</th>
										<th class="th-like" style="width: 100px">터미널</th>
										<th class="th-like" style="width: 130px">도착 게이트</th>
										<th class="th-like" style="width: 100px">수화물수취대</th>
										<th class="th-like" style="width: 91px">입국장출구</th>
										<th class="th-like" style="width: 110px">도착현황</th>
										<th class="th-like" style="width: 93px">알려주기</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="vo" items="${voList }" >
										<tr class="text-center">
											<td style="vertical-align: middle;">${vo.estimateddatetime }<br/>
												<c:if test="${vo.estimateddatetime ne vo.scheduledatetime}">
													<span style="text-decoration:line-through;">${vo.scheduledatetime }</span>
												</c:if>
											</td>
											<td style="vertical-align: middle;">${vo.airport }</td>
											<td style="vertical-align: middle;">
												<img alt="${vo.airline }" src="images/${vo.airline }.png" 
												onerror="this.src='images/nologo.png'"/>
												${vo.flightid }<br/>
												${vo.airline }</td>
											<td style="vertical-align: middle;">${vo.terminalid }</td>
											<td style="vertical-align: middle;">${vo.gatenumber }</td>
											<td style="vertical-align: middle;">${vo.carousel }</td>
											<td style="vertical-align: middle;">${vo.exitnumber }</td>
											<td style="vertical-align: middle;">
											<button type="button" class="<c:choose>
												<c:when test="${vo.remark eq '도착'}">
												    btn btn-primary
												</c:when>
												<c:when test="${vo.remark eq '결항'}">
												    btn btn-primary
												</c:when>
												<c:when test="${vo.remark eq '지연'}">
												    btn btn-danger
												</c:when>
												<c:when test="${vo.remark eq '회황'}">
												    btn btn-warning
												</c:when>
												<c:when test="${vo.remark eq '착륙'}">
												    btn btn-info
												</c:when>
												<c:otherwise>
												    btn btn-default
												</c:otherwise>
												</c:choose>" disabled="disabled">${vo.remark }</button>
												</td>
												<td style="vertical-align: middle;">
													<a id="kakao-link-btn" airport="${vo.airport }" time="${vo.estimateddatetime }"
													remark="${vo.remark}" airline="${vo.airline }">
													<img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
													style="width: 45%; height: 45%;"/>
												</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
                    </div>
                </div>
            </div>
        </section><!-- End of Home Section -->
</body>
</html>
