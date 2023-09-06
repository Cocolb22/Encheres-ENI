package fr.eni.encheres.dal.gestionUtilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;
import fr.eni.encheres.dal.util.CodeResultDAL;
import fr.eni.encheres.dal.util.ConnectionProvider;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	final String SELECT = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?;";
	final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?;";
	final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	final String FIND_BY_USER_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		Connection con;
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setInt(11,utilisateur.isAdministrateur()?1:0);
			int nb = stmt.executeUpdate();
			if(nb>0) {
				ResultSet rs= stmt.getGeneratedKeys();
				if(rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if(e.getMessage().contains("UQ_UTILISATEURS_EMAIL")) {
				BusinessException be = new BusinessException(20013);
				throw be;
			} else if (e.getMessage().contains("UQ_UTILISATEURS_PSEUDO")) {
				BusinessException be = new BusinessException(20012);
				throw be;
			}
		}
			
	}

	@Override
	public Utilisateur findByLoginAndPassword(String pseudo, String motDePasse) throws BusinessException {
		Utilisateur utilisateur = null ;
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT);
			stmt.setString(1, pseudo);
			stmt.setString(2, motDePasse);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
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
							rs.getInt("administrateur")==1?true:false
						);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodeResultDAL.CHECK_CONNECTION_ECHEC);
			throw be;
		}
		
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur, Integer noUtilisateur) throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setInt(11,utilisateur.isAdministrateur()?1:0);
			stmt.setInt(12, noUtilisateur);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(Integer noUtilisateur) throws BusinessException {
		
		try (Connection con = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, noUtilisateur);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> getUsers() throws BusinessException {
		List <Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Utilisateur utilisateur = null ;
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
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
							rs.getInt("administrateur")==1?true:false
						);
			}
			utilisateurs.add(utilisateur);
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return utilisateurs;
	}
	
	@Override
	public Utilisateur findById(Integer idUser) throws BusinessException {
		Utilisateur user = null;
	    try (Connection con = ConnectionProvider.getConnection();
	        PreparedStatement stmt = con.prepareStatement(FIND_BY_USER_ID)) {
	        stmt.setInt(1, idUser);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                user = new Utilisateur(rs.getInt("no_utilisateur"),
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
							rs.getInt("administrateur")==1?true:false
						);
	            }

	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return user;
	}
}
