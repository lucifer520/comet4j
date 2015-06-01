<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Comet4J Hello World</title>
<script type="text/javascript" src="js/comet4j.js"></script>
<script type="text/javascript">
function init(){
        var kbDom = document.getElementById('kb');
        var kbDom2 = document.getElementById('kb2');
        JS.Engine.on({
                test : function(aa){//侦听一个channel
                        kbDom.innerHTML = aa;
                },
                test2 : function(bb){
                	kbDom2.innerHTML = bb;
                }
        });
        JS.Engine.start('comet');
}
</script>
</head>
<body onload="init()">
        剩余内存：<span id="kb">...</span>KB <br/>
        剩余内存：<span id="kb2">...</span>KB
</body>
</html>