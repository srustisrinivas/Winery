<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
* {box-sizing: border-box;}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #e9e9e9;
}

.topnav a {
  float: left;
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color:#800000;
  color: white;
}

.topnav .search-container {
  float: right;
}

.topnav input[type=text] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  border: none;
}

.topnav .search-container button {
  float: right;
  padding: 6px 10px;
  margin-top: 8px;
  margin-right: 16px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}

.topnav .search-container button:hover {
  background: #ccc;
}

@media screen and (max-width: 600px) {
  .topnav .search-container {
    float: none;
  }
  .topnav a, .topnav input[type=text], .topnav .search-container button {
    float: none;
    display: block;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    text-align: left;
    width: 100%;
    margin: 0;
    padding: 14px;
  }
  .topnav input[type=text] {
    border: 1px solid #ccc;  
  }
}
.center {
  text-align: center;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #800000;
  color: white;
  border: 1px solid #800000;
}

.pagination a:hover:not(.active) {background-color:#ddd ;}
fieldset 
	{
		border: 1px solid #ddd !important;
		margin: 0;
		xmin-width: 0;
		padding: 10px;       
		position: relative;
		border-radius:4px;
		background-color:#f5f5f5;
		padding-left:10px!important;
	}
	
	

ul.breadcrumb {
	margin-top:0px;
  padding: 10px 16px;
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 18px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
ul.breadcrumb li+li:before {
   padding: 8px; 
  color: black;
  content: "/\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
</style>
</head>
<body>

<div class="topnav">
  <!-- <a class="" href="#home"></a> -->
  <a class="active" href="WineServlet">Welcome to Wine World!</a>
  <div class="search-container">
    <form action="WineServlet?homePage=hPage&homeBread=hBread">
      <input type="text" placeholder="Search.." name="searchTerm">
      <button type="submit" value="searchTerm"><i class="fa fa-search"></i></button>
    </form>
  </div>
</div>

<div>
<ul class="breadcrumb">
<li><a href="WineServlet?homePage=hPage&homeBread=hBread&saver=save">Home</a></li>
<c:forEach var="bcMap" items="${breadcrumbsMap}">
  <li><a href="WineServlet?searchTerm=${searchTerm}&bcMapKey=${bcMap.getKey()}&bcMapValue=${bcMap.getValue()}&homeBread=hBread&saver=save">${bcMap.getValue()}</a></li>
</c:forEach>

<b style="padding-left: 900px">SortBy:<a href="WineServlet?sortField=P_Score&searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">Score</a>|<a href="WineServlet?sortField=P_PriceStr&searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">Price</a>&nbsp&nbsp&nbsp&nbsp<a href="WineServlet?orderBy=ASC&searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">ASC</a>|<a href="WineServlet?orderBy=DESC&searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">DESC</a></b>
</ul>
</div>


<div class=row>

<div class=col-sm-3>
<div id="facet1">
<h3>Winery</h3>
<fieldset>
<c:forEach var="wMap" items="${wineryMap}">
 <a href="WineServlet?searchTerm=${searchTerm}&winery=${wMap.getKey()}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">${wMap.getKey()}(${wMap.getValue()})</a><br>
 </c:forEach>
 </fieldset>
</div>

<div id="facet2">
<h3>WineType1</h3>
<fieldset>
<c:forEach var="wT1Map" items="${wineType1Map}">
 <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wT1Map.getKey()}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&saver=save">${wT1Map.getKey()}(${wT1Map.getValue()})</a><br>
 </c:forEach>
 </fieldset>
</div>

<div id="facet3">
<h3>WineType2</h3>
<fieldset>
<c:forEach var="wT2Map" items="${wineType2Map}">
 <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wT2Map.getKey()}&flavor=${flavor}&region=${region}&saver=save">${wT2Map.getKey()}(${wT2Map.getValue()})</a><br>
 </c:forEach>
 </fieldset>
</div>

<div id="facet4">
<h3>Flavors</h3>
<fieldset>
<c:forEach var="fMap" items="${flavorsMap}">
 <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${fMap.getKey()}&region=${region}&saver=save">${fMap.getKey()}(${fMap.getValue()})</a><br>
 </c:forEach>
 </fieldset>
</div>

<div id="facet5">
<h3>Region</h3>
<fieldset>
<c:forEach var="rMap" items="${regionMap}">
 <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${rMap.getKey()}&saver=save">${rMap.getKey()} (${rMap.getValue()})</a><br>
 </c:forEach>
 </fieldset>
</div>
</div>


<div class=col-sm-8>
<c:out value ="${numRecords} records displayed"/>
<fieldset>
     <c:forEach var="wlist" items="${wineDetailsList}">
      <fieldset>
        <strong>WineID:</strong>${wlist.getP_WineID()}<br>
        <strong>Winestrong:</strong>${wlist.getP_Wine()} <br>
        <strong>Description:</strong>${wlist.getP_Description()}<br>
        <strong>Winery:</strong>${wlist.getP_Winery()}<br>
        <strong>Year:</strong>${wlist.getP_Year()}<br>
        <strong>Price:</strong>${wlist.getP_PriceStr()}<br>
        <strong>Score:</strong>${wlist.getP_Score()}<br><br>
       </fieldset>
</c:forEach>
</fieldset>
</div>
</div>

<div class="center">
    <div class="pagination">
    <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&pagination=prev">Prev</a>
    <a><c:out value ="${pageNum+1}"/></a>
    <c:if test = "${count == 10}">
    <a href="WineServlet?searchTerm=${searchTerm}&winery=${winery}&wineType1=${wineType1}&wineType2=${wineType2}&flavor=${flavor}&region=${region}&pagination=next">Next</a>
    </c:if>
    </div>
  </div>

</body>
</html>
