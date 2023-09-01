package fr.eni.encheres.bll.gestionEncheres;

public class VenteArticleManagerSing {
	private static VenteArticleManager instance;
	
	public static VenteArticleManager getInstance() {
		if(instance == null) {
			instance = new VenteArticleManagerImpl();
		}
		return instance;
	}
}
