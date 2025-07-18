<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
<script src="<c:url value='/stock/js/HeaderBar.js' />"></script>
<script>
COMMON.Block(function(){
    var header = new HeaderBar(document.getElementById('main'), {});
    header.setLabel('헤더입니다.');

    var table = new TableTemplate();
});
</script>
<div id="main"></div>
<%@ include file="../common/tail.jsp" %>