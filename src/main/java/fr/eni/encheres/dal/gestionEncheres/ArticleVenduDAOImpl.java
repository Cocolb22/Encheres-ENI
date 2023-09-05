package fr.eni.encheres.dal.gestionEncheres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Retrait;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;
import fr.eni.encheres.dal.gestionUtilisateurs.UtilisateurDAO;
import fr.eni.encheres.dal.util.ConnectionProvider;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	final String SELECT = """
			SELECT *
			FROM ARTICLES_VENDUS a
			INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur
			INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie
			LEFT JOIN RETRAITS r ON r.no_article = a.no_article
				""";
	final String INSERT_ARTICLE = """
			INSERT INTO ARTICLES_VENDUS( 
			nom_article, 
			description, 
			date_debut_encheres, 
			date_fin_encheres, 
			prix_initial,
			no_categorie,
			no_utilisateur) VALUES (?,?,?,?,?,?,?);
				""";

	final String INSERT_RETRAIT = """
			INSERT INTO RETRAITS(
			rue,
			code_postal,
			ville) VALUES (?,?,?);
			""";

	final String SELECT_BY_ID = """
						SELECT *
			FROM ARTICLES_VENDUS a
			INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur
			INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie
			LEFT JOIN RETRAITS r ON r.no_article = a.no_article
			WHERE a.no_article=?
			""";

	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			stmt.setDate(4, Date.valueOf(article.getDateDebutEncheres()));
			stmt.setInt(5, article.getPrixInitial());
			stmt.setInt(6, article.getCategorie().getNoCategorie());
			stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.executeUpdate();
			
			PreparedStatement pstmt = con.prepareStatement(INSERT_RETRAIT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getPointRetrait().getRue());
			pstmt.setString(2, article.getPointRetrait().getCodePostal());
			pstmt.setString(1, article.getPointRetrait().getVille());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArticleVendu> getAll() throws BusinessException {

		List<ArticleVendu> result = new ArrayList<>();

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = map(rs);
				result.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArticleVendu map(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
		Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
				rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
				rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
				rs.getInt("credit"), rs.getBoolean("administrateur"));
		Retrait pointDeRetrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
		ArticleVendu article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
				rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
				rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
				utilisateur, categorie, pointDeRetrait);
		return article;
	}

	@Override
	public ArticleVendu getArticleById(Integer noArticle) throws BusinessException {
		ArticleVendu articleVendu = null;
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, noArticle);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				articleVendu = map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVendu;
	}
}
