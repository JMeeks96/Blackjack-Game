/**Copyright 2013
 * Property of Justin Meeken
 */
import java.util.Scanner;
public class Blackjack
{
    public static void main(String[] args)
    {
        Boolean playAgain = true;
        int balance = 1000;//for wagering
        while (playAgain)
        {
            //must fix if player draws two aces
            int bet = 0;//bet amount
            String answer;//play again
            Boolean win = null;//keeps track of player win or loss
            Scanner in = new Scanner(System.in);//constructs a Scanner
            Card card = new Card();//constructs a deck of cards
            String move;//hit or stay
            int playerValue = 0;//value of all cards player has drawn
            int dealerValue = 0;//value of all cards dealer has drawn
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Welcome to Justin's game of Blackjack!");
            if (balance <= 0)//checks if player ran out of money
            {
                System.out.println("It appears you ran out of chips.\nThe casino gave you 1000 chips to help support your gambling addiction.");
                balance += 1000.00;
            }
            System.out.println("You have " + balance + " chips.");//displays number of chips
            do
            {
                System.out.print("Enter the amount you would like to bet: ");
                while (!in.hasNextInt())//checks if the input is a number
                {
                   System.out.print("Enter a valid number: ");
                   in.next();
                }
                bet = in.nextInt();
                if (bet > balance)
                {
                    System.out.println("You cannot enter more money than you have.");
                }
                if (bet < 0)
                {
                    System.out.println("You cannot bet a negative amount of money.");
                }
            }
            while (bet > balance || bet < 0);//makes sure bet isn't negative or greater than player balance
            card.draw();//draws a new card from deck
            System.out.print("\nYou drew a " + card.getName() + " and ");//displays cards drawn
            playerValue = playerValue + card.getValue();//adds card drawn's value to total player value
            card.draw();
            System.out.println(card.getName() + ".");
            playerValue = playerValue + card.getValue();
            if (playerValue == 22)//in case player draws two aces to start
            {
                playerValue = 12;
            }
            System.out.println("Your total is " + playerValue + ".");
            card.draw();
            System.out.println("\nThe dealer drew a " + card.getName() + ", and the other card is hidden.\nThe dealers total is hidden too.");
            dealerValue = dealerValue + card.getValue();
            card.draw();
            String hiddenCard = card.getName();
            dealerValue = dealerValue + card.getValue();
            if (playerValue == 21 && dealerValue == 21)//checks if both players got a blackjack
            {
                System.out.println("\nThe dealer got a blackjack.");
                win = false;
            }
            else if (playerValue == 21)//checks if player got a blackjack
            {
                System.out.println("\nYou got a blackjack!");
                balance += bet/2;//gives player 1.5 times bet for blackjack
                win = true;
            }
            else if (dealerValue == 21)//checks if dealer got a blackjack
            {
                System.out.println("\nThe dealer got a blackjack.");
                win = false;
            }
            else//if no blackjacks, checks if people want to hit or stay
            {
                boolean hit = true;
                while (playerValue < 21 && hit)//checks to make sure player can hit
                {
                    do
                    {
                        System.out.print("\nWould you like to hit or stay? ");
                        answer = in.next().toLowerCase();
                    }
                    while (!(answer.equals("hit") || answer.equals("stay")));//validates input
                    if (answer.equals("hit"))//checks if player wants to hit
                    {
                        card.draw();
                        playerValue = playerValue + card.getValue();
                        System.out.println("You drew a " + card.getName() + ".");
                        System.out.println("Your total is " + playerValue + ".");
                        if (playerValue > 21)//checks if player busted
                        {
                            System.out.println("You busted.");
                            win = false;
                        }
                    }
                    else
                    {
                        hit = false;
                    }
                }
                if (win == null)//checks if player has lost yet by busting
                {
                    System.out.println("\nOkay, dealers turn.");
                    System.out.println("The dealer's hidden card was a " + hiddenCard + ".");
                    System.out.println("The dealers total was " + dealerValue + ".");
                }
                while (dealerValue < 17 && win == null)//checks if dealer will hit or stay
                {
                    System.out.println("\nThe dealer chooses to hit.");
                    card.draw();
                    System.out.println("The dealer drew a " + card.getName() + ".");
                    dealerValue = dealerValue + card.getValue();
                    System.out.println("The dealer's total is " + dealerValue + ".");
                    if (dealerValue > 21)//checks if dealer busted
                    {
                        System.out.println("The dealer busted.");
                        win = true;
                    }
                }
                if (win == null)//checks who won if no one has won yet
                {
                    System.out.println("\nThe dealer stays.");
                    if (playerValue > dealerValue)
                    {
                        win = true;
                    }
                    else if (dealerValue > playerValue)
                    {
                        win = false;
                    }
                    else
                    {
                        win = false;
                    }
                }
            }
            System.out.println("\nThe dealer's total is " + dealerValue + ".");//displays dealer total
            System.out.println("Your total is " + playerValue + ".");//displays player total
            if (win)//displays if player won
            {
                System.out.println("\nYou win!");
                balance += bet;
                System.out.printf("You have " + balance + " chips.");
            }
            else if (!win)//displays if player lost
            {
                System.out.println("\nThe dealer won.");
                balance -= bet;
                System.out.println("You have " + balance + " chips.");
            }
            do//asks if player wants to play again
            {
                System.out.print("\nWould you like to play again? ");
                answer = in.next().toLowerCase();
            }
            while (!(answer.equals("yes") || answer.equals("no")));//validates input
            if (answer.equals("yes"))//checks player answer is yes
            {
                playAgain = true;
            }
            else//checks if player answer is no and tells player how much he or she left with
            {
                playAgain = false;
                System.out.println("You walked away with " + balance + " chips.");
            }
        }
    }
}