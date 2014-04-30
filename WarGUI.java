/*
Marco White
4/28/14
CS110

CS110 Final Project

   This program launches a GUI emulating the card game of war.
   The player can play round by round against a computer or choose
   to simulate the entire game at once.

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WarGUI extends JFrame implements ActionListener
{
   private War game;
   private JPanel headerPanel, buttonPanel, gamePanel;
   private JLabel title, status, player, computer, gameInfo;
   private JButton newGame, playRound, playAll, exitGame;
   private JSlider playAllSpeed;
   private Timer warTimer, playAllTimer;
   private int timerCount, warCount;
   private Card[] cards; // Temporarily holds cards to be displayed in gui
   
   public WarGUI()
   {
      // Call super constructor
      super("Marco White's War Game");
      // Set default close operation
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      // Set the gui layout
      setLayout(new BorderLayout());
      // Define the game's functionality
      game = new War();
      // Configure timer for war animation
      warTimer = new Timer(500, this);
      warTimer.setRepeats(true);
      // Configure timer for playAll button
      playAllTimer = new Timer(100, this);
      playAllTimer.setRepeats(true);
      // Configure the header panel
      headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(Color.GREEN);
      title = new JLabel("War Card Game", SwingConstants.CENTER);
      title.setFont(new Font("HELVETICA", Font.ITALIC, 32));
      headerPanel.add(title, BorderLayout.NORTH);
      status = new JLabel("By: Marco White", SwingConstants.CENTER);
      status.setFont(new Font("HELVETICA", Font.PLAIN, 24));
      headerPanel.add(status, BorderLayout.CENTER);
      add(headerPanel, BorderLayout.NORTH);
      // Configure the game panel
      gamePanel = new JPanel(new GridLayout(1,2));
      gamePanel.setBackground(Color.GREEN);
      player = new JLabel(new ImageIcon("cardPics/back.jpg"));
      gamePanel.add(player);
      computer = new JLabel(new ImageIcon("cardPics/back.jpg"));
      gamePanel.add(computer);
      add(gamePanel, BorderLayout.CENTER);
      // Configure the button panel
      buttonPanel = new JPanel(new GridLayout(7,1));
      buttonPanel.setBackground(Color.GREEN);
      gameInfo = new JLabel("", SwingConstants.CENTER);
      updateGameInfo();
      gameInfo.setFont(new Font("HELVETICA", Font.PLAIN, 18));
      buttonPanel.add(gameInfo);
      newGame = new JButton("New Game");
      newGame.addActionListener(this);
      newGame.setEnabled(false);
      buttonPanel.add(newGame);
      playRound = new JButton("Play Round");
      playRound.addActionListener(this);
      buttonPanel.add(playRound);
      playAll = new JButton("Play All Rounds");
      playAll.addActionListener(this);
      buttonPanel.add(playAll);
      playAllSpeed = new JSlider(1, 199);
      playAllSpeed.addChangeListener(
         new ChangeListener()
         {
            @Override
            public void stateChanged(ChangeEvent e)
            {
               playAllTimer.setDelay(playAllSpeed.getValue());
            }
         });
      buttonPanel.add(playAllSpeed);
      buttonPanel.add(new JLabel("Play All Rounds Speed", SwingConstants.CENTER));
      exitGame = new JButton("Exit Game");
      exitGame.addActionListener(this);
      buttonPanel.add(exitGame);
      add(buttonPanel, BorderLayout.SOUTH);
      // Pack gui and make visible
      pack();
      setVisible(true);
   }
   
   private String getImagePath(Card c)
   {
      String filepath = "cardPics/";
      switch (c.getRank())
      {
         case Card.ACE:
            filepath += "ace";
            break;
         case 2:
            filepath += "2";
            break;
         case 3:
            filepath += "3";
            break;
         case 4:
            filepath += "4";
            break;
         case 5:
            filepath += "5";
            break;
         case 6:
            filepath += "6";
            break;
         case 7:
            filepath += "7";
            break;
         case 8:
            filepath += "8";
            break;
         case 9:
            filepath += "9";
            break;
         case 10:
            filepath += "10";
            break;
         case Card.JACK:
            filepath += "jack";
            break;
         case Card.QUEEN:
            filepath += "queen";
            break;
         case Card.KING:
            filepath += "king";
            break;
      }
      switch (c.getSuit())
      {
         case Card.SPADES:
            filepath += "s.jpg";
            break;
         case Card.CLUBS:
            filepath += "c.jpg";
            break;
         case Card.HEARTS:
            filepath += "h.jpg";
            break;
         case Card.DIAMONDS:
            filepath += "d.jpg";
            break;
      }
      return filepath;
   }
   
   private void updateGameInfo()
   {
      gameInfo.setText("Player: " + game.getCardSpread()[0] + "    Computer: " + game.getCardSpread()[1] + 
         "    Rounds Played: " + game.getRoundCount());
   }
   
   private void checkForWinner()
   {
      if (game.checkForWinner() != 0)
      {
         playRound.setEnabled(false);
         playAll.setEnabled(false);
         if (game.checkForWinner() > 0)
         {
            status.setText("Player won the game!");
         }
         else if (game.checkForWinner() < 0)
         {
            status.setText("Computer won the game, try again...");
         }
      }
   }
   
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == newGame)
      {
         game = new War();
         player.setIcon(new ImageIcon("cardPics/back.jpg"));
         computer.setIcon(new ImageIcon("cardPics/back.jpg"));
         status.setText("");
         updateGameInfo();
         if (!playRound.isEnabled())
         {
            playRound.setEnabled(true);
         }
         if (!playAll.isEnabled())
         {
            playAll.setEnabled(true);
         }
         newGame.setEnabled(false);
      }
      else if (e.getSource() == playRound)
      {
         if (game.checkForWinner() == 0)
         {
            cards = game.round();
            player.setIcon(new ImageIcon(getImagePath(cards[0])));
            computer.setIcon(new ImageIcon(getImagePath(cards[1])));
            if (cards.length == 2)
            {
               if (cards[0].compareTo(cards[1]) > 0)
               {
                  status.setText("Player won round, gains 1 card");
               }
               else
               {
                  status.setText("Computer won round, gains 1 card");
               }
               updateGameInfo();
               if (!newGame.isEnabled())
               {
                  newGame.setEnabled(true);
               }
               checkForWinner();
            }
            else
            {
               warCount = (cards.length-2)/4;
               timerCount = 0;
               status.setText("War!");
               newGame.setEnabled(false);
               playRound.setEnabled(false);
               playAll.setEnabled(false);
               warTimer.start();
            }
         }
      }
      else if (e.getSource() == playAll)
      {
         newGame.setEnabled(false);
         playRound.setEnabled(false);
         playAll.setEnabled(false);
         playAllTimer.start();
      }
      else if (e.getSource() == exitGame)
      {
         System.exit(0);
      }
      else if (e.getSource() == warTimer)
      {
         timerCount++;
         if (timerCount % 2 == 0)
         {
            player.setIcon(new ImageIcon(getImagePath(cards[timerCount*2])));
            computer.setIcon(new ImageIcon(getImagePath(cards[timerCount*2+1])));
            if (timerCount >= warCount * 2)
            {
               warTimer.stop();
               if (cards[timerCount*2].compareTo(cards[timerCount*2+1]) > 0)
               {
                  status.setText("Player won " + warCount + " wars, gains " + (cards.length/2) + " cards");
               }
               else
               {
                  status.setText("Computer won " + warCount + " wars, gains " + (cards.length/2) + " cards");
               }
               updateGameInfo();
               newGame.setEnabled(true);
               playRound.setEnabled(true);
               playAll.setEnabled(true);
               checkForWinner();
            }
         }
         else
         {
            player.setIcon(new ImageIcon("cardPics/back.jpg"));
            computer.setIcon(new ImageIcon("cardPics/back.jpg"));
         }
      }
      else if (e.getSource() == playAllTimer)
      {
         if (game.checkForWinner() == 0)
         {
            String statusText;
            cards = game.round();
            player.setIcon(new ImageIcon(getImagePath(cards[cards.length-2])));
            computer.setIcon(new ImageIcon(getImagePath(cards[cards.length-1])));
            if (cards[cards.length-2].compareTo(cards[cards.length-1]) > 0)
            {
               statusText = "Player won round, gains ";
            }
            else
            {
               statusText = "Computer won round, gains ";
            }
            statusText += (cards.length/2) + " card";
            if (cards.length != 2)
            {
               statusText += "s";
            }
            status.setText(statusText);
            updateGameInfo();
         }
         else
         {
            playAllTimer.stop();
            checkForWinner();
            newGame.setEnabled(true);
         }
      }
   }
   
   public static void main(String [] args)
   {
      new WarGUI();
   }
}
