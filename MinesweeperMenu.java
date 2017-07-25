import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.io.*;

public class MinesweeperMenu extends JPanel{
  private JLabel title;
  private JButton newGame, newGameColour, quit;
  
  MinesweeperMenu(){
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    newGame = new JButton("Play Game");
    newGameColour = new JButton("Play Coloursweeper");
    quit = new JButton("Quit");
    title = new JLabel("Minesweeper");
    quit.addActionListener(new Quit());
    newGame.addActionListener(new NewGame());
    newGameColour.addActionListener(new NewGameColour());
    add(title);
    add(newGame);
    add(newGameColour);
    add(quit);
  }
  
  static public void main (String[] args){
    JFrame minesweep = new JFrame("Minesweeper");
    minesweep.getContentPane().add(new MinesweeperMenu());
    minesweep.setSize(new Dimension(400,400));
    minesweep.setVisible(true);
  }
  
  private class Quit implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
  }
  
  private class NewGame implements ActionListener{
    public void actionPerformed(ActionEvent e){
      //JFrame ex = new Mines(30, user);
      //ex.setVisible(true);
    }
  }
  private class NewGameColour implements ActionListener{
    public void actionPerformed(ActionEvent e){
      //JFrame ex = new Mines(40, user);
      //ex.setVisible(true);
    }
  }
}
