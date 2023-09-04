package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;


import fr.eni.encheres.bll.categories.CategorieManager;
import fr.eni.encheres.bll.categories.CategorieManagerSing;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;
import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Retrait;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;


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
		
		HttpSession session = request.getSession();
		
		Utilisateur utilisateurInscrit = (Utilisateur) session.getAttribute("utilisateurInscrit");
		
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("description");
		Integer noCategorie = Integer.parseInt(request.getParameter("categorie"));
		String libelleCategorie = request.getParameter("categorie");
		Integer prixInitial = Integer.parseInt(request.getParameter("miseAPrix"));
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
		LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		
		Retrait retrait = new Retrait(rue, codePostal, ville);
		Categorie categorie = new Categorie(noCategorie, libelleCategorie);
		
		ArticleVendu articleVendu = new ArticleVendu(nomArticle, descriptionArticle, dateDebut, dateFin, prixInitial, null, utilisateurInscrit, categorie, retrait);
		System.out.println(utilisateurInscrit.getNoUtilisateur());
		try {
			String action = request.getParameter("action");
			System.out.println(action);
			if ("enregistrer".equals(action)) {
				articleManager.addArticle(articleVendu);
				enregistrer(request, response);
		    } else if ("annuler".equals(action)) {
		        annuler(request, response);
		    }
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void enregistrer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}
	
	private void annuler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}
}
