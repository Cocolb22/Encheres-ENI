package fr.eni.encheres.dal.gestionUtilisateur;

public class DAOFactUtilisateur {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
}
