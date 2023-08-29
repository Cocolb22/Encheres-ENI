package dal;



public class DAOFact {
	public static ArticleVenduDAO getTaskDAO() {
		return new ArticleVenduDAOImpl();
	}
}
