package fr.eni.encheres.ihm.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManager;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManagerSing;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSing.getInstance();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		
		try {
			Utilisateur utilisateurInscrit = manager.connectUtilisateur(pseudo, motDePasse);
			System.out.println(utilisateurInscrit);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurInscrit", utilisateurInscrit);
			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);
		}
	}
}
