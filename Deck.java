/*
Marco White
4/24/14
CS110

Deck class

**************************************
*          Deck UML Diagram          *
**************************************
*  -CARDS_IN_DECK : int              *
*  -deck : ArrayList<Card>           *
**************************************
*  +Deck(void)                       *
*  +freshDeck(void) : void           *
*  +dealCard(void) : Card            *
*  +addCard(Card) : void             *
*  +addCard(ArrayList<Card>) : void  *
*  +cardsRemaining(void) : int       *
*  +shuffle(void) : void             *
*  +isEmpty(void) : boolean          *
*  +splitDeck(void) : Deck           *
*  +toString(void) : String          *
**************************************

*/

import java.util.Random;
import java.util.ArrayList;

/**
   Represents a deck of 52 playing cards.
*/

public class Deck 
{
   // Fields
   private final int CARDS_IN_DECK = 52;
   private ArrayList<Card> deck;
   
   /**
      Default constructor
   */
   public Deck()
   {
      freshDeck();
      shuffle();
   }
   
   /**
      The freshDeck() method creates a fresh deck of 52 cards.
   */
   public void freshDeck()
   {
      deck = new ArrayList<Card>(CARDS_IN_DECK);
      for (int r = 2; r <= Card.ACE; r++)
      {
         for (int s = Card.SPADES; s <= Card.DIAMONDS; s++)
         {
           deck.add(new Card(s,r));
         }
      }
   }
   
   /**
      The dealCard() method removes the top card from the deck.
      @return The card at the top of the deck
   */
   public Card dealCard()
   {
      Card c = deck.remove(0);
      return c;
   }
   
   /**
      The addCard() method adds a card to the bottom of the deck.
      @param other The card to be added to the bottom of the deck
   */
   public void addCard(Card other)
   {
      deck.add(other);
   }
   
   /**
      This alternate addCard() method can add multiple cards held in
      an array list to the deck.
      @param cards Collection of cards to be added to the deck
   */
   public void addCard(ArrayList<Card> cards)
   {
      for (Card c : cards)
      {
         deck.add(c);
      }
   }
   
   /**
      The cardsRemaining() method returns the number of cards in the deck.
      @return The number of cards in the deck
   */
   public int cardsRemaining()
   {  
      return deck.size();
   }
   
   /**
      The shuffle() method shuffles the full 52 card deck.
   */
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < deck.size(); i++)
      {
         randNum = r.nextInt(deck.size());
         temp = deck.get(i);
         deck.set(i,deck.get(randNum));
         deck.set(randNum,temp);
      }      
   }
   
   /**
      The isEmpty() method checks if the deck is empty.
      @return True if the deck is empty, false if it is not
   */
   public boolean isEmpty()
   {
      return (deck.size() == 0);
   }
   
   /**
      The splitDeck() method takes the deck and splits it in two,
      returning the other half as a seperate deck.
      @return The other half of the deck.
   */
   public Deck splitDeck()
   {
      Deck other = new Deck();
      for (int i = 1; i <= CARDS_IN_DECK; i++)
      {
         other.dealCard();
      }
      for (int i = 0; i < (CARDS_IN_DECK/2); i++)
      {
         other.addCard(deck.remove(0));
      }
      return other;
   }
   
   /**
      The toString() method returns a formatted string showing all of the
      cards within the deck.
      @return A formatted string showing all of the cards within the deck
   */
   public String toString()
   {
      String out = "";
      for (Card c : deck)
      {
         out += c.toString() + "\n";
      }
      return out;
   }
   
   // Demo code
   public static void main(String [] args)
   {
      Deck d1, d2;
      d1 = new Deck();
      d2 = d1.splitDeck();
      System.out.println("Deck 1:");
      System.out.println("****************************");
      System.out.println(d1.toString());
      System.out.println("\nDeck 2:");
      System.out.println("*****************************");
      System.out.println(d2.toString());
   }
}

