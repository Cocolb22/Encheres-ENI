package fr.eni.encheres.dal.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.dal.util.DALException;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu article) throws DALException;
	public List<ArticleVendu> getAll() throws DALException;

}
