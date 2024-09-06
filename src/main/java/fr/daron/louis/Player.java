package fr.daron.louis;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    private int playerNumber;

    private static Scanner scanner = new Scanner(System.in);

    private mapShips playerMap = new mapShips();

    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        this.playerMap = playerMap;
    }

    private void fillPlayerMap() {
        System.out.println("JOUEUR " + this.playerNumber + ", entrez les coordonnes de vos navires, sous la forme \"L C\" \n" +
                "(L représente la ligne entre 0 et 4 et C la colonne entre 0 et 4).");
        for (int i = 1; i <= 5; i++) {
            String number = inputCoord(i);
            boolean patternRespect = checkPattern(number);
            while (patternRespect == false){
                number = inputCoord(i);
                patternRespect = checkPattern(number);
            }
            String[] nb = number.split(" ");
            boolean checkCoord = playerMap.checkMapCoord(Integer.parseInt(nb[0]), Integer.parseInt(nb[1]));
            while (!checkCoord) {
                System.out.println("Coordonnées invalides. Veuillez réessayer.");
                number = inputCoord(i);
                nb = number.split(" ");
                checkCoord = playerMap.checkMapCoord(Integer.parseInt(nb[0]), Integer.parseInt(nb[1]));
            }

            boolean checkShip = playerMap.checkShipAtThisCase( Integer.parseInt(nb[0]), Integer.parseInt(nb[1]));
            while (!checkShip) {
                System.out.println("Vous avez déja un navire a cet endroit ! ");
                number = inputCoord(i);
                nb = number.split(" ");
                checkShip = playerMap.checkShipAtThisCase( Integer.parseInt(nb[0]), Integer.parseInt(nb[1]));
            }

            playerMap.addNavire(Integer.parseInt(nb[0]),Integer.parseInt(nb[1]));
            playerMap.showMap();
        }
    }

    public void placeShips() {
        fillPlayerMap();
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private static boolean checkPattern(String nombre){
        Pattern regex = Pattern.compile("[0-9] [0-9]");
        Matcher match = regex.matcher(nombre);
        return match.find();
    }

    private static String inputCoord(int numeroNavire){
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez les coordonnées du navire " + numeroNavire + " : ");
        return scanner.nextLine();
    }

    public boolean shipOrFall(int L, int C){
        return playerMap.shipOrFall(L, C);
    }

    public boolean allShipSunk(){
        return playerMap.allShipsSunk();
    }
}

