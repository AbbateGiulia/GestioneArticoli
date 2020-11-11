<%@page import="it.gestionearticoli.model.Articolo"%>
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
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista risultati della categoria ${categoriaAttribute.categoria} </h5> 
		    </div>
		    <div class='card-body'>
		    <c:if test = "${sessionScope.oggettoSessione.getRuolo() != 'guest'}">
		     <a class="btn btn-primary " href="${pageContext.request.contextPath}/PrepareInsertArticoloServlet?IdDaInviareComeParametro=${requestScope.categoriaAttribute.getId()}">Add New</a>
		     </c:if>
		    	
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Codice</th>
		                        <th>Descrizione</th>
		                        <th>Prezzo</th>		                        
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                		
		                			                		
		                		<c:forEach items ="${requestScope.listaArticoliAttribute}" var ="articolo"> 
		                    <tr>
		                        <td><c:out value =" ${articolo.id}"/></td> <!-- <c:out value = "item.id"/> -->
		                        <td><c:out value =" ${articolo.codice}"/></td>
		                        <td><c:out value =" ${articolo.descrizione}"/></td>
		                        <td><c:out value =" ${articolo.prezzo}"/></td>		                 
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/laservletpervisualizzare?IdDaInviareComeParametro=${articolo.id}">Visualizza</a>
									
									<c:if test = "${sessionScope.oggettoSessione.getRuolo() != 'guest'}">
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/PrepareUpdateArticoloServlet?IdDaInviareComeParametro=${articolo.id}">Edit</a>																	
										<c:if test = "${sessionScope.oggettoSessione.getRuolo() == 'admin'}">
											<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/ExecuteDeleteArticoloServlet?id=${articolo.id}">Delete</a>
										</c:if>									
									</c:if>
								</td>
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