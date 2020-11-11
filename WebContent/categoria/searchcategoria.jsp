<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
	
	<c:if test = "${ empty sessionScope.oggettoSessione }"> 
	      <c:redirect url= "/index.jsp"/>
	      </c:if>

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Search Categoria</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					Cerca la categoria
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/ExecuteSearchCategoriaServlet"
					class="needs-validation" novalidate>
					
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Categoria <span class="text-danger">*</span></label> 
							<input type="text" name="idCategoria" id="idCategoria" class="form-control" placeholder="Inserire la categoria">
						</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>
				
				<script src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>