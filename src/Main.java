import ca.qc.bdeb.sim202.tp2.DePipe;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        tab();

        Case[] rnd = new Case[2];
        LinkedList<Joueur> liste = new LinkedList<>();
        tourmenu(new DePipe(), rnd, new Joueur("asd"), liste);
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        // Entrer le choix du joueur
        System.out.println("**Montrealopoly**");
        System.out.println("1- Jouer une nouvelle partie.");
        System.out.println("2- Charger une partie précédente.");
        System.out.println("3- Quitter le jeu.");
        int choix = sc.nextInt();
        while (choix != 1 && choix != 2 && choix != 3) {
            System.out.println("Veuillez saisir une entrée valide");
            choix = sc.nextInt();
        }
        //
        switch (choix) {
            case 1:
                System.out.println("Entrez le nom du fichier binaire.");
                String nomfichier = sc.next();
                //Vérification du nom du fichier
                if (!nomfichier.endsWith(".bin")) {
                    nomfichier = nomfichier + ".bin";
                }
                if (!nomfichier.startsWith("src/")) {
                    nomfichier = "src/" + nomfichier;
                }
                jouer("src/plateau.bin");

                break;
            case 2:
                break;
            case 3:
                quitter();
                break;
        }

    }

    public static void jouer(String nomfichier) {
        //Initialiser le plateau
        Case[] plateau = initialiserTableau(nomfichier);
        if (validerPlateau(plateau)) {

            //Initialiser les joueurs
            LinkedList<Joueur> liste = listeDeJoueurs();
            while (true) {
                tourmenu(new DePipe(), plateau, liste.get(0), liste);
                liste.addLast(liste.pop());
            }
        } else {
            System.err.println("Le fichier binaire du plateau n'est pas valide");
        }
    }

    private static boolean validerPlateau(Case[] plateau) {
        boolean departValide = true;
        boolean auMoinsUnTx = false;
        boolean auMoinsUnP = false;
        boolean auMoinsUnSP = false;
        boolean auMoinsUnT = false;
        for (int i = 0; i < 15; i++) {
            if (plateau[0].getType() != "D") {
                departValide = false;
            }
            if (i > 0) {
                if (plateau[i].getType() == "D") {
                    departValide = false;
                }
            }
            switch (plateau[i].getType()) {
                case "Tx":
                    auMoinsUnTx = true;
                    break;
                case "P":
                    auMoinsUnP = true;
                    break;
                case "SP":
                    auMoinsUnSP = true;
                    break;
                case "T":
                    auMoinsUnT = true;
                    break;
            }
        }
        return departValide && auMoinsUnTx && auMoinsUnP && auMoinsUnSP && auMoinsUnT;

    }

    public static Case[] initialiserTableau(String nomfichier) {
        Case[] plateau = new Case[15];
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(nomfichier));
            for (int i = 0; i < 15; i++) {
                String typeDeCase = reader.readUTF();
                switch (typeDeCase) {
                    case "D" -> {
                        plateau[i] = new Depart("D", reader.readUTF(), reader.readUTF(), reader.readInt());
                    }
                    case "Tx" -> {
                        plateau[i] = new Taxe("Tx", reader.readUTF(), reader.readUTF(), reader.readInt());
                    }
                    case "P" -> {
                        plateau[i] = new Stationnement("P", reader.readUTF(), reader.readUTF(), reader.readInt());
                    }
                    case "SP" -> {
                        plateau[i] = new Servicepublic("SP", reader.readUTF(), reader.readInt());
                    }
                    case "T" -> {
                        plateau[i] = new Terrains("T", reader.readUTF(), reader.readInt(), reader.readInt());

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return plateau;
    }


    public static LinkedList<Joueur> listeDeJoueurs() {
        Scanner sc = new Scanner(System.in);
        LinkedList<Joueur> liste = new LinkedList<Joueur>();
        int nmbdejoueurs = 0;
        while (nmbdejoueurs < 5) {
            //Nom du joueur
            System.out.print("Joueur " + (nmbdejoueurs + 1) + ": ");
            String nom = sc.nextLine();

            //Minimum de 2 joueurs
            if (nom.isBlank()) {
                if (nmbdejoueurs < 2) {
                    System.out.println("Vous avez besoin d'au moins 2 joueurs.");
                    nmbdejoueurs--;
                } else {
                    return liste;
                }
            }

            //Ajout du joueurs à la liste
            liste.add(new Joueur(nom));
            if (nmbdejoueurs == 0)
                liste.peek().setPremierJoueur();
            nmbdejoueurs++;
            nmbdejoueurs++;
        }
        return liste;
    }

    public static void tourmenu(DePipe de, Case[] plateau, Joueur joueur, LinkedList<Joueur> liste) {

//        afficher plateau;
        if (joueur.estPremierJoueur()) {
            afficherPlateau(plateau);
        }
//        afficherJoueurs(liste);

        System.out.println("\033[93mC'est à " + joueur.getNom() + " de jouer\033[39m");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Lancer le dé en appuyant sur « \033[94mEntrée\033[39m ».");
        System.out.println("Quitter la partie ( \033[94mQ\033[39m )");
        System.out.println("Mettre fin à la partie ( \033[94mM\033[39m )");
        String choix = sc.nextLine();

        // validation du choix de l'utilisateur
        while (!choix.equals("") && !choix.equalsIgnoreCase("q") && !choix.equalsIgnoreCase("m")) {
            System.out.println("Veuillez saisir une entrée valide");
            choix = sc.nextLine();
        }
        switch (choix.toLowerCase()) {
            case "q":
                quitter();
                break;
            case "m":
                //Enregistrer les données dans un fichier binaire
                //Quitter
                quitter();
                break;
            case "":
                de.lancer();
                System.out.println("Le dé est tombé sur " + de.getDernierevaleur() + "!");
                avancer(plateau, de.getDernierevaleur(), joueur);
                break;
        }

    }

    private static void afficherJoueurs(LinkedList<Joueur> liste) {
        System.out.println("\033[96m************** Les Joueurs **************");
        for (int i = 0; i < liste.size(); i++) {
            System.out.print(liste.get(i));
            //case
            //argent
        }


    }

    private static void afficherPlateau(Case[] plateau) {
        System.out.println("\033[95m************** Le Plateau **************");
        for (int i = 0; i < 15; i++) {




            if (plateau[i].getType() == "D" || plateau[i].getType() == "Tx") {

                System.out.println(i + 1 + " : " + plateau[i].getNom() + " " + plateau[i].getDescription() + " "
                        + plateau[i].getMontantdelacase());

            } else if (plateau[i].getType() == "P") {

                System.out.println(i + 1 + " : " + plateau[i].getNom() + " " + plateau[i].getDescription());

            } else if (plateau[i].getType() == "SP" || plateau[i].getType() == "T") {

                System.out.print(i + 1 + " : " + plateau[i].getNom() + " " + plateau[i].getPrix() + " "
                        + plateau[i].getLoyer());
                //proprietaire
                System.out.println();

            }
        }
        System.out.println("\033[39m");
    }


    private static void quitter() {
        System.out.println("""
                Vous avez quitté Montrealopoly
                Merci d'avoir joué!
                """);
        System.exit(0);
    }

    public static void avancer(Case[] plateau, int resultatde, Joueur joueur) {
        //Un attribut pour la position dans joueur.
        //Switch pour déterminer les actions selon la case
        //D-Gagner de l'argent
        //Le reste - Perdre de l'argent
        for (int i = joueur.getPosition(); i != joueur.getPosition() + resultatde; i++) {
            joueur.setPosition(joueur.getPosition() + 1);
            String type = plateau[i].getType();
                switch (type) {
                    case "D", "Tx", "P" -> {
                        System.out.println(type);
                        System.out.println(plateau[i].getNom());
                        System.out.println(plateau[i].getDescription());
                        System.out.println(plateau[i].getMontantdelacase());
                        System.out.println();
                    }
                    case "SP" -> {
                        System.out.println(type);
                        System.out.println(plateau[i].getNom());
                        System.out.println("Valeur : " + plateau[i].getPrix());
                        System.out.println("Loyer : Valeur du dé * 10");
                        System.out.println();
                    }
                    case "T" -> {
                        System.out.println(type);
                        System.out.println(plateau[i].getNom());
                        System.out.println("Valeur : " + plateau[i].getPrix());
                        System.out.println("Loyer : " + plateau[i].getLoyer());
                        System.out.println();
                    }
                }
                i++;
            }

        }

    public static void tab() {
        try (DataInputStream reader = new DataInputStream(new FileInputStream("src/plateau.bin"))) {
            int i = 1;
            while (i != 15) {
                String s = reader.readUTF();
                switch (s) {
                    case "D", "Tx", "P" -> {
                        System.out.println(s);
                        System.out.println(reader.readUTF());
                        System.out.println(reader.readUTF());
                        System.out.println(reader.readInt());
                        System.out.println();
                    }
                    case "SP" -> {
                        System.out.println(s);
                        System.out.println(reader.readUTF());
                        System.out.println("Valeur : " + reader.readInt());
                        System.out.println("Loyer : Valeur du dé * 10");
                        System.out.println();
                    }
                    case "T" -> {
                        System.out.println(s);
                        System.out.println(reader.readUTF());
                        System.out.println("Valeur : " + reader.readInt());
                        System.out.println("Loyer : " + reader.readInt());
                        System.out.println();
                    }
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}