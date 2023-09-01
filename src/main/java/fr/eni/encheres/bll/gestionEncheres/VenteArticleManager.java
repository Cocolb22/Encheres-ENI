package fr.eni.encheres.bll.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bundles.BusinessException;

public interface VenteArticleManager {
	
	public void addArticle(ArticleVendu article) throws BusinessException;
	public List<ArticleVendu> getAllArticles() throws BusinessException;

}
