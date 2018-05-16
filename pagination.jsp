<%--
  Created by IntelliJ IDEA.
  User: guoliang
  Date: 2018/5/15
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() +
            "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>pagination分页</title>
    <link rel="stylesheet"  href="../../js/jqueryPagination/css/pagination.css" type="text/css"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script  type="text/javascript" src="../../js/jqueryPagination/jquery.pagination.js"></script>
</head>
<body>
<div style="height: 150px ;width: 45px"></div>
<div>
            <h2>${msg}</h2>
            <c:if test="${fn:length(carBrandList.dataList) eq 0}">
                <span>暂无相关数据</span>
            </c:if>
            <c:if test="${fn:length(carBrandList.dataList) gt 0}">
                <table border="1px">
                    <tr>
                        <th>編號</th>
                        <th>车牌号</th>
                        <th>定位纬度</th>
                        <th>定位经度</th>
                        <th>GPS时间</th>
                    </tr>
            <c:forEach  items="${carBrandList.dataList}" var="car">
                <tr>
                    <td><c:out value="${car.carId}"></c:out></td>
                    <td>${car.plateNumber}</td>
                    <td>${car.latitude}</td>
                    <td>${car.longitude}</td>
                    <td>${car.dataTimeGps}</td>
                </tr>
            </c:forEach>
        </table>
                <br/>
            <div id="News-Pagination"></div>
        </c:if>
    </div>
</body>
</html>
<script type="text/javascript">
    //回调函数
    //new_page_index :当前页码
    function handlePaginationClick(new_page_index, pagination_container) {
        var  currentPage= new_page_index+1;
        window.location.href="subList.action?currentPage="+currentPage;
        return false;

    }
    $(function(){})
        // First Parameter: 总条数
        // Second Parameter: 每页显示的条数
        $("#News-Pagination").pagination(${carBrandList.totalRecord}, {
            items_per_page:${carBrandList.pageSize},
            current_page:${carBrandList.currentPage}-1,//当前显示第几页
            num_display_entries:6,//分页的条目数
            next_text:"下一页",
            prev_text:"上一页",
            num_edge_entries:2,
            load_first_page: false, //不加一直进行死循环
            callback:handlePaginationClick
        });


</script>
