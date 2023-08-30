package fr.eni.encheres.dal.gestionUtilisateurs;

import java.util.List;

import bundles.BusinessException;
import fr.eni.encheres.bo.model.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws BusinessException;
	public void update(Utilisateur utilisateur)throws BusinessException;
	public void delete(Utilisateur utilisateur)throws BusinessException;
	public List<Utilisateur> findByLoginAndPassword(String pseudo, String motDePasse) throws BusinessException;
	
}
