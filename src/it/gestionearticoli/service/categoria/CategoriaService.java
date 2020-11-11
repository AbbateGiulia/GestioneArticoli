package it.gestionearticoli.service.categoria;

import java.util.List;

import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;

public interface CategoriaService {
	// questo mi serve per injection
		public void setCategoriaDao(CategoriaDAO categoriaDao);

		public List<Categoria> listAll() throws Exception;

		public Categoria findById(Long idInput) throws Exception;

		public int aggiorna(Categoria input) throws Exception;

		public int inserisciNuovo(Categoria input) throws Exception;

		public int rimuovi(Categoria input) throws Exception;

		public List<Categoria> findByExample(Categoria input) throws Exception;


}
