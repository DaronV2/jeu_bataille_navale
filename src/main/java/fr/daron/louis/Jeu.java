package fr.daron.louis;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jeu {
    
    private static boolean gagne = false;
    
    private static void placerNaviresJoueur(Player j1, Player j2) {
        j1.placeShips();
        j2.placeShips();
    }
    
    private static void tirez(Player j1, Player j2){
        mapShips mapAttaqueJoueur1 = new mapShips();
        mapShips mapAttaqueJoueur2 = new mapShips();
        while (gagne == false){
            System.out.print("JOUEUR 1, ");
            String nombres = entrerCoordonees();
            String[] nb = nombres.split(" ");
            int L = Integer.parseInt(nb[0]);
            int C = Integer.parseInt(nb[1]);
            while (! mapAttaqueJoueur1.checkMapCoord(L, C)){
                nombres = entrerCoordonees();
                nb = nombres.split(" ");
                L = Integer.parseInt(nb[0]);
                C = Integer.parseInt(nb[1]);
            }
            if (j2.shipOrFall(L,C)){
                mapAttaqueJoueur1.touchedShip(L, C);
                System.out.println("LE JOUEUR 1 A COULÉ UN NAVIRE");
                mapAttaqueJoueur1.showMap();
                if(j2.allShipSunk()){
                    gagne = true;
                    System.out.println("ET C'EST LA VICTOIRE POUR LE JOUEUR NUMERO 1");
                    afficherFin(mapAttaqueJoueur1,mapAttaqueJoueur2);
                }
            }else{
                System.out.println("LE JOUEUR 1 NE VISAIT RIEN APPAREMMENT");
                mapAttaqueJoueur1.nothing(L, C);
                mapAttaqueJoueur1.showMap();
            }
            if ( ! gagne){
                System.out.println("AU TOUR DU JOUEUR 2, ");
                nombres = entrerCoordonees();
                nb = nombres.split(" ");
                L = Integer.parseInt(nb[0]);
                C = Integer.parseInt(nb[1]);
                while (! mapAttaqueJoueur1.checkMapCoord(L, C)){
                    nombres = entrerCoordonees();
                    nb = nombres.split(" ");
                    L = Integer.parseInt(nb[0]);
                    C = Integer.parseInt(nb[1]);
                }
                if (j1.shipOrFall(L,C)){
                    mapAttaqueJoueur2.touchedShip(L, C);
                    System.out.println("LE JOUEUR 2 A COULÉ UN NAVIRE");
                    mapAttaqueJoueur2.showMap();
                    if(j1.allShipSunk()){
                        gagne = true;
                        System.out.println("ET C'EST LA VICTOIRE POUR LE JOUEUR NUMERO 2");
                        afficherFin(mapAttaqueJoueur1,mapAttaqueJoueur2);
                    }
                }else{
                    System.out.println("LE JOUEUR 2 NE VISAIT RIEN APPAREMMENT");
                    mapAttaqueJoueur2.nothing(L, C);
                    mapAttaqueJoueur2.showMap();
                }
            }
        }
    }
    
    private static String entrerCoordonees(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez les coordonnées du navire à couler  : ");
        String res =  scanner.nextLine();
        if(checkPattern(res)){
            return res;
        }else{
            while(!checkPattern(res)){
                res = entrerCoordonees();
            }
        }
        return res;
    }

    private static boolean checkPattern(String nombre){
        Pattern regex = Pattern.compile("[0-9] [0-9]");
        Matcher match = regex.matcher(nombre);
        return match.find();
    }
    
    public static void Jouer(){
        Player j1 = new Player(1);
        Player j2 = new Player(2);
        placerNaviresJoueur(j1,j2);
        tirez(j1,j2);
    }

    private static void afficherFin(mapShips map1, mapShips map2){
        System.out.println("VOICI LES GRILLES DE FIN :");
        System.out.println("Grille du joueur 1 : ");
        map1.showMap();
        System.out.println();
        System.out.println("Grille du joueur 2 : ");
        map2.showMap();
    }
}
