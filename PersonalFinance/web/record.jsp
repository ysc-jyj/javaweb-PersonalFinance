<%@ page import="com.jyj.bean.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/28
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>记账</title>
    <link rel="stylesheet" type="text/css" href="css/record.css"/>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
    <script src="/js/jquery-2.1.3.min.js"></script>

</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="container">
    <div class="superbox">
        <%--<iframe src="right.jsp" height="100%" width="290" scrolling="no" frameborder="0" style="position: fixed"></iframe>--%>
        <div class="right">
            <jsp:include page="right.jsp"></jsp:include>
        </div>
        <div class="left">
            <div class="record">
                <h2>记账</h2>
                <form action="${pageContext.request.contextPath}/BookkeepingServlet?method=add" method="post">
                    <div class="top">
                        <input type="radio" name="payments" id="btnS" value="支出" checked="checked"/>支出
                        <input type="radio" name="payments" id="btnI" value="收入"/>收入
                        <div class="type">
                            账目类型:
                            <select id="spending" name="type">
                                <option value="饮食">饮食</option>
                                <option value="教育">教育</option>
                                <option value="购物">购物</option>
                                <option value="借款">借款</option>
                                <option value="通信">通信</option>
                                <option value="医疗">医疗</option>
                                <option value="其它">其它</option>
                            </select>
                            <select id="income" class="hidden" name="type" disabled="disabled">
                                <option value="红包">红包</option>
                                <option value="工资">工资</option>
                                <option value="理财">理财</option>
                                <option value="副业">副业</option>
                                <option value="其它">其它</option>
                            </select>
                        </div>
                        <div class="way">
                            方式:
                            <select id="payment_way" name="way">
                                <option value="现金">现金</option>
                                <option value="银行卡">银行卡</option>
                                <option value="微信">微信</option>
                                <option value="支付宝">支付宝</option>
                            </select>
                        </div>
                    </div>
                    金额:<input type="text" name="money">
                    日期:<input type="date" name="pdate">
                    备注:<input type="text" name="remarks"><br/>
                    <input type="submit" value="添加" onclick="updateTable()" class="btnAdd"><br/>
                </form>
            </div>
            <div class="table">
                <h2>账目清单</h2>
                <%--    <jsp:include page="table.jsp"></jsp:include>--%>
                <table>
                    <%-- <caption>财务明细</caption>--%>
                    <thead>
                    <tr class="title">
                        <th class="hidden">ID</th>
                        <th>日期</th>
                        <th>收/支</th>
                        <th>类型</th>
                        <th>支付方式</th>
                        <th>金额</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <c:if test="${not empty requestScope.TablePage.list}">
                        <c:forEach items="${requestScope.TablePage.list}" var="payment">
                            <tbody>
                            <tr>
                                <td class="hidden">${payment.ID}</td>
                                <td>${payment.pdate}</td>
                                <td>${payment.payments}</td>
                                <td>${payment.type}</td>
                                <td>${payment.way}</td>
                                <td>${payment.money}</td>
                                <td>${payment.remarks}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/BookkeepingServlet?method=delete&ID=${payment.ID}"
                                       onClick="updateTable()">删除</a>
                                </td>

                            </tr>
                            </tbody>
                        </c:forEach>
                    </c:if>
                </table>
                <div class="pageTool">
                    <a href="javascript:beforePage('${TablePage.beforePage}')">上一页</a>
                    共${TablePage.totalPage}页 共${TablePage.totalRows}行
                    第${TablePage.currentPage}页
                    每页${TablePage.pageSize}行
                    <a href="javascript:afterPage('${TablePage.afterPage}')">下一页</a>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $("#btnI").click(function () {
            $("#spending").hide();
            $("#income").show();
            $("#income").attr("disabled", false);
            $('#spending').attr("disabled", "disabled");

        });
        $("#btnS").click(function () {
            $("#income").hide();
            $("#spending").show();
            $("#spending").attr("disabled", false);
            $("#income").attr("disabled", true);
        });
    });

    function updateTable() {

        window.location = "${pageContext.request.contextPath}/BookkeepingServlet?currentPage=" + page;

    }

    function beforePage(page) {
        window.location = "${pageContext.request.contextPath}/BookkeepingServlet?currentPage=" + page + "&pageSize=${TablePage.pageSize}";
    };

    function afterPage(page) {
        window.location = "${pageContext.request.contextPath}/BookkeepingServlet?currentPage=" + page + "&pageSize=${TablePage.pageSize}";
    };
</script>

</html>
