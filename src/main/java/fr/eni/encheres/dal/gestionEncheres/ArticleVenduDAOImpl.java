package fr.eni.encheres.dal.gestionEncheres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.dal.util.ConnectionProvider;
import fr.eni.encheres.dal.util.DALException;


public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	
	final String SELECT = "SELECT * FROM ARTICLES_VENDUS";
	final String INSERT = "INSERT INTO (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente) VALUES (?,?,?,?,?,?,?,?)";
	

	@Override
	public void insert(ArticleVendu article) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2,article.getDescription());
			stmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			stmt.setDate(4, Date.valueOf(article.getDateDebutEncheres()));
			stmt.setInt(5, article.getPrixVente());
			int nb = stmt.executeUpdate();
			if(nb>0) {
				ResultSet rs= stmt.getGeneratedKeys();
				if(rs.next()) {
					article.setNoArticle(rs.getInt(1));
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
            	Categorie categorie = new Categorie(
            			rs.getInt("no_categorie"),
            			rs.getString("libelle")
            			);
            	Utilisateur utilisateur = new Utilisateur(
            			rs.getInt("no_utilisateur"),
            			rs.getString("pseudo"),
            			rs.getString("nom"),
            			rs.getString("prenom"),
            			rs.getString("email"),
            			rs.getString("telephone"),
            			rs.getString("rue"),
            			rs.getString("code_postal"),
            			rs.getString("ville"),
            			rs.getString("mot_de_passe"),
            			rs.getInt("credit"),
            			rs.getBoolean("administrateur")
            			);
				ArticleVendu article = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							rs.getDate("date_debut_encheres").toLocalDate(),
							rs.getDate("date_fin_encheres").toLocalDate(),
							rs.getInt("mise_a_prix"),
							rs.getInt("prix_vente"),
							utilisateur,
							categorie,
							null //TODO : gérer pt de retrait
						);
				result.add(article);
			}
		}
		catch(SQLException e) {
			throw new DALException("ms_insert");
		}
		
		return result;
	}

}
