package it.gestionearticoli.service.utente;

import java.util.List;

import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;


public interface UtenteService {
	
	// questo mi serve per injection
		public void setUtenteDao(UtenteDAO utenteDao);

		public List<Utente> listAll() throws Exception;

		public Utente findById(Long idInput) throws Exception;
		
		public Utente findByUser(String username) throws Exception;

		public int aggiorna(Utente input) throws Exception;

		public int inserisciNuovo(Utente input) throws Exception;

		public int rimuovi(Utente input) throws Exception;

		public List<Utente> findByExample(Utente input) throws Exception;
		

}
