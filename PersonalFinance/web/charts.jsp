<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/30
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>报表</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/charts.css">
    <script src="js/echarts.js"></script>
    <script src="/js/jquery-2.1.3.min.js"></script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>

<div class="container">
    <div class="superbox">
        <div class="right">
            <jsp:include page="right.jsp"></jsp:include>
        </div>
        <div class="left">
            <h2>资产情况</h2>
            <div class="asset">

                <div>
                    <p>银行卡</p>
                    <p><c:out value="${assets.card}"></c:out></p>
                </div>
                <div>
                    <p>微信</p>
                    <p><c:out value="${assets.weChat}"></c:out></p>
                </div>
                <div>
                    <p>支付宝</p>
                    <p><c:out value="${assets.alipay}"></c:out></p>
                </div>
                <div>
                    <p>现金</p>
                    <p><c:out value="${assets.other}"></c:out></p>
                </div>
            </div>

                <form action="${pageContext.request.contextPath}/ChartsServlet?method=seek" method="post">
                    请选择：
                    <select name="year" id="year">
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                        <option value="2025">2025</option>
                        <option value="2026">2026</option>
                        <option value="2027">2027</option>
                        <option value="2028">2028</option>
                        <option value="2029">2029</option>
                        <option value="2030">2030</option>
                    </select>年
                    <select name="month" id="month">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>月
                    <button class="btnSeek" type="submit">查询</button>
                    <br>
                    <div class="chart">
                    <div id='echartsP' style="width: 400px;height:400px;border:1px solid #eee"></div>
                    <script>
                        var myChart = echarts.init(document.getElementById('echartsP'));
                        option = ({
                            title: {
                                text: '月支出'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                data: [
                                    <c:forEach items="${requestScope.chartsList}" var="charts">
                                    ["${charts.type}"],
                                    </c:forEach>
                                ]
                            },
                            series: [
                                {
                                    name: '支出',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        <c:forEach items="${requestScope.chartsList}" var="charts">
                                        {value: "${charts.typeTotalMoney}", name: "${charts.type}"},
                                        </c:forEach>
                                    ],
                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    }
                                }
                            ]
                        });
                        myChart.setOption(option);
                    </script>
                    <div id="echartsI" style="width: 400px;height:400px;border:1px solid #eee"></div>
                    <script>
                        var myChart1 = echarts.init(document.getElementById('echartsI'));
                        option = ({
                            title: {
                                text: '月收入'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                data: [
                                    <c:forEach items="${requestScope.chartsList2}" var="charts">
                                    ["${charts.type}"],
                                    </c:forEach>
                                ]
                            },
                            series: [
                                {
                                    name: '收入',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        <c:forEach items="${requestScope.chartsList2}" var="charts">
                                        {value: "${charts.typeTotalMoney}", name: "${charts.type}"},
                                        </c:forEach>
                                    ],
                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    }
                                }
                            ]
                        });
                        myChart1.setOption(option);
                    </script>
                    <div id="echartsT" style="width: 800px;height:500px;border:1px solid #eee"></div>
                    <script>
                        var myChart2 = echarts.init(document.getElementById("echartsT"));
                        /* var pay = new Array();
                         var income = new Array();
                         var bala = new Array();
                         var index = 0;*/

                        option = {
                            title: {
                                text: "总结"
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow'
                                }
                            },
                            legend: {
                                data: ['支出', '收入', '结余']
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis: [
                                {
                                    type: 'category',
                                    data: [
                                        <c:forEach items="${chartsList3}" var="charts">
                                        ["${charts.timeT}"],
                                        </c:forEach>
                                    ]
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value'
                                }
                            ],
                            series: [
                                {
                                    name: '支出',
                                    type: 'bar',
                                    label: {
                                        show: true
                                    },
                                    data: [
                                        <c:forEach items="${chartsList3}" var="charts">
                                        ${charts.totalPay},
                                        </c:forEach>
                                    ]
                                },
                                {
                                    name: '收入',
                                    type: 'bar',
                                    label: {
                                        show: true
                                    },
                                    data: [
                                        <c:forEach items="${chartsList3}" var="charts">
                                        ${charts.totalIncome},
                                        </c:forEach>
                                    ]

                                },
                                {
                                    name: '结余',
                                    type: 'bar',
                                    label: {
                                        show: true
                                    },
                                    data: [
                                        <c:forEach items="${chartsList3}" var="charts">
                                        ${charts.balance},
                                        </c:forEach>
                                    ]
                                }
                            ]
                        };
                        myChart2.setOption(option);
                    </script>
                </form>
            </div>
        </div>
    </div>
</div>
l


</body>
</html>
