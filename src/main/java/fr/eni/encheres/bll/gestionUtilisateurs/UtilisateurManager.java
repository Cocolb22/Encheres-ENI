package fr.eni.encheres.bll.gestionUtilisateurs;

import bundles.BusinessException;
import fr.eni.encheres.bo.model.Utilisateur;

public interface UtilisateurManager {

	public void addUtilisateur (Utilisateur utilisateur, String confirmationMDP) throws BusinessException;
	public void updateUtilisateur (Utilisateur utilisateur);
	public void deleteUtilisateur (Utilisateur utilisateur);
	public Utilisateur showUtilisateur (Utilisateur utilisateur);
	public Utilisateur connectUtilisateur (String pseudo, String motDePasse) throws BusinessException;
	public Utilisateur disconnectUtilisateur();
}
