/*
Marco White
4/24/14
CS110

Card class

******************************
*      Card UML Diagram      *
******************************
*  +SPADES : int             *
*  +CLUBS : int              *
*  +HEARTS : int             *
*  +DIAMONDS : int           *
*  +ACE : int                *
*  +JACK : int               *
*  +QUEEN : int              *
*  +KING : int               *
*  -rank : int               *
*  -suit : int               *
******************************
*  +Card(int, int)           *
*  +getSuit(void) : int      *
*  +getRank(void) : int      *
*  +toString(void) : String  *
*  +equals(Card) : boolean   *
*  +compareTo(Card) : int    *
******************************

*/

/**
   Represents a single playing card with a rank and suit.
*/

public class Card
{
   // Fields
   public final static int SPADES = 1, CLUBS = 2, HEARTS = 3, DIAMONDS = 4;
   public final static int ACE = 1, JACK = 11, QUEEN = 12, KING = 13;
   private int rank, suit;
   
   /**
      The constructor sets the suit and rank.
      @param suit Suit of the card
      @param rank Rank of the card
   */
   public Card (int suit, int rank)
   {
      if (suit < 1 || suit > 4)
      {
         this.suit = 1;
      }
      else
      {
         this.suit = suit;
      }
      if (rank < 1 || rank > 13)
      {
         this.rank = 1;
      }
      else
      {
         this.rank = rank;
      }
   }
   
   /**
      The getSuit() method returns the card's suit.
      @return The card's suit
   */
   public int getSuit()
   {
      return suit;
   }
   
   /**
      The getRank() method returns the card's rank.
      @return The card's rank
   */
   public int getRank()
   {
      return rank;
   }
   
   /**
      The toString() method prints the card's rank and suit.
      @return String containing the card's rank and suit
   */
   public String toString()
   {
      String out = "Suit: ";
      switch (suit)
      {
         case SPADES:
            out += "Spades";
            break;
         case CLUBS:
            out += "Clubs";
            break;
         case HEARTS:
            out += "Hearts";
            break;
         case DIAMONDS:
            out += "Diamonds";
      }
      out += "\tRank: ";
      switch (rank)
      {
         case 1:
            out += "1";
            break;
         case 2:
            out += "2";
            break;
         case 3:
            out += "3";
            break;
         case 4:
            out += "4";
            break;
         case 5:
            out += "5";
            break;
         case 6:
            out += "6";
            break;
         case 7:
            out += "7";
            break;
         case 8:
            out += "8";
            break;
         case 9:
            out += "9";
            break;
         case 10:
            out += "10";
            break;
         case JACK:
            out += "Jack";
            break;
         case QUEEN:
            out += "Queen";
            break;
         case KING:
            out += "King";
      }
      return out;
   }
   
   /**
      The equals() method compares the card's rank to another card's rank
      to see if they are equal and returns a boolean accordingly.
      @param other Another card to compare to
      @return True if both cards have same rank, false if otherwise
   */
   public boolean equals(Card other)
   {
      if (getRank() == other.getRank())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
      The compareTo() method compares the card's rank to another card's rank
      and returns a 1 if it's greater than the other card, -1 if it's less than
      the other card, and a 0 if the two cards are equal.
      @param other Another card to compare to
      @return Either a 1, -1, or 0
   */
   public int compareTo(Card other)
   {
      if (getRank() > other.getRank())
      {
         return 1;
      }
      else if (getRank() < other.getRank())
      {
         return -1;
      }
      else
      {
         return 0;
      }
   }
}
