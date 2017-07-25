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
    minesweep.pack();
    minesweep.setVisible(true);
  }
  
  private class Quit implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
  }
  
  private class NewGame implements ActionListener{
    public void actionPerformed(ActionEvent e){
      JFrame gameSelectMenu = new JFrame("GameOptions");
      gameSelectMenu.getContentPane().add(new GameSelect());
      gameSelectMenu.pack();
      gameSelectMenu.setVisible(true);
    }
  }
  private class NewGameColour implements ActionListener{
    public void actionPerformed(ActionEvent e){
      //JFrame ex = new Mines(40, user);
      //ex.setVisible(true);
    }
  }
  
  public class GameSelect extends JPanel {
    private JLabel diff, mode;
    private JButton start, back;
    private JRadioButtonMenuItem easy, medium, hard, square, hex;
    
    GameSelect(){
      super();
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      start = new JButton("Start");
      start.addActionListener(new StartGame());
      back = new JButton("Back");
      back.addActionListener(new GoBack());
      diff = new JLabel("Difficulty");
      mode = new JLabel("Game Mode");
      
      ButtonGroup diffGroup = new ButtonGroup();
      JMenu diffMenu = new JMenu("Game Difficulty");
      easy = new JRadioButtonMenuItem("Easy");
      easy.addActionListener(new Difficulty());
      medium = new JRadioButtonMenuItem("Medium");
      medium.addActionListener(new Difficulty());
      hard = new JRadioButtonMenuItem("Hard");
      hard.addActionListener(new Difficulty());
      diffGroup.add(easy);
      diffMenu.add(easy);
      //diffGroup.add(medium);
      //diffMenu.add(medium);
      //diffGroup.add(hard);
      //diffMenu.add(hard);
      
      ButtonGroup modeGroup = new ButtonGroup();
      JMenu modeMenu = new JMenu("Game Mode");
      square = new JRadioButtonMenuItem("Squares");
      square.addActionListener(new GameMode());
      hex = new JRadioButtonMenuItem("Hexagons");
      hex.addActionListener(new GameMode());
      modeGroup.add(square);
      modeMenu.add(square);
      //modeGroup.add(hex);
      //modeMenu.add(hex);
      
      
      
      add(diff);
      add(diffMenu);
      add(mode);
      add(modeMenu);
      add(start);
      add(back);
      setVisible(true);
    }
    
    private class StartGame implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //JFrame ex = new Mines(30, user);
        //ex.setVisible(true);
      }
    }
    
    private class GoBack implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //JFrame ex = new Mines(30, user);
        //ex.setVisible(true);
      }
    }
    
    private class Difficulty implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //JFrame ex = new Mines(30, user);
        //ex.setVisible(true);
      }
    }
    
    private class GameMode implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //JFrame ex = new Mines(30, user);
        //ex.setVisible(true);
      }
    }
  }
}
