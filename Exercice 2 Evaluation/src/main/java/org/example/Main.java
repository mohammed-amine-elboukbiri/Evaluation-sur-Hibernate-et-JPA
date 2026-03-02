package org.example;

import org.example.entities.*;
import org.example.service.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("=========== TEST EMPLOYE / PROJET / TACHE ===========");

        // 1️⃣ Création des employés
        Employe emp1 = new Employe("Alami", "Youssef", "0612345678");
        Employe emp2 = new Employe("Bennani", "Sara", "0698765432");
        employeService.create(emp1);
        employeService.create(emp2);

        // 2️⃣ Création des projets
        Projet projet1 = new Projet("Gestion de stock", sdf.parse("2013-01-14"), sdf.parse("2013-12-31"), emp1);
        Projet projet2 = new Projet("Projet Mobile", sdf.parse("2024-03-01"), sdf.parse("2024-12-31"), emp2);
        projetService.create(projet1);
        projetService.create(projet2);

        // 3️⃣ Création des tâches
        Tache tache1 = new Tache("Analyse", sdf.parse("2013-02-10"), sdf.parse("2013-02-20"), 1500.0, projet1);
        Tache tache2 = new Tache("Conception", sdf.parse("2013-03-10"), sdf.parse("2013-03-15"), 800.0, projet1);
        Tache tache3 = new Tache("Développement", sdf.parse("2013-04-10"), sdf.parse("2013-04-25"), 2000.0, projet1);
        Tache tache4 = new Tache("Tests Mobile", sdf.parse("2024-04-01"), sdf.parse("2024-06-01"), 1200.0, projet2);
        tacheService.create(tache1);
        tacheService.create(tache2);
        tacheService.create(tache3);
        tacheService.create(tache4);


        EmployeTache et1 = new EmployeTache(sdf.parse("2013-02-10"), sdf.parse("2013-02-20"), emp1, tache1);
        EmployeTache et2 = new EmployeTache(sdf.parse("2013-03-10"), sdf.parse("2013-03-15"), emp1, tache2);
        EmployeTache et3 = new EmployeTache(sdf.parse("2013-04-10"), sdf.parse("2013-04-25"), emp1, tache3);
        EmployeTache et4 = new EmployeTache(sdf.parse("2024-04-05"), null, emp2, tache4);
        employeTacheService.create(et1);
        employeTacheService.create(et2);
        employeTacheService.create(et3);
        employeTacheService.create(et4);


        // 4️⃣ Recharger les entités avant de créer EmployeTache
        emp1   = employeService.findById(emp1.getId());
        emp2   = employeService.findById(emp2.getId());
        tache1 = tacheService.findById(tache1.getId());
        tache2 = tacheService.findById(tache2.getId());
        tache3 = tacheService.findById(tache3.getId());
        tache4 = tacheService.findById(tache4.getId());

        // 5️⃣ Création des EmployeTache


        // 6️⃣ Recharger projet1 pour les tests
        projet1 = projetService.findById(projet1.getId());
        emp1    = employeService.findById(emp1.getId());

        // ===================== TESTS =====================

        System.out.println("\n--- Tâches de " + emp1.getPrenom() + " " + emp1.getNom() + " ---");
        List<Tache> tachesEmp1 = employeService.findTachesByEmploye(emp1);
        for (Tache t : tachesEmp1) {
            System.out.println("  " + t.getId() + " | " + t.getNom() + " | " + t.getPrix() + " DH");
        }

        System.out.println("\n--- Projets de " + emp1.getPrenom() + " " + emp1.getNom() + " ---");
        List<Projet> projetsEmp1 = employeService.findProjetsByEmploye(emp1);
        for (Projet p : projetsEmp1) {
            System.out.println("  " + p.getId() + " | " + p.getNom());
        }

        System.out.println("\n--- Tâches du projet : " + projet1.getNom() + " ---");
        List<Tache> tachesProjet1 = projetService.findTachesByProjet(projet1);
        for (Tache t : tachesProjet1) {
            System.out.println("  " + t.getId() + " | " + t.getNom() + " | " + t.getPrix() + " DH");
        }

        System.out.println("\n--- Tâches réalisées avec dates réelles : " + projet1.getNom() + " ---");
        List<EmployeTache> tachesRealisees = projetService.findTachesRealiseesByProjet(projet1);
        for (EmployeTache et : tachesRealisees) {
            System.out.println("  " + et.getTache().getId() + " | " + et.getTache().getNom()
                    + " | Début : " + new SimpleDateFormat("dd/MM/yyyy").format(et.getDateDebutReelle())
                    + " | Fin : "   + new SimpleDateFormat("dd/MM/yyyy").format(et.getDateFinReelle()));
        }

        System.out.println("\n--- Tâches dont le prix > 1000 DH ---");
        List<Tache> tachesExpensives = tacheService.findTachesExpensives();
        for (Tache t : tachesExpensives) {
            System.out.println("  " + t.getId() + " | " + t.getNom() + " | " + t.getPrix() + " DH");
        }

        System.out.println("\n--- Tâches entre 01/01/2013 et 01/05/2013 ---");
        List<Tache> tachesBetween = tacheService.findTachesBetweenDates(
                sdf.parse("2013-01-01"),
                sdf.parse("2013-05-01")
        );
        for (Tache t : tachesBetween) {
            System.out.println("  " + t.getId() + " | " + t.getNom()
                    + " | Fin : " + new SimpleDateFormat("dd/MM/yyyy").format(t.getDateFin()));
        }

        System.out.println("\n=========== FIN TEST ===========");
    }
}