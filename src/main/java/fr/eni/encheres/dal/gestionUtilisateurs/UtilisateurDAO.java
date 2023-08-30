package fr.eni.encheres.dal.gestionUtilisateurs;

import bundles.BusinessException;
import fr.eni.encheres.bo.model.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur);
	public void update(Utilisateur utilisateur)throws BusinessException;
	public void delete(Utilisateur utilisateur)throws BusinessException;
	public Utilisateur findByLoginAndPassword(String pseudo, String motDePasse) throws BusinessException;
	
}
