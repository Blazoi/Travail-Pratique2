import ca.qc.bdeb.sim202.tp2.DePipe;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                jouer(nomfichier);
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
                afficherJoueurs(liste, plateau);
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
            if (nmbdejoueurs == 0) {
                liste.peek().setPremierJoueur();
            }
            nmbdejoueurs++;
        }
        return liste;
    }

    public static void tourmenu(DePipe de, Case[] plateau, Joueur joueur, LinkedList<Joueur> liste) {

//        afficher plateau;
        if (joueur.estPremierJoueur()) {
            afficherPlateau(plateau);
        }

        System.out.println("\033[93mC'est à " + joueur.getNom() + " de jouer\033[39m");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Lancer le dé en appuyant sur « \033[94mEntrée\033[39m ».");
        System.out.println("Quitter la partie ( \033[94mQ\033[39m )");
        System.out.println("Mettre fin à la partie ( \033[94mM\033[39m )");
        String choix = sc.nextLine();

        // validation du choix de l'utilisateur
        while (!choix.isEmpty() && !choix.equalsIgnoreCase("q") && !choix.equalsIgnoreCase("m")) {
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
                Case.survolerCase(plateau, de.getDernierevaleur(), joueur);
                break;
        }

    }

    private static void afficherJoueurs(LinkedList<Joueur> joueurs, Case[] plateau) {
        System.out.println("\033[96m************** Les Joueurs **************");
        for (int i = 0; i < joueurs.size(); i++) {
            System.out.println(joueurs.get(i).getNom() + " est sur la case " + plateau[joueurs.get(i).getPosition()].getNom() + " et possède $" + joueurs.get(i).getArgent());
        }
        System.out.println("Le prochain joueur est " + joueurs.peek() + ".");
    }

    private static void afficherPlateau(Case[] plateau) {
        System.out.println("\033[95m************** Le Plateau **************");
        for (int i = 0; i < plateau.length; i++) {
            String type = plateau[i].getType();
            switch (type) {
                case "D", "Tx", "P" -> {
                    System.out.print(i);
                    System.out.print(" " + type);
                    System.out.print(" " + plateau[i].getNom());
                    System.out.print(" " + plateau[i].getDescription());
                    System.out.print(" " + plateau[i].getMontantdelacase());
                    System.out.println();
                }
                case "SP" -> {
                    System.out.print(i);
                    System.out.print(" " + type);
                    System.out.print(" " + plateau[i].getNom());
                    System.out.print("  : " + plateau[i].getPrix());
                    System.out.print(" Loyer : Valeur du dé * 10");
                    System.out.println();
                }
                case "T" -> {
                    System.out.print(i);
                    System.out.print(" " + type);
                    System.out.print(" " + plateau[i].getNom());
                    System.out.print(" Valeur : " + plateau[i].getPrix());
                    System.out.print(" Loyer : " + plateau[i].getLoyer());
                    System.out.println();
                }
            }
        }
        System.out.println("\033[39m");
    }

    public static void quitter() {
        System.out.println("""
                Vous avez quitté Montrealopoly
                Merci d'avoir joué!
                """);
        System.exit(0);
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