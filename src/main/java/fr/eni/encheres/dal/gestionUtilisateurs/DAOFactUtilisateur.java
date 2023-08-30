package fr.eni.encheres.dal.gestionUtilisateurs;

public class DAOFactUtilisateur {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
}
