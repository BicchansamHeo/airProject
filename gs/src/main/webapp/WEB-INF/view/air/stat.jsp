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
		<script type="text/javascript">
	       	google.charts.load("current", {packages:['corechart']});
	        google.charts.setOnLoadCallback(drawChart);
	        function drawChart() {
	          var data = google.visualization.arrayToDataTable([
	            ["Element", "운항수", { role: "style" } ],
	            ["00:00 ~ 00:59", ${timeVO.c00}, "color: #e5e4e2"],
	            ["01:00 ~ 01:59", ${timeVO.c01}, "color: #e5e4e2"],
	            ["02:00 ~ 02:59", ${timeVO.c02}, "color: #e5e4e2"],
	            ["03:00 ~ 03:59", ${timeVO.c03}, "color: #e5e4e2"],
	            ["04:00 ~ 04:59", ${timeVO.c04}, "color: #e5e4e2"],
	            ["05:00 ~ 05:59", ${timeVO.c05}, "color: #e5e4e2"],
	            ["06:00 ~ 06:59", ${timeVO.c06}, "color: #e5e4e2"],
	            ["07:00 ~ 07:59", ${timeVO.c07}, "color: #e5e4e2"],
	            ["08:00 ~ 08:59", ${timeVO.c08}, "color: #e5e4e2"],
	            ["09:00 ~ 09:59", ${timeVO.c09}, "color: #e5e4e2"],
	            ["10:00 ~ 10:59", ${timeVO.c10}, "color: #e5e4e2"],
	            ["11:00 ~ 11:59", ${timeVO.c11}, "color: #e5e4e2"],
	            ["12:00 ~ 12:59", ${timeVO.c12}, "color: #e5e4e2"],
	            ["13:00 ~ 13:59", ${timeVO.c13}, "color: #e5e4e2"],
	            ["14:00 ~ 14:59", ${timeVO.c14}, "color: #e5e4e2"],
	            ["15:00 ~ 15:59", ${timeVO.c15}, "color: #e5e4e2"],
	            ["16:00 ~ 16:59", ${timeVO.c16}, "color: #e5e4e2"],
	            ["17:00 ~ 17:59", ${timeVO.c17}, "color: #e5e4e2"],
	            ["18:00 ~ 18:59", ${timeVO.c18}, "color: #e5e4e2"],
	            ["19:00 ~ 19:59", ${timeVO.c19}, "color: #e5e4e2"],
	            ["20:00 ~ 20:59", ${timeVO.c20}, "color: #e5e4e2"],
	            ["21:00 ~ 21:59", ${timeVO.c21}, "color: #e5e4e2"],
	            ["22:00 ~ 22:59", ${timeVO.c22}, "color: #e5e4e2"],
	            ["23:00 ~ 23:59", ${timeVO.c23}, "color: #e5e4e2"]
	          ]);

	          var view = new google.visualization.DataView(data);
	          view.setColumns([0, 1,
	                           { calc: "stringify",
	                             sourceColumn: 1,
	                             type: "string",
	                             role: "annotation" },
	                           2]);

	          var options = {
	            width: "1000px",
	            height: "1000px",
	            bar: {groupWidth: "95%"},
	            legend: { position: "none" },
	          };
	          var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
	          chart.draw(view, options);
	          
	          
	          
	          /* ssssssssssssssssssssssssssssssssss */
	          google.charts.load("current", {packages:["corechart"]});
		      google.charts.setOnLoadCallback(drawChart);
		      function drawChart() {
		        var data = google.visualization.arrayToDataTable([
		          ['Task', 'Hours per Day'],
		          <c:forEach items="${timeList }" var="time" begin="0" end="3" varStatus="i">
		    		['${time.time }',${time.passenger }]
		    		<c:if test="${i.index ne 3 }">,</c:if>
	    		</c:forEach>
		        ]);
		
		        var options = {
		          is3D: true,
		        };
		        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		        chart.draw(data, options);
		      }
	      }
    	</script>
	</head> 
<body data-spy="scroll" data-target=".navbar-collapse">
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
	        <div class="container" style="text-align: center;">
	            <div id="time1" class="row">
	                <div class="col-sm-12">
							<br/>
							<br/>
						<label style="font-size: 20px;">시간대별 운항 통계</label>
	               		<div id="columnchart_values"></div>
	                </div>
	            </div>
	            <br/><br/>
	            <div id="time2">
	                <div class="col-sm-12">
	                	<br/><br/>
						<label style="font-size: 20px;">시간대별 승객 통계 Best4</label>
	               		<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
	                </div>
	            </div>
	        </div>
	    </section><!-- End of Home Section -->
</body>
</html>
