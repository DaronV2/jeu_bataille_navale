package fr.daron.louis;

public class mapShips {

    private String[][] ShipMapPlayer = new String[5][5];

    public mapShips(){
        fill();
    }

    private void fill(){
        for (int i = 0; i < ShipMapPlayer.length; i++) {
            for (int j = 0; j < ShipMapPlayer[i].length; j++) {
                ShipMapPlayer[i][j] = "-";
            }
        }
    }

    public void showMap() {
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < ShipMapPlayer.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < ShipMapPlayer[i].length; j++) {
                System.out.print(ShipMapPlayer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkMapCoord(int un, int deux) {
        return un >= 0 && un < ShipMapPlayer.length && deux >= 0 && deux < ShipMapPlayer[0].length;
    }

    public boolean checkShipAtThisCase(int un, int deux) {
        return !ShipMapPlayer[un][deux].equals("@");
    }
    
    public void addNavire(int L, int C){
        ShipMapPlayer[L][C] = "@"; 
    }

    public boolean shipOrFall(int L, int C){
        if(ShipMapPlayer[L][C].equals("@")){
            ShipMapPlayer[L][C] = "X";
            return true;
        }else{
            return false;
        }
    }

    public void nothing(int L, int C){
        ShipMapPlayer[L][C] = "O";
    }

    public void touchedShip(int L, int C){
        ShipMapPlayer[L][C] = "X";
    }

    public boolean allShipsSunk(){
        for (int i = 0; i < ShipMapPlayer.length; i++){
            for (int j =0 ; j < ShipMapPlayer[i].length ; j++){
                if (ShipMapPlayer[i][j] == "@"){
                    return false;
                }
            }
        }
        return true;
    }
}
