<%@ page import="Test.SingleTest" 
%>
<%@ page import="Test.compare" %>
<%@ page import="Test.SubtopicSearchLucene" %>
<%@ page import="Test.sub" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <link href="http://fonts.googleapis.com/css?family=Didact+Gothic" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
 
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Result Page</title>
 </head>
 <body>

<% 
SingleTest s= new SingleTest(request.getParameter("text1").toString());
String i=s.Test();
%>

<div id="banner-wrapper">
	<div id="banner" class="container">
 <div class="title">
The subtopic :
 <%  
 out.print(i);
 %>
 </div>
 </div>
 </div>
  </body>
 </html>