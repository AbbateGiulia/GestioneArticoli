package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class VisualizzaArticoloServlet
 */
@WebServlet("/laservletpervisualizzare")
public class VisualizzaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utente utente= (Utente) session.getAttribute("oggettoSessione");
		
		
		if( utente == null ) {
			request.setAttribute("messaggioErrore", "non puoi effettuare l'operazione");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			return;
		}
		
		String articoloInDettaglio = request.getParameter("IdDaInviareComeParametro");
		
		if (articoloInDettaglio == null || articoloInDettaglio.isEmpty()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
			
		ArticoloService a = MyServiceFactory.getArticoloServiceInstance();
		
		Articolo result=null;
		try {
			result = a.findById(Long.parseLong(articoloInDettaglio));
			if (result == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			request.setAttribute("articoloDaInviareAPaginaDettaglio", result);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("articolo/show.jsp");
		rd.forward(request, response);
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
