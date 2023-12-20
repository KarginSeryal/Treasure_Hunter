/**
 * The Shop class controls the cost of the items in the Treasure Hunt game.<p>
 * The Shop class also acts as a go between for the Hunter's buyItem() method.<p>
 */
import java.util.Scanner;


public class Shop
{
    // constants
    private static int WATER_COST = 2;
    private static int ROPE_COST = 4;
    private static int MACHETE_COST = 6;
    private static  int HORSE_COST = 12;
    private static  int BOAT_COST = 20;

    // instance variables
    private double markdown;
    private Hunter customer;


    //Constructor
    public Shop(double markdown, boolean easy)
    {
        if(easy){
            WATER_COST /=2;
            ROPE_COST /=2;
            MACHETE_COST /=2;
            HORSE_COST /=2;
            BOAT_COST /=2;
        }
        this.markdown = markdown;
        customer = null;
    }


    /** method for entering the shop
     * @param hunter  the Hunter entering the shop
     * @param buyOrSell  String that determines if hunter is "B"uying or "S"elling
     */
    public void enter(Hunter hunter, String buyOrSell)
    {
        customer = hunter;


        Scanner scanner = new Scanner(System.in);
        if (buyOrSell.equals("B") || buyOrSell.equals("b"))
        {
            System.out.println("WELCOME TO THE SHOP! WE HAVE THE FINEST WARES IN TOWN");
            System.out.println("CURRENTLY WE HAVE THE FOLLOWING ITEMS:");
            System.out.println("");
            System.out.println(inventory());
            System.out.print("WHAT'RE YOU LOOKIN' TO BUY? ");
            String item = scanner.nextLine();
            int cost = checkMarketPrice(item, true);
            if (cost == 0)
            {
                System.out.println("WE AIN'T GOT NONE OF THOSE.");
            }
            else
            {
                System.out.print("IT'LL COST YOU " + cost + " GOLD. BUY IT (Y/N)? ");
                String option = scanner.nextLine();


                if (option.equals("y") || option.equals("Y"))
                {
                    buyItem(item);
                }
            }
        }
        else
        {
            System.out.println("WHAT'RE YOU LOOKIN' TO SELL? ");
            System.out.print("YOU CURRENTLY HAVE THE FOLLOWING ITEMS: " + customer.getInventory());
            String item = scanner.nextLine();
            int cost = checkMarketPrice(item, false);
            if (cost == 0)
            {
                System.out.println("WE DON'T WANT NONE OF THOSE.");
            }
            else
            {
                System.out.print("IT'LL GET YOU " + cost + " GOLD. SELL IT (Y/N)? ");
                String option = scanner.nextLine();


                if (option.equals("y") || option.equals("Y"))
                {
                    sellItem(item);
                }
            }
        }
    }
    public void adjustForEasyMode()
    {
        WATER_COST /= 2;
        ROPE_COST /= 2;
        MACHETE_COST /= 2;
        HORSE_COST /= 2;
        BOAT_COST /= 2;
    }


    public static int getWaterCost() {
        return WATER_COST;
    }

    public static int getRopeCost() {
        return ROPE_COST;
    }

    public static int getMacheteCost() {
        return MACHETE_COST;
    }

    public static int getHorseCost() {
        return HORSE_COST;
    }

    public static int getBoatCost() {
        return BOAT_COST;
    }

    /** A method that returns a string showing the items available in the shop (all shops sell the same items)
     *
     * @return  the string representing the shop's items available for purchase and their prices
     */
    public String inventory()
    {
        String str = "Water: " + WATER_COST + " gold\n";
        str += "Rope: " + ROPE_COST + " gold\n";
        str += "Machete: " + MACHETE_COST + " gold\n";
        str += "Horse: " + HORSE_COST + " gold\n";
        str += "Boat: " + BOAT_COST + " gold\n";


        return str;
    }


    /**
     * A method that lets the customer (a Hunter) buy an item.
     * @param item The item being bought.
     */
    public void buyItem(String item)
    {
        int costOfItem = checkMarketPrice(item, true);
        if (customer.buyItem(item, costOfItem))
        {
            System.out.println("YE' GOT YERSELF A " + item + " COME AGAIN SOON.");
        }
        else
        {
            System.out.println("HMM, EITHER YOU DON'T HAVE ENOUGH GOLD OR YOU'VE ALREADY GOT ONE OF THOSE!");
        }
    }


    /**
     * A pathway method that lets the Hunter sell an item.
     * @param item The item being sold.
     */
    public void sellItem(String item)
    {
        int buyBackPrice = checkMarketPrice(item, false);
        if (customer.sellItem(item, buyBackPrice))
        {
            System.out.println("Pleasure doin' business with you.");
        }
        else
        {
            System.out.println("Stop stringin' me along!");
        }
    }


    /**
     * Determines and returns the cost of buying or selling an item.
     * @param item The item in question.
     * @param isBuying Whether the item is being bought or sold.
     * @return The cost of buying or selling the item based on the isBuying parameter.
     */
    public int checkMarketPrice(String item, boolean isBuying)
    {
        if (isBuying)
        {
            return getCostOfItem(item);
        }
        else
        {
            return getBuyBackCost(item);
        }
    }


    /**
     * Checks the item entered against the costs listed in the static variables.
     *
     * @param item The item being checked for cost.
     * @return The cost of the item or 0 if the item is not found.
     */
    public int getCostOfItem(String item)
    {
        if (item.equals("Water"))
        {
            return WATER_COST;
        }
        else if (item.equals("Rope"))
        {
            return ROPE_COST;
        }
        else if (item.equals("Machete"))
        {
            return MACHETE_COST;
        }
        else if (item.equals("Horse"))
        {
            return HORSE_COST;
        }
        else if (item.equals("Boat"))
        {
            return BOAT_COST;
        }
        else
        {
            return 0;
        }
    }


    /**
     * Checks the cost of an item and applies the markdown.
     *
     * @param item The item being sold.
     * @return The sell price of the item.
     */
    public int getBuyBackCost(String item)
    {
        int cost = (int)(getCostOfItem(item) * markdown);
        return cost;
    }
}
