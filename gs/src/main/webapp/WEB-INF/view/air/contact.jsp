<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>GS Air에 오신것을 환영합니다.</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
    </head>
    <body data-spy="scroll" data-target=".navbar-collapse">
        <div class='preloader'><div class='loaded'>&nbsp;</div></div>
        <!-- Contact Section -->
        <section id="contact" class="contactus margin-top-120">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="main_contact sections">
                            <div class="head_title text-center">
                                <h1>모두가 선망하는 GS AIR!</h1>
                            </div>

                            <div class="row">
                                <div class="contact_contant">
                                    <div class="col-sm-6 col-xs-12">
                                    	<br/>
                                    	<br/>
                                        <div class="single_message_right_info">
                                            <ul>
                                                <li><i class="fa fa-map-marker"></i> <span style="font-size: 21px;">서울시 종로구 계동길 31 보헌빌딩 1F & 2F (우)03059</span></li>

                                                <li><i class="fa fa-mobile-phone"></i> <span style="font-size: 22px;">02-2189-6700</span></li>

                                                <li><i class="fa fa-envelope-o"></i> <span style="font-size: 22px;">gsair@gsair.com</span></li>
                                            </ul>
											<br/><br/>
                                            <div class="work_socail transition">
                                                <a href=""><i class="fa fa-facebook img-circle"></i></a>
                                                <a href=""><i class="fa fa-twitter img-circle"></i></a>
                                                <a href=""><i class="fa fa-google-plus img-circle"></i></a>
                                                <a href=""><i class="fa fa-pinterest img-circle"></i></a>
                                                <a href=""><i class="fa fa-instagram img-circle"></i></a>
                                            </div>
                                        </div>
                                    </div><!-- End of col-sm-6 -->

                                    <div class="col-sm-6 col-xs-12">
                                        <div class="single_contant_left margin-top-60">
                                            <form action="#" id="formid">
                                                <div id="map" style="width:500px;height:400px;"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div> <!-- End of messsage contant-->
                            </div>
                        </div>
                    </div>
                </div><!-- End of row -->
            </div><!-- End of container -->
        </section><!-- End of contact Section -->

        <div class="scrollup">
            <a href="#"><i class="fa fa-chevron-up"></i></a>
        </div>

        <script src="assets/js/vendor/jquery-1.11.2.min.js"></script>
        <script src="assets/js/vendor/bootstrap.min.js"></script>
        <script src="assets/js/jquery.easing.1.3.js"></script>

        <script src="assets/js/plugins.js"></script>
        <script src="assets/js/main.js"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=291795bfc47de493eac5797cdeeb8623"></script>
        <script type="text/javascript">
        	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	        var options = { //지도를 생성할 때 필요한 기본 옵션
	        	center: new daum.maps.LatLng(37.5786446, 126.98644059999992), //지도의 중심좌표.
	        	level: 3 //지도의 레벨(확대, 축소 정도)
	        };
	        var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
	        var positions = [
                {
                    content: '<div>GS AIR</div>', 
                    latlng: new daum.maps.LatLng(37.5786446, 126.98644059999992)
                }
			]
	        for (var i = 0; i < positions.length; i ++) {
	            // 마커를 생성합니다
	            var marker = new daum.maps.Marker({
	                map: map, // 마커를 표시할 지도
	                position: positions[i].latlng // 마커의 위치
	            });
	
	            // 마커에 표시할 인포윈도우를 생성합니다 
	            var infowindow = new daum.maps.InfoWindow({
	                content: positions[i].content // 인포윈도우에 표시할 내용
           		});
	        }

        </script>
	
    </body>
</html>
