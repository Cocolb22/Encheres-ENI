package fr.eni.encheres.dal.gestionUtilisateurs;

import java.util.List;

import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws BusinessException;
	public void update(Utilisateur utilisateur, Integer noUtilisateur)throws BusinessException;
	public void delete(Integer noUtilisateur)throws BusinessException;
	public Utilisateur findByLoginAndPassword(String pseudo, String motDePasse) throws BusinessException;
	public List<Utilisateur> getUsers() throws BusinessException;
	public Utilisateur findById(Integer idUser) throws BusinessException;
}
