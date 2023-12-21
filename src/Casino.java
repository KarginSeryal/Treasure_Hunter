import java.util.Scanner;

public class Casino {
    private int goldWon;
    private int goldLost;
    static int luck;
    private double cashback;
    private Hunter victim;
    public Casino(boolean hardMode, Hunter victim){
        cashback = 1;
        if(hardMode){
            cashback = 0.5;
        }
        this.victim = victim;
    }

    public void gamble(){
        Scanner scanner = new Scanner(System.in);
        int bet;
        if(victim.getGold() < 1){
            System.out.println("Sorry sir, you can no longer participate duu to a lack of funds.");
            System.out.println("Please come back when you have more gold.");

        }

        bet = Integer.parseInt(scanner.nextLine());

    }


}
