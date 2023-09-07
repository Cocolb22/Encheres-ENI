package fr.eni.encheres.bll.gestionUtilisateurs;

import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

public interface UtilisateurManager {

	public void addUtilisateur (Utilisateur utilisateur, String confirmationMDP) throws BusinessException;
	public void updateUtilisateur (Utilisateur utilisateur, String confirmationMdp) throws BusinessException;
	public void deleteUtilisateur (Utilisateur utilisateur)throws BusinessException;
	public Utilisateur connectUtilisateur (String pseudo, String motDePasse) throws BusinessException;
	public Utilisateur checkIdUser(Integer idUser) throws BusinessException;
}
