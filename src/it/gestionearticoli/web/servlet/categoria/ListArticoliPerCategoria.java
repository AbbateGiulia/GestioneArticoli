package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class ListArticoliPerCategoria
 */
@WebServlet("/ListArticoliPerCategoria")
public class ListArticoliPerCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListArticoliPerCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("oggettoSessione");

		if (utente == null) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		String idInput = request.getParameter("IdDaInviareComeParametro");

		if (idInput == null || idInput.isEmpty()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		try {
			CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
			Categoria result = null;
			result = service.findById(Long.parseLong(idInput));
			if (result == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			request.setAttribute("categoriaAttribute", result);
			request.setAttribute("listaArticoliAttribute",
					MyServiceFactory.getArticoloServiceInstance().listByCategoria(result));
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		request.getRequestDispatcher("categoria/resultsfromcategoria.jsp").forward(request, response); // jsp lista
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
