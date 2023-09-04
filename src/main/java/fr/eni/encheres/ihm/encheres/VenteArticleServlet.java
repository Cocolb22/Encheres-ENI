package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.categories.CategorieManager;
import fr.eni.encheres.bll.categories.CategorieManagerSing;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;

public class VenteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenteArticleManager articleManager = VenteArticleManagerSing.getInstance();
	private CategorieManager managerCategorie = CategorieManagerSing.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> categorie = new ArrayList<>();
		try {
			categorie = managerCategorie.getAll();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorie", categorie);
		request.getRequestDispatcher("/WEB-INF/VenteArticle.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("description");
		
		
		
		String action = request.getParameter("action");
		
		 if ("enregistrer".equals(action)) {
	            enregistrer(request, response);
	        } else if ("annuler".equals(action)) {
	            supprimer(request, response);
	}

}

	private void enregistrer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}
	
	private void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}
}
