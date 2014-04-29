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

public class WarGUI extends JFrame
{
   private War game;
   private JPanel headerPanel, buttonPanel, gamePanel;
   private JLabel title, name, player, computer;
   private JButton newGame, playRound, playAll, exitGame;
   
   public WarGUI()
   {
      // Call super constructor
      super("Marco White's War Game");
      // Set the gui layout
      setLayout(new BorderLayout());
      // Define the game's functionality
      game = new War();
      // Configure the header panel
      headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(Color.GREEN);
      title = new JLabel("War Card Game", SwingConstants.CENTER);
      title.setFont(new Font("HELVETICA", Font.ITALIC, 32));
      headerPanel.add(title, BorderLayout.NORTH);
      name = new JLabel("By: Marco White", SwingConstants.CENTER);
      name.setFont(new Font("HELVETICA", Font.PLAIN, 24));
      headerPanel.add(name, BorderLayout.CENTER);
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
      buttonPanel = new JPanel(new GridLayout(4,1));
      buttonPanel.setBackground(Color.GREEN);
      newGame = new JButton("New Game");
      buttonPanel.add(newGame);
      playRound = new JButton("Play Round");
      buttonPanel.add(playRound);
      playAll = new JButton("Play All Rounds");
      buttonPanel.add(playAll);
      exitGame = new JButton("Exit Game");
      buttonPanel.add(exitGame);
      add(buttonPanel, BorderLayout.SOUTH);
      // Pack gui and make visible
      pack();
      setVisible(true);
   }
   
   public static void main(String [] args)
   {
      new WarGUI();
   }
}
