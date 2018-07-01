<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html class="no-js">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" type="image/png" href="favicon.ico">
	<title><decorator:title default="GS AIR" /></title>
	
	<link rel="apple-touch-icon" href="apple-touch-icon.png">
	
	<link rel="stylesheet" href="assets/css/fonticons.css">
    <link rel="stylesheet" href="assets/css/slider-pro.css">
    <link rel="stylesheet" href="assets/css/stylesheet.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">

    <!--For Plugins external css-->
    <link rel="stylesheet" href="assets/css/plugins.css" />

    <!--Theme custom css -->
    <link rel="stylesheet" href="assets/css/style.css">

    <!--Theme Responsive css-->
    <link rel="stylesheet" href="assets/css/responsive.css" />

    <script src="assets/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <script src="assets/js/vendor/jquery-1.11.2.min.js"></script>
	<script src="assets/js/vendor/bootstrap.min.js"></script>
	<script src="assets/js/jquery.easing.1.3.js"></script>
	<script src="assets/js/masonry/masonry.min.js"></script>
	<script src="assets/js/jquery.sliderPro.min.js"></script>
	<script src="assets/js/plugins.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<!--d3js CDN-->
	<script src="https://d3js.org/d3.v4.min.js"></script>
	<!-- Load billboard.js with style -->
	<script src="assets/js/billboard.js"></script>
	<link rel="stylesheet" href="assets/css/billboard.css">
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	<decorator:head/>
	<script type="text/javascript">
		$(document).ready(function() {
			//모달 닫기 버튼 클릭
			$("#basicModalYes").on("click",function(){
				$("#basicModal").modal("hide");
			});
		})
	</script>
</head>
<body data-spy="scroll" data-target=".navbar-collapse">
	
	<div class='preloader'><div class='loaded'>&nbsp;</div></div>
	<%--<page:apply-decorator name="panel" page="/top.do" /> --%>
	<jsp:include page="/WEB-INF/view/common/include/Top.jsp" />
	<decorator:body />		
	<jsp:include page="/WEB-INF/view/common/include/Footer.jsp" />
	
	<!-- START SCROLL TO TOP  -->
	<div class="scrollup">
	    <a href="#"><i class="fa fa-chevron-up"></i></a>
	</div>

</body>
</html>
