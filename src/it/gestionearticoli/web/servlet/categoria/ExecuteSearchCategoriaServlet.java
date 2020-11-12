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

/**
 * Servlet implementation class ExecuteSearchCategoriaServlet
 */
@WebServlet("/ExecuteSearchCategoriaServlet")
public class ExecuteSearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utente utente= (Utente) session.getAttribute("oggettoSessione");
		
		
		if( utente == null) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			return;
		}
			String categoriaParam =request.getParameter("idCategoria");
			Categoria categoriaInstance= new Categoria(categoriaParam);
			
			try {
				request.setAttribute("listaCategorieAttribute", MyServiceFactory.
						getCategoriaServiceInstance().findByExample(categoriaInstance));
			} catch (Exception e) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				e.printStackTrace();
				return;
			}
			request.getRequestDispatcher("categoria/listacategoriesearch.jsp").forward(request, response);

			}
	}

