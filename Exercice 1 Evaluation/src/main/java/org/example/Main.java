package org.example;

import org.example.entities.*;
import org.example.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService coms = new CommandeService();
        LigneCommandeProduitService ls = new LigneCommandeProduitService();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("=========== TEST COMMANDE ===========");

        // 1️⃣ Création catégorie
        Categorie c = new Categorie("INFO", "Informatique");
        cs.create(c);

        // 2️⃣ Création produits
        Produit p1 = new Produit("Lenovo", 10000, c);
        Produit p2 = new Produit("HP", 9000, c);

        ps.create(p1);
        ps.create(p2);

        // 3️⃣ Création commande avec date
        Commande cmd = new Commande();
        cmd.setDate(sdf.parse("2023-06-15"));
        coms.create(cmd);

        // 4️⃣ Création lignes de commande
        LigneCommandeProduit l1 = new LigneCommandeProduit(2, p1, cmd);
        LigneCommandeProduit l2 = new LigneCommandeProduit(1, p2, cmd);

        ls.create(l1);
        ls.create(l2);

        // 5️⃣ Test findBetweenDates
        System.out.println("\n--- Produits commandés entre 2020 et 2025 ---");

        List<Produit> produits =
                ps.findBetweenDates(
                        sdf.parse("2020-01-01"),
                        sdf.parse("2025-01-01")
                );

        for (Produit p : produits) {
            System.out.println("Produit : " + p.getReference());
        }

        System.out.println("\n=========== FIN TEST ===========");
    }
}