package fr.eni.encheres.bll.gestionEncheres;

public class EnchereManagerSing {
	private static EnchereManager instance;
	public static EnchereManager getInstance() {
		if(instance == null ) {
			instance = new EnchereManagerImpl();
		}
		return instance;
	}

}
