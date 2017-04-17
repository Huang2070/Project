<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>转盘</title>
    <script src="http://106.38.226.66/operative/wap/js/base/mobile-util.js"></script>
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="乐视云">
    <link rel="apple-touch-icon" href="http://i3.letvimg.com/lc05_lecloud/201601/12/10/21/favicon.ico" type="image/vnd.microsoft.icon">
    <link rel="shortcut icon" href="http://i3.letvimg.com/lc05_lecloud/201601/12/10/21/favicon.ico" type="image/vnd.microsoft.icon">
    <link rel="stylesheet" href="http://106.38.226.66/operative/wap/css/core.css">
    <link rel="stylesheet" href="http://106.38.226.66/operative/wap/css/modify.css">
</head>
<body class="cj-01-bg">
    <div class="cj-01 font-16">
        <div class="cj-title">
            <p>${activity.acTitle}</p>
        </div>
        <a href="4-zhongjiangjilu.html" title="" class="cj-common-btn-yellow mg-center ui-mt-10">我的奖品</a>    
        <div class="cj-round-wrap">
            <div class="cj-round-bg  mg-center">
            <!-- round-6 6个区域 round-8 8个区域  li标签个数对应-->
                <div class="cj-round-table roundtable-style pst-center round-6">
                    <ul>
                        <c:forEach var="item" items="${activity.prizeList}">
                            <li><div class="round"><span data-jiang="${item['id']}" class="font-14">${item['name']}</span><img src="${item['img']}" alt=""></div></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="cj-round-mask"></div>
                <div class="cj-round-dot flash"></div>
            </div>
            <div class="cj-round-point"></div>
        </div>
        <div class="cj-content font-12 color-fff">
            <p class="cj-date text-center cj-common-box-bg ui-mt-10">
                活动时间：${activity.acStartTime} -- ${activity.acEndTime}
            </p>
            <h2><span><img src="http://106.38.226.66/operative/wap/images/hd-01.png" alt=""></span></h2>
            <ul class="cj-ruler cj-common-box-bg ui-mt-10"> 
                <li>${activity.acDesc}</li>
            </ul>
            
            <!-- 幸运朋友占位符 -->
            <div id="luckyVueDom"></div>
            </div>  
        </div>    
    </div>

    <div id="tipDom"></div>
    <div id="dialogDom"></div>

    <script src="http://106.38.226.66/operative/wap/js/base/base.js"></script>
    <script src="http://106.38.226.66/operative/wap/dist/js/vendor.js"></script>
    <script src="http://106.38.226.66/operative/wap/js/abc.js"></script>
    <script>
        __loadjs('zhuanpan')
    </script>

</body>
</html>