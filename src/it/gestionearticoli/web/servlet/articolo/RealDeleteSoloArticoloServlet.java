package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

/**
 * Servlet implementation class RealDeleteSoloArticoloServlet
 */
@WebServlet("/RealDeleteSoloArticoloServlet")
public class RealDeleteSoloArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealDeleteSoloArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utente utente= (Utente) session.getAttribute("oggettoSessione");
		
		
		if( utente == null || !utente.getRuolo().equals("admin")) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			return;
		}
		String param=request.getParameter("id");
		
		if (param == null || param.isEmpty()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		ArticoloService service= MyServiceFactory.getArticoloServiceInstance();
		Articolo result=null;
			
		try {
			result =service.findById(Long.parseLong(param));
			if (result == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			request.setAttribute("articolo", result);
			service.rimuovi(result);
			
			
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		
		request.getRequestDispatcher("articolo/results.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
