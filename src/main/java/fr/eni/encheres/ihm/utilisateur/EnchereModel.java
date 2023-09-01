package fr.eni.encheres.ihm.utilisateur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Utilisateur;

public class EnchereModel {

    private String message = "";
    private Enchere currentEnchere;
    private List<Enchere> lstEnchere = new ArrayList<>();
    
    public EnchereModel() {
        
    }

    public EnchereModel(String message, Enchere enchere, List<Enchere> lstEnchere) {
        super();
        this.message = message;
        this.currentEnchere = enchere;
        this.lstEnchere = lstEnchere;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Enchere getCurrentEnchere() {
        return currentEnchere;
    }

    public void setCurrentEnchere(Enchere current) {
        this.currentEnchere = current;
    }

    public List<Enchere> getLstEnchere() {
        return lstEnchere;
    }

    public void setLstEnchere(List<Enchere> lstEnchere) {
        this.lstEnchere = lstEnchere;
    }

    @Override
    public String toString() {
        return "EnchereModel [message=" + message + ", currentEnchere=" + currentEnchere + ", lstEnchere=" + lstEnchere + "]";
    }
    
    
}