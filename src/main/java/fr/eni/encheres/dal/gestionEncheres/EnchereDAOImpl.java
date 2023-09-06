package fr.eni.encheres.dal.gestionEncheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.dal.util.ConnectionProvider;
import fr.eni.encheres.dal.util.DALException;

public class EnchereDAOImpl implements EnchereDAO {

    final String SELECT_MAX = "SELECT MAX(montant_enchere) max_enchere FROM ENCHERES WHERE no_article=?";

    final String SELECT = """
    	SELECT *
	    FROM ENCHERES e
	    INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur
	    INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article
    	INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie
    	LEFT JOIN RETRAITS r ON r.no_article = a.no_article
    		    		""";
    final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";

    @Override
    public void insert(Enchere enchere) throws DALException {
        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enchere.getEnchereur().getNoUtilisateur());
            stmt.setObject(2, enchere.getArticleVendu().getNoArticle());
            stmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
            stmt.setInt(4, enchere.getMontantEnchere());
            int nb = stmt.executeUpdate();
            if (nb > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    enchere.setNoEnchere(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new DALException("ms_insert");
        }
    }



    @Override
    public List<Enchere> getAll() throws DALException {
        List<Enchere> result = new ArrayList<>();

        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SELECT);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
            			rs.getInt("prix_initial"),
            			rs.getInt("prix_vente"),
            			utilisateur,
            			categorie,
            			null	//TODO : ajouter Retrait
            			);
                Enchere enchere = new Enchere(
                		rs.getInt("no_enchere"),
                		utilisateur,
                        article,
                        rs.getTimestamp("date_enchere").toLocalDateTime(),
                        rs.getInt("montant_enchere")
                        );
                result.add(enchere);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new DALException("ms_getall");
        }
		
        return result;
    }


    
    
   @Override
    public List<Enchere> findByCategorie(Integer noCategorie) throws DALException {
        List<Enchere> result = new ArrayList<>();
        String query = SELECT + " WHERE a.no_categorie = ?";

        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, noCategorie);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
            			rs.getInt("prix_initial"),
            			rs.getInt("prix_vente"),
            			utilisateur,
            			categorie,
            			null	//TODO : ajouter Retrait
            			);
                Enchere enchere = new Enchere(
                		rs.getInt("no_enchere"),
                		utilisateur,
                        article,
                        rs.getTimestamp("date_enchere").toLocalDateTime(),
                        rs.getInt("montant_enchere")
                        );
                result.add(enchere);
            }
        } catch (SQLException e) {
            throw new DALException("ms_findByCategorie");
        }
        return result;
    }


    @Override
    public List<Enchere> findByNomArticle(String nomArticle) throws DALException {
        List<Enchere> result = new ArrayList<>();
        String query = SELECT + " WHERE a.nom_article LIKE ?";
        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,"%" + nomArticle +"%" );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
            			rs.getInt("prix_initial"),
            			rs.getInt("prix_vente"),
            			utilisateur,
            			categorie,
            			null	//TODO : ajouter Retrait
            			);
                Enchere enchere = new Enchere(
                		rs.getInt("no_enchere"),
                		utilisateur,
                        article,
                        rs.getTimestamp("date_enchere").toLocalDateTime(),
                        rs.getInt("montant_enchere")
                        );
                result.add(enchere);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new DALException("ms_findByNomArticle");
        }
        return result;
    }



	@Override
	public Integer selectMontantMaxInteger(ArticleVendu article) throws DALException {
		Integer montantMax = null;
		
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT_MAX);
			stmt.setInt(1, article.getNoArticle());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				montantMax = rs.getInt("max_enchere");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(montantMax != null) {
			return montantMax;
		} else {
			return article.getPrixInitial();
		}
		
	}


   
}

