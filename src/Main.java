import ca.qc.bdeb.sim202.tp2.DePipe;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (RandomAccessFile rdm = new RandomAccessFile("src/plateau.bin","rw")){
            while (true) {
                System.out.println(rdm.readUTF());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        // Entrer le choix du joueur
        System.out.println("**Montrealopoly**");
        System.out.println("1- Jouer une nouvelle partie.");
        System.out.println("2- Charger une partie précédente.");
        System.out.println("3- Quitter le jeu.");
        int choix = 1; // = sc.nextInt();
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
                try {
                    DataInputStream reader = new DataInputStream(new FileInputStream(nomfichier));
                    System.out.println(reader.read());



                } catch (FileNotFoundException f) {
                    System.out.println("Fichier inexistant");
                } catch (IOException e) {
                    System.out.println("Erreur d'accès au fichier");
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    public static void jouer(String nomfichier) {
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(nomfichier));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier inexistant.");
        }
    }

    public static void tab() {
        try (RandomAccessFile rdm = new RandomAccessFile("src/plateau.bin","rw")){
            System.out.println(rdm.read());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}