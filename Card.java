/**Copyright 2013
 * Property of Justin Meeken
 */
import java.util.Random;
/**
*A card deck that a user can draw a random card from without the card repeating. 
*/
public class Card
{
    private String cardName;
    private int value;
    private String previousCards;
    Random gen = new Random();
    /**
    *Constructs a randomly shuffled deck of cards.
    */
    public Card()
    {
        cardName = null;
        value = 0;
        previousCards = "";
    }
    /**
    *Draws a card that has not been previously drawn from a deck. 
    */
    public void draw()
    {
        do
        {
            int num = gen.nextInt(13) + 1;
            if (num == 1)
            {
                cardName = "Ace";
                value = 11;
            }
            else if (num == 2)
            {
                cardName = "Two";
                value = 2;
            }
            else if (num == 3)
            {
                cardName = "Three";
                value = 3;
            }
            else if (num == 4)
            {
                cardName = "Four";
                value = 4;
            }
            else if (num == 5)
            {
                cardName = "Five";
                value = 5;
            }
            else if (num == 6)
            {
                cardName = "Six";
                value = 6;
            }
            else if (num == 7)
            {
                cardName = "Seven";
                value = 7;
            }
            else if (num == 8)
            {
                cardName = "Eight";
                value = 8;
            }
            else if (num == 9)
            {
                cardName = "Nine";
                value = 9;
            }
            else if (num == 10)
            {
                cardName = "Ten";
                value = 10;
            }
            else if (num == 11)
            {
                cardName = "Jack";
                value = 10;
            }
            else if (num == 12)
            {
                cardName = "Queen";
                value = 10;
            }
            else if (num == 13)
            {
                cardName = "King";
                value = 10;
            }
            int suit = gen.nextInt(4)+1;
            if (suit == 1)
            {
                cardName = cardName + " of Spades";
            }
            else if (suit == 2)
            {
                cardName = cardName + " of Clubs";
            }
            else if (suit == 3)
            {
                cardName = cardName + " of Hearts";
            }
            else if (suit == 4)
            {
                cardName = cardName + " of Diamonds";
            }
        }
        while (previousCards.contains(cardName));
        //checks if card has previously been drawn
        previousCards = previousCards + cardName;
    }
    /**
    *Gives the value that a card is worth in blackjack.
    *@return the card value
    */
    public int getValue()
    {
        return value;
    }
    /**
    *Gives the name and suit of a card drawn.
    *@return card name
    */
    public String getName()
    {
        return cardName;
    }
}