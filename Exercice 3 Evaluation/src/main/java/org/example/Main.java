package org.example;

import org.example.entities.Femme;
import org.example.entities.Homme;
import org.example.entities.Mariage;
import org.example.service.FemmeService;
import org.example.service.HommeService;
import org.example.service.MariageService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        FemmeService   femmeService   = new FemmeService();
        HommeService   hommeService   = new HommeService();
        MariageService mariageService = new MariageService();

        // ============================================================
        // 1. Créer 10 femmes et 5 hommes
        // ============================================================
        System.out.println("========== Création des personnes ==========");

        Femme f1  = new Femme("Alaoui",  "Fatima",   "0611111111", "Casablanca", sdf.parse("1980-03-15"));
        Femme f2  = new Femme("Idrissi", "Sara",     "0622222222", "Rabat",      sdf.parse("1975-07-22"));
        Femme f3  = new Femme("Bennani", "Khadija",  "0633333333", "Fes",        sdf.parse("1990-01-10"));
        Femme f4  = new Femme("Chraibi", "Zineb",    "0644444444", "Marrakech",  sdf.parse("1985-11-05"));
        Femme f5  = new Femme("Tazi",    "Nadia",    "0655555555", "Agadir",     sdf.parse("1970-06-30"));
        Femme f6  = new Femme("Fassi",   "Imane",    "0666666666", "Tanger",     sdf.parse("1995-09-18"));
        Femme f7  = new Femme("Ouali",   "Hajar",    "0677777777", "Meknes",     sdf.parse("1988-04-25"));
        Femme f8  = new Femme("Lamrani", "Soukaina", "0688888888", "Oujda",      sdf.parse("1992-12-03"));
        Femme f9  = new Femme("Ziani",   "Rim",      "0699999999", "Kenitra",    sdf.parse("1983-08-14"));
        Femme f10 = new Femme("Berrada", "Loubna",   "0610101010", "Tetouan",    sdf.parse("1978-02-28"));

        Homme h1 = new Homme("Benali",  "Ahmed",   "0621212121", "Casablanca", sdf.parse("1972-05-10"));
        Homme h2 = new Homme("Karimi",  "Youssef", "0632323232", "Rabat",      sdf.parse("1968-09-20"));
        Homme h3 = new Homme("Hajji",   "Omar",    "0643434343", "Fes",        sdf.parse("1980-03-05"));
        Homme h4 = new Homme("Naciri",  "Karim",   "0654545454", "Marrakech",  sdf.parse("1975-11-15"));
        Homme h5 = new Homme("Sqalli",  "Rachid",  "0665656565", "Agadir",     sdf.parse("1985-07-25"));

        femmeService.create(f1);  femmeService.create(f2);  femmeService.create(f3);
        femmeService.create(f4);  femmeService.create(f5);  femmeService.create(f6);
        femmeService.create(f7);  femmeService.create(f8);  femmeService.create(f9);
        femmeService.create(f10);

        hommeService.create(h1); hommeService.create(h2); hommeService.create(h3);
        hommeService.create(h4); hommeService.create(h5);

        System.out.println("10 femmes et 5 hommes créés avec succès.");

        // ============================================================
        // Créer les mariages
        // ============================================================
        // h1 marié à f1, f2, f3, f4 → 4 femmes entre 2000 et 2020
        Mariage m1  = new Mariage(sdf.parse("2000-06-01"), sdf.parse("2005-01-01"), 2, h1, f1);
        Mariage m2  = new Mariage(sdf.parse("2006-03-15"), sdf.parse("2010-07-20"), 1, h1, f2);
        Mariage m3  = new Mariage(sdf.parse("2011-09-10"), sdf.parse("2015-04-30"), 3, h1, f3);
        Mariage m4  = new Mariage(sdf.parse("2016-02-14"), null,                   2, h1, f4);
        // f5 mariée à h2 et h3 → mariée 2 fois
        Mariage m5  = new Mariage(sdf.parse("2005-05-20"), sdf.parse("2012-08-15"), 1, h2, f5);
        Mariage m6  = new Mariage(sdf.parse("2003-11-01"), sdf.parse("2009-03-10"), 2, h3, f5);
        // f6 mariée à h3 et h4 → mariée 2 fois
        Mariage m7  = new Mariage(sdf.parse("2010-07-07"), null,                   0, h3, f6);
        Mariage m8  = new Mariage(sdf.parse("2008-01-20"), sdf.parse("2014-06-30"), 1, h4, f6);
        // mariages simples
        Mariage m9  = new Mariage(sdf.parse("2015-03-22"), null, 2, h4, f7);
        Mariage m10 = new Mariage(sdf.parse("2018-09-05"), null, 1, h5, f8);

        mariageService.create(m1);  mariageService.create(m2);
        mariageService.create(m3);  mariageService.create(m4);
        mariageService.create(m5);  mariageService.create(m6);
        mariageService.create(m7);  mariageService.create(m8);
        mariageService.create(m9);  mariageService.create(m10);

        System.out.println("Mariages créés avec succès.\n");

        // ============================================================
        // 2. Afficher la liste des femmes
        // ============================================================
        System.out.println("========== Liste des femmes ==========");
        List<Femme> femmes = femmeService.findAll();
        for (Femme f : femmes) {
            System.out.println("- " + f.getPrenom() + " " + f.getNom()
                    + " | Née le : " + f.getDateNaissance()
                    + " | Tél : "    + f.getTelephone()
                    + " | Adresse : " + f.getAdresse());
        }



        // ============================================================
        // 4. Afficher les épouses d'un homme donné entre deux dates
        // ============================================================
        System.out.println("\n========== Épouses de Ahmed Benali entre 2000 et 2020 ==========");
        List<Femme> epouses = hommeService.findEpouses(
                h1.getId(),
                sdf.parse("2000-01-01"),
                sdf.parse("2020-12-31")
        );
        for (Femme f : epouses) {
            System.out.println("- " + f.getPrenom() + " " + f.getNom());
        }

        // ============================================================
        // 5. Afficher le nombre d'enfants d'une femme entre deux dates
        // ============================================================
        System.out.println("\n========== Nombre d'enfants de Fatima Alaoui entre 1999 et 2010 ==========");
        int nbEnfants = femmeService.findEnfants(
                f1.getId(),
                sdf.parse("1999-01-01"),
                sdf.parse("2010-12-31")
        );
        System.out.println("Nombre d'enfants : " + nbEnfants);

        // ============================================================
        // 6. Afficher les femmes mariées deux fois ou plus
        // ============================================================
        System.out.println("\n========== Femmes mariées 2 fois ou plus ==========");
        List<Femme> femmesDeuxFois = femmeService.findFemmesMarieesAuMoinsDeux();
        for (Femme f : femmesDeuxFois) {
            System.out.println("- " + f.getPrenom() + " " + f.getNom());
        }

        // ============================================================
        // 7. Afficher le nombre d'hommes mariés à 4 femmes entre deux dates
        // ============================================================
        System.out.println("\n========== Hommes mariés à 4 femmes entre 2000 et 2020 ==========");
        long nbHommes = hommeService.getNbrHommesMariesQuatreFemmes(
                sdf.parse("2000-01-01"),
                sdf.parse("2020-12-31")
        );
        System.out.println("Nombre d'hommes mariés à 4 femmes : " + nbHommes);

        // ============================================================
        // 8. Afficher les mariages d'un homme avec tous les détails
        // ============================================================
        System.out.println("\n========== Mariages de Ahmed Benali avec détails ==========");
        hommeService.afficherMariagesByHomme(h1.getId());
    }
}