/*
Marco White
4/24/14
CS110

War class

***********************************
*         War UML Diagram         *
***********************************
*  -player : Deck                 *
*  -computer : Deck               *
*  -roundCount : int              *
***********************************
*  +War(void)                     *
*  +round(void)                   *
*  +checkForWinner(void) : int    *
*  +getCardSpread(void) : int[]   *
*  +getRoundCount(void) : int     *
***********************************

*/

import java.util.ArrayList;

/**
   Holds the functionality of the war game.
*/

public class War
{
   // Fields
   Deck player, computer;
   int roundCount;
   
   /**
      Default constructor
   */
   public War()
   {
      player = new Deck();
      computer = player.splitDeck();
      roundCount = 0;
   }
   
   /**
      The round() method simulates a round of war.
   */
   public void round()
   {
      ArrayList<Card> playerCard, computerCard;
      playerCard = new ArrayList<Card>();
      computerCard = new ArrayList<Card>();
      boolean isWar = true;
      while (isWar)
      {
         playerCard.add(0, player.dealCard());
         computerCard.add(0, computer.dealCard());
         System.out.println(playerCard.get(0).toString() + " versus " + computerCard.get(0).toString());
         if (playerCard.get(0).compareTo(computerCard.get(0)) > 0)
         {
            // Player wins round
            player.addCard(computerCard);
            player.addCard(playerCard);
            isWar = false;
            System.out.println("Player wins");
         }
         else if (playerCard.get(0).compareTo(computerCard.get(0)) < 0)
         {
            // Player loses round
            computer.addCard(playerCard);
            computer.addCard(computerCard);
            isWar = false;
            System.out.println("Computer wins");
         }
         else
         {
            // Tie, therefore there is a war
            System.out.println("War!");
            if (player.cardsRemaining() < 2 || computer.cardsRemaining() < 2)
            {
               isWar = false;
            }
            else
            {
               playerCard.add(0, player.dealCard());
               computerCard.add(0, computer.dealCard());
            }
         }
      }
      roundCount++;
   }
   
   /**
      The checkForWinner() method checks if there is a winner.
      @return 1 if the player has won, -1 if the computer has won, 0 if there is no winner yet
   */
   public int checkForWinner()
   {
      if (computer.isEmpty())
      {
         return 1;
      }
      else if (player.isEmpty())
      {
         return -1;
      }
      else
      {
         return 0;
      }
   }
   
   /**
      The getCardSpread() method returns the amount of cards the player
      and computer have remaining.
      @return A two element array containing the card spread where the first element is the player's cards
   */
   public int[] getCardSpread()
   {
      int[] spread = new int[2];
      spread[0] = player.cardsRemaining();
      spread[1] = computer.cardsRemaining();
      return spread;
   }
   
   /**
      The getRoundCount() method returns the number of rounds that have been played.
      @return The round count
   */
   public int getRoundCount()
   {
      return roundCount;
   }
   
   // Demo code
   public static void main(String [] args)
   {
      War game = new War();
      while (game.checkForWinner() == 0)
      {
         game.round();
         System.out.println("Player: " + game.getCardSpread()[0] + " Computer: " + game.getCardSpread()[1]);
         System.out.println("Rounds played: " + game.getRoundCount() + "\n");
      }
      if (game.checkForWinner() > 0)
      {
         System.out.println("Player won");
      }
      if (game.checkForWinner() < 0)
      {
         System.out.println("Computer won");
      }
   }
}
