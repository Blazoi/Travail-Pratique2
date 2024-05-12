import ca.qc.bdeb.sim202.tp2.DePipe;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
        Case[] rnd = new Case[2];
        tourmenu(new DePipe(), rnd, new Joueur(""));
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
                String nomfichier = "src/plateau.bin";//sc.next();
                //Vérification du nom du fichier
//                if (!nomfichier.endsWith(".bin")) {
//                    nomfichier = nomfichier + ".bin";
//                }
//                if (!nomfichier.startsWith("src/")) {
//                    nomfichier = "src/" + nomfichier;
//                }
                jouer("src/plateau.bin");

                break;
            case 2:

                break;
            case 3:
                System.out.println("Vous avez quitté Montrealopoly. Au revoir !");
                System.exit(0);
                break;
        }

    }

    public static void jouer(String nomfichier) {
        //Initialiser le plateau
        Case[] plateau = initialiserTableau(nomfichier);

        //Initialiser les joueurs
        LinkedList<Joueur> liste = listeDeJoueurs();
        while (true) {
            tourmenu(new DePipe(), plateau, liste.get(0));
            liste.add(liste.get(0));
            liste.remove(0);
        }
    }

    public static Case[] initialiserTableau(String nomFichier) {
        Case[] plateau = new Case[15];
        try (DataInputStream reader = new DataInputStream(new FileInputStream(nomFichier))) {
            for (int i = 0; i < 15; i++) {
                String type = reader.readUTF();
                String nom = reader.readUTF();
                String description = reader.readUTF();
                int valeur1 = reader.readInt();
                int valeur2 = reader.readInt();

                switch (type) {
                    case "D" -> plateau[i] = new Depart(type, nom, description, valeur1);
                    case "T" -> plateau[i] = new Terrains(type, nom, valeur1, valeur2);
                    case "Tx" -> plateau[i] = new Taxe(type, nom, description, valeur1);
                    case "P" -> plateau[i] = new Stationnement(type, nom, description, valeur1);
                    case "SP" -> plateau[i] = new Servicepublic(type, nom, valeur1);
                    default -> throw new IllegalArgumentException("Type de case inconnu : " + type);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plateau;
    }


    private static String readString(DataInputStream reader) throws IOException {
        int length = reader.readByte() & 0xFF; // On lit la longueur de la chaîne
        byte[] bytes = new byte[length];
        reader.readFully(bytes);
        return new String(bytes, "UTF-8");
    }


    public static LinkedList<Joueur> listeDeJoueurs() {
        Scanner sc = new Scanner(System.in);
        LinkedList<Joueur> liste = new LinkedList<Joueur>();
        int nmbdejoueurs = 0;
        while (nmbdejoueurs < 5) {
            //Nom du joueur
            System.out.print("Joueur " + (nmbdejoueurs + 1) + ": ");
            String ligne = sc.nextLine();

            //Minimum de 2 joueurs
            if (ligne.isBlank()) {
                if (nmbdejoueurs < 2) {
                    System.out.println("Vous avex besoin d'au moins 2 joueurs.");
                    nmbdejoueurs--;
                } else {
                    return liste;
                }
            }

            //Ajout du joueurs à la liste
            liste.add(new Joueur(ligne));
            nmbdejoueurs++;
        }
        return liste;
    }

    public static void tourmenu(DePipe de, Case[] plateau, Joueur joueur) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Lancer le dé en appuyant sur « \033[94mEntrée\033[39m ».");
        System.out.println("\033[94mQ\033[39muitter la partie.");
        System.out.println("\033[94mM\033[39mettre fin à la partie.");
        String choix = sc.nextLine();

        if (choix.isBlank()) {
            de.lancer();
            System.out.println("Le dé est tombé sur " + de.getDernierevaleur() + "!");
            avancer(plateau, de.getDernierevaleur(), joueur);

            return;
        } else if (choix.equalsIgnoreCase("Q")) {

        } else if (choix.equalsIgnoreCase("M")) {

        }
    }

    public static void avancer(Case[] plateau, int resultatde, Joueur joueur) {

    }
//    public static void tab() {
//        try (DataInputStream reader = new DataInputStream(new FileInputStream("src/plateau.bin"))) {
//            int i = 1;
//            while (i != 15) {
//                String s = reader.readUTF();
//                switch (s) {
//                    case "D", "Tx", "P" -> {
//                        System.out.println(s);
//                        System.out.println(reader.readUTF());
//                        System.out.println(reader.readUTF());
//                        System.out.println(reader.readInt());
//                        System.out.println();
//                    }
//                    case "SP" -> {
//                        System.out.println(s);
//                        System.out.println(reader.readUTF());
//                        System.out.println("Valeur : " + reader.readInt());
//                        System.out.println("Loyer : Valeur du dé * 10");
//                        System.out.println();
//                    }
//                    case "T" -> {
//                        System.out.println(s);
//                        System.out.println(reader.readUTF());
//                        System.out.println("Valeur : " + reader.readInt());
//                        System.out.println("Loyer : " + reader.readInt());
//                        System.out.println();
//                    }
//                }
//                i++;
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
