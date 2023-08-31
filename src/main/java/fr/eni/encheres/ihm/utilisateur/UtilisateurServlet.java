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

public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSing.getInstance();

    public UtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Utilisateur utilisateurInscrit = (Utilisateur) session.getAttribute("utilisateurInscrit");
		
		utilisateurInscrit.setPseudo(request.getParameter("pseudo"));
		utilisateurInscrit.setNom(request.getParameter("nom"));
		utilisateurInscrit.setPrenom(request.getParameter("prenom"));
		utilisateurInscrit.setEmail(request.getParameter("email"));
		utilisateurInscrit.setTelephone(request.getParameter("telephone"));
		utilisateurInscrit.setRue(request.getParameter("rue"));
		utilisateurInscrit.setCodePostal(request.getParameter("codePostal"));
		utilisateurInscrit.setVille(request.getParameter("ville"));
		utilisateurInscrit.setMotDePasse(request.getParameter("motDePasse"));
		
		String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");

		try {
			if(request.getParameter("modifier") != null) {
				manager.updateUtilisateur(utilisateurInscrit, confirmationMotDePasse);
				request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp").forward(request, response);
			} else if(request.getParameter("supprimer") != null) {
				manager.deleteUtilisateur(utilisateurInscrit);
				session.invalidate();
				request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
				
			}
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp").forward(request, response);
		}
	}

}
