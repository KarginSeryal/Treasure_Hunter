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
        goldLost = 0;
        goldWon = 0;
        luck = 0;
    }

    public void gamble(){
        Scanner scanner = new Scanner(System.in);
        int target = 0;
        int bet;
        int guess;
        String choice;
        if(victim.getGold() < 1){
            System.out.println("Sorry sir, you can no longer participate duu to a lack of funds.");
            System.out.println("Please come back when you have more gold.");
        }

        bet = Integer.parseInt(scanner.nextLine());
        while(bet < 1){
            System.out.print("That is an invalid value, please place a bet of at least 1 gold: ");
            bet = Integer.parseInt(scanner.nextLine());
        }
        victim.changeGold(-bet);
        if(bet > 9){
            System.out.println("Go big or go home!");
        }
        for(int i = 0; i < 2; i++){
            target += (int) ((Math.random()*6)+1);
        }
        guess = Integer.parseInt(scanner.nextLine());
        if(guess == target){
            victim.changeGold((int) (bet * 1.5));
            System.out.println("We have a WINNER!!");
            System.out.println("You won your money back AND an extra " + (bet*1.5-bet) + " gold!!");
        }else if(guess >= target -2 && guess <= target + 2){
            victim.changeGold(bet);
            System.out.println("Maybe next time you will make money instead of just taking your money back.");
        }else{
            System.out.println("Maybe you should try again and win back the money you Lost.");
        }
        System.out.println("Would you like to play some more?(y/n)");
        choice = scanner.nextLine();
        if(choice.equals("y"))
    }


}
