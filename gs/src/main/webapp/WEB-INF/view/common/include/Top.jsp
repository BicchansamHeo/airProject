<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<header id="main_menu" class="header navbar-fixed-top">            
    <div class="main_menu_bg">
        <div class="container">
            <div class="row">
                <div class="nave_menu">
                    <nav class="navbar navbar-default" id="navmenu">
                        <div class="container-fluid">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="main.do">
                                    <img id="mainImg" src="assets/images/logo.jpg"/>
                                </a>
                            </div>

                            <!-- Collect the nav links, forms, and other content for toggling -->

                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav navbar-right" style="font-size: 19px;">
                                    <li><a href="airDeparture.do">출발</a></li>
                                    <li><a href="airArrive.do">도착</a></li>
                                    <li><a href="stat.do">통계</a></li>
                                    <li><a href="contact.do">contact</a></li>
                                    <li><a href="#"></a></li>
                                    <li>
                                    	<form id="logOut" action="j_spring_security_logout" method="post">
										    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
										</form>
                                    	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
											 <a href="#" style="margin-top: -8px;" onclick="logoutCheck()"><label class="form-control" style="width: 120%;">로그아웃</label></a>
										</sec:authorize>
                                    	<sec:authorize access="isAnonymous()">
											 <a href="login" style="margin-top: -8px;"><label class="form-control" style="width: 120%;">로그인</label></a>
										</sec:authorize>
									</li>
                                </ul>
                            </div>
                        </div>
                        <br/>
                    </nav>
                </div>	
            </div>
        </div>
    </div>
   	<!-- 기본 modal창 시작 -->
	<div id="basicModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="basicMobody" class="modal-body" align="center">	
					<h5>로그아웃 하시겠습니까?</h5>
				</div>
				<div id="basic_ft" class="modal-footer">
					<button type='button' class='btn btn-default' id='basicModalYes'>확인</button>
					<button type='button' class='btn btn-danger' id='basicModalNo'>취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 기본 modal창 끝 -->
    <script type="text/javascript">
	    function logoutCheck(){
	    	$("#basicModal").modal("show");
	    	$("#basicModalYes").on("click",function(){
	    		$("#logOut").submit();
	    	})
         }
	    $(document).ready(function(){
	    	$("#basicModalNo").on("click",function(){
	    		$("#basicModal").modal("hide");
	    	})
	    	
	    })
    </script>
</header>