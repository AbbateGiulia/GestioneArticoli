<%@page import="it.gestionearticoli.model.Categoria"%>
<%@page import="it.gestionearticoli.model.Utente"%>
<%@page import="java.util.List"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<c:if test = "${ empty sessionScope.oggettoSessione }"> 
	      <c:redirect url= "/index.jsp"/>
	      </c:if>
	
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista delle categorie</h5> 
		    </div>
		    <c:if test= "${ not empty requestScope.messaggioErrore}">
		    	
		  			<div class="alert alert-danger" role="alert">						 
		 					 ${requestScope.messaggioErrore } ! 		  			
		  			</div>	
			</c:if>
		    <div class='card-body'>		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead> 
		                    <tr>
		                        
		                        <th>Categoria</th>	                        
		                    </tr>
		                </thead>
		                <tbody>
		                		
		                			                		
		                		<c:forEach items ="${requestScope.listaCategorieAttribute}" var ="categoria"> 
		                    <tr >
		                        <td><c:out value =" ${categoria.categoria}"/></td>		                 	                        
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>