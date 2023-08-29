package dal;

import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu article) throws DALException;
	public List<ArticleVendu> getAll() throws DALException;

}
