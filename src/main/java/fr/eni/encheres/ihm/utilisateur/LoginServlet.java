package fr.eni.encheres.ihm.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.categories.CategorieManager;
import fr.eni.encheres.bll.categories.CategorieManagerSing;
import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManager;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSing.getInstance();
	private EnchereManager managerEnchere = EnchereManagerSing.getInstance();
	private CategorieManager managerCategorie = CategorieManagerSing.getInstance();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		
		try {
			Utilisateur utilisateurInscrit = manager.connectUtilisateur(pseudo, motDePasse);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurInscrit", utilisateurInscrit);
			
			List<Categorie> categorie = new ArrayList<>();
			EnchereModel modelEnchere = new EnchereModel();
			try {
			    categorie = managerCategorie.getAll();
			    modelEnchere.setLstEnchere(managerEnchere.getAll());
			} catch (BLLException e) {
			    e.printStackTrace();
			    modelEnchere.setMessage("Erreur lors de la récupération des enchères.");
			}
			request.setAttribute("categorie", categorie);
			request.setAttribute("modelEnchere", modelEnchere);
			
			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);	
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);
		}
	}
}
