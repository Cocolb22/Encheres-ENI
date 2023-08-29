package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.formation.task.bo.Task;
import fr.formation.task.dal.DALException;
import fr.formation.task.dal.util.ConnectionProvider;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	
	final String SELECT = "SELECT noArticle, nomArticle, description, dateDebutEncheres, dateFFinEncheres, miseAPrix, prixVente, etatVente FROM ArticleVendu";
	final String INSERT = "INSERT INTO (noArticle, nomArticle, description, dateDebutEncheres, dateFFinEncheres, miseAPrix, prixVente) VALUES (?,?,?,?,?,?,?,?)";
	

	@Override
	public void insert(ArticleVendu article) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getnomArticle());
			stmt.setString(2,article.getDescription());
			stmt.setDate(3, (Date) article.getdateDebutEncheres());
			stmt.setDate(4, LocalDate(article.getdateFinEncheres()));
			stmt.setInt(5, article.getprixVente());
			int nb = stmt.executeUpdate();
			if(nb>0) {
				ResultSet rs= stmt.getGeneratedKeys();
				if(rs.next()) {
					article.setnoArticle(rs.getInt(1));
				}
			}
		}
		catch(SQLException e) {
			throw new DALException("ms_insert");
		}
	}

	@Override
	public List<ArticleVendu> getAll() throws DALException {
		
		List<ArticleVendu> result = new ArrayList<>();
		
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu article = new ArticleVendu(rs.getString("nomArticle"),
							rs.setString("description"),
							rs.getDate("dateDebutEncheres").toLocalDate(),
							rs.getDate("dateFFinEncheres"),
							rs.getInt("miseAPrix"),
							rs.getInt("prixVente")
						);
				article.setnoArticle(rs.getInt("noArticle"));
				result.add(article);
			}
		}
		catch(SQLException e) {
			throw new DALException("ms_insert");
		}
		
		return result;
	}

}
