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
				<h5>Search Articolo</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					Cerca il tuo articolo
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/ExecuteSearchArticoloServlet"
					class="needs-validation" novalidate>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Codice </label> 
							<input type="text" name="codice" id="codice" class="form-control"
								placeholder="Inserire il codice">
								<div class="invalid-tooltip">Inserisci un codice valido.</div>
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione </label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione">
								<div class="invalid-tooltip">Inserisci una descrizione valida.</div>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Prezzo </label> <input
								type="number" class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" >
								<div class="invalid-tooltip">Inserisci un prezzo valido.</div>
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Categoria <span class="text-danger">*</span></label> 
							<select	name="idCategoria" id="idCategoria">
								<option selected value="-1"> Nessuna Categoria </option>
							<c:forEach items ="${requestScope.listaCategorieAttribute}" var ="categoria"> 
								<option value="${categoria.id}">
								<c:out value="${categoria.categoria }"/>  
								</option>
								</c:forEach>
							</select>
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