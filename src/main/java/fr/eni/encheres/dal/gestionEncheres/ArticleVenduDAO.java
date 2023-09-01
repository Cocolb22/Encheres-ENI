package fr.eni.encheres.dal.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bundles.BusinessException;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu article) throws BusinessException;
	public List<ArticleVendu> getAll() throws BusinessException;

}
