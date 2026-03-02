package org.example.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommandeProduitPk implements Serializable {

    private Integer commande;
    private Integer produit;

    public CommandeProduitPk() {
    }

    public CommandeProduitPk(Integer commande, Integer produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public Integer getCommande() {
        return commande;
    }

    public void setCommande(Integer commande) {
        this.commande = commande;
    }

    public Integer getProduit() {
        return produit;
    }

    public void setProduit(Integer produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommandeProduitPk that = (CommandeProduitPk) o;
        return Objects.equals(commande, that.commande) && Objects.equals(produit, that.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commande, produit);
    }
}