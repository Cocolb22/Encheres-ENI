package fr.eni.encheres.dal.gestionUtilisateur;

import java.util.List;

import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.dal.util.DALException;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws DALException;
	public void update(Utilisateur utilisateur)throws DALException;
	public void delete(Utilisateur utilisateur)throws DALException;
	public List<Utilisateur> findByLoginAndPassword(String pseudo, String motDePasse) throws DALException;
	
}
