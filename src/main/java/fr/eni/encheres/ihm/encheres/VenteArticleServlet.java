package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;

public class VenteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenteArticleManager articleManager = VenteArticleManagerSing.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateurInscrit");
		
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
