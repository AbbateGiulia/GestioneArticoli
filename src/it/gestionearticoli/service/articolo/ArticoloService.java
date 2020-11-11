package it.gestionearticoli.service.articolo;

import java.util.List;

import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public interface ArticoloService {

	// questo mi serve per injection
	public void setArticoloDao(ArticoloDAO articoloDao);

	public List<Articolo> listAll() throws Exception;

	public Articolo findById(Long idInput) throws Exception;

	public int aggiorna(Articolo input) throws Exception;

	public int inserisciNuovo(Articolo input) throws Exception;

	public int rimuovi(Articolo input) throws Exception;

	public List<Articolo> findByExample(Articolo input) throws Exception;
	
	public List<Articolo> listByCategoria(Categoria categoria) throws Exception;

}
