<%@page import="it.gestionearticoli.model.Articolo" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Real delete</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<c:if test = "${ empty sessionScope.oggettoSessione or sessionScope.oggettoSessione.getRuolo()  != 'admin' }"> 
	      <c:redirect url= "/index.jsp"/>
	      </c:if>
	
		<div class='card'>
		<c:set var= "categoria" value="${requestScope.categoriaDaRimuovere}"/>
		    <div class='card-header'>
		        <h5>Sei sicuro di voler eliminare la categoria?</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class=" btn btn-danger" href="${pageContext.request.contextPath}/RealDeleteCategoriaServlet?id=${categoria}">Delete</a>
		    	<a class="btn btn-primary" href="${pageContext.request.contextPath}/ListCategorieServlet">Back</a>
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>