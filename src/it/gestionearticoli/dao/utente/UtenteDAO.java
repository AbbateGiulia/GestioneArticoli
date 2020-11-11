package it.gestionearticoli.dao.utente;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Utente getUser(String username) throws Exception;

}
