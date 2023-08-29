package fr.eni.encheres.bll.gestionUtilisateurs;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Utilisateur;

public interface UtilisateurManager {

	public void addUtilisateur (Utilisateur utilisateur) throws BLLException;
	public void updateUtilisateur (Utilisateur utilisateur);
	public void deleteUtilisateur (Utilisateur utilisateur);
	public Utilisateur showUtilisateur (Utilisateur utilisateur);
	public Utilisateur connectUtilisateur (String pseudo, String motDePasse);
	public Utilisateur disconnectUtilisateur();
}
