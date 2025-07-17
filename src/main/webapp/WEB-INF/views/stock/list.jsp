<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상장법인 목록</title>
    <!-- Axios CDN -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <!-- 외부 JS -->
    <script src="<c:url value='/resources/js/company-search.js' />"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<div class="container">
    <header>
        <h1>상장법인 목록</h1>
    </header>

    <div class="filter-bar">
        <form method="get" action="companyList.jsp">
            <label>시장구분:
                <select name="market">
                    <option value="">전체</option>
                    <option value="유가증권">유가증권</option>
                    <option value="코스닥">코스닥</option>
                    <option value="코넥스">코넥스</option>
                </select>
            </label>

            <label>검색유형:
                <select name="searchType">
                    <option value="상장법인">상장법인</option>
                    <option value="종목명">종목명</option>
                    <option value="대표자">대표자</option>
                    <!-- 등등 -->
                </select>
            </label>

            <label>업종:
                <select name="industry">
                    <option value="">전체</option>
                    <option value="소프트웨어">소프트웨어</option>
                    <option value="반도체">반도체</option>
                </select>
            </label>

            <label>결산월:
                <select name="fisc_month">
                    <option value="">전체</option>
                    <c:forEach begin="1" end="12" var="m">
                        <fmt:formatNumber value="${m}" pattern="00월" var="mFormatted"/>
                        <option value="${mFormatted}">${mFormatted}</option>
                    </c:forEach>
                </select>
            </label>

            <label>회사명:
                <input type="text" name="company_name"/>
            </label>

            <label>지역:
                <select name="region">
                    <option value="">전체</option>
                    <option value="서울특별시">서울특별시</option>
                    <option value="경기도">경기도</option>
                    <option value="부산광역시">부산광역시</option>
                    <!-- 등등 -->
                </select>
            </label>

            <button type="submit">검색</button>
        </form>
    </div>


    <main>
        <table class="company-table">
            <thead>
            <tr>
                <th>회사명</th>
                <th>업종</th>
                <th>주요제품</th>
                <th>상장일</th>
                <th>결산월</th>
                <th>대표자명</th>
                <th>홈페이지</th>
                <th>지역</th>
            </tr>
            </thead>
            <tbody id="company-table-body">
            <c:forEach var="item" items="${itemList}" varStatus="status">
                <c:if test="${status.index < 10}">
                    <tr>
                        <td>${item.comp_name}</td>
                        <td>${item.industry}</td>
                        <td>${item.main_prod}</td>
                        <td><fmt:formatDate value="${item.list_date}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.fisc_month}</td>
                        <td>${item.ceo_name}</td>
                        <td>
                            <c:if test="${not empty item.website}">
                                <a href="${item.website}" target="_blank">홈페이지</a>
                            </c:if>
                        </td>
                        <td>${item.region}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <button class="page-btn" disabled>이전</button>
            <c:forEach begin="1" end="10" var="page">
                <button class="page-btn" <c:if test='${page == 1}'>active</c:if>">${page}</button>
            </c:forEach>
            <button class="page-btn">다음</button>
        </div>

    </main>

    <footer>
        <p>&copy; 2025 상장법인정보시스템</p>
    </footer>
</div>
</body>
</html>
