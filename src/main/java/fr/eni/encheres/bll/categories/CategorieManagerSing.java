package fr.eni.encheres.bll.categories;

public class CategorieManagerSing {
	
	private static CategorieManager instance;
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManagerImpl();
		}
		return instance;
	}

}
