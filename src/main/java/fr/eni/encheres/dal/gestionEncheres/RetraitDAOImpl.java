package fr.eni.encheres.dal.gestionEncheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Retrait;
import fr.eni.encheres.dal.util.ConnectionProvider;
import fr.eni.encheres.dal.util.DALException;

public class RetraitDAOImpl implements RetraitDAO{
	
	final String INSERT = "INSERT INTO Retrait (noArticle, rue, codePostal, ville) VALUES (?, ?, ?, ?)";
	final String SELECT = "SELECT noArticle, rue, codePostal, ville FROM Retrait INNER JOIN Article ON Retrait.noArticle = Article.noArticle WHERE Article.noArticle = ?";
	
	@Override
	public void insert(Retrait retrait) throws DALException {
	      try (Connection con = ConnectionProvider.getConnection()) {
	            PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
	            stmt.setInt(1, retrait.getnoArticle());
	            stmt.setString(2, retrait.getRue());
	            stmt.setString(3, retrait.getcodePostal());
	            stmt.setString(4, retrait.getVille());
	        } catch (SQLException e) {
	            throw new DALException("ms_insert");
	        }		
	}

	@Override
	public List<Retrait> findByNoArticle(Integer noArticle) throws DALException {
		List<Retrait> result = new ArrayList<>();
        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SELECT);
            stmt.setInt(1, noArticle);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Retrait retrait = new Retrait(
                    rs.getInt("noArticle"),
                    rs.getString("rue"),
                    rs.getString("codePostal"),
                    rs.getString("ville")
                );
                retrait.setnoArticle(rs.getInt("noArticle"));
                result.add(retrait);
            }
        } catch (SQLException e) {
            throw new DALException("ms_findByNoArticle");
        }
		return result;
	}

}
