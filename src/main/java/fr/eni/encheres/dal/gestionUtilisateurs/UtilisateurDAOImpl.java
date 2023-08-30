package fr.eni.encheres.dal.gestionUtilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bundles.BusinessException;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.dal.util.CodeResultDAL;
import fr.eni.encheres.dal.util.ConnectionProvider;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	final String SELECT = "SELECT * FROM UTILISATEURS WHERE pseudo=?, mot_de_passe=?";
	//final String DELETE = "DELETE no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	//final String UPDATE = "UPDATE no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void insert(Utilisateur utilisateur) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public Utilisateur findByLoginAndPassword(String pseudo, String motDePasse) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getString("pseudo"),
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
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodeResultDAL.INSERT_OBJECT_ECHEC);
			throw be;
		}
		
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
}
