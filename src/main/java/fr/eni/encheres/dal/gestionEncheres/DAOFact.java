package fr.eni.encheres.dal.gestionEncheres;



public class DAOFact {
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}
}
