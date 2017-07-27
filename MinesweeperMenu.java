import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.io.*;

public class MinesweeperMenu extends JPanel{
  private JLabel title;
  private JButton newGame, newGameColour, quit;
  static private JFrame minesweep, gameSelectMenu;
  
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
    minesweep = new JFrame("Minesweeper");
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
      gameSelectMenu = new JFrame("GameOptions");
      gameSelectMenu.getContentPane().add(new GameSelect());
      gameSelectMenu.pack();
      gameSelectMenu.setVisible(true);
      minesweep.setVisible(false);
    }
  }
  private class NewGameColour implements ActionListener{
    public void actionPerformed(ActionEvent e){
      ///!!!
    }
  }
  
  private class GameSelect extends JPanel {
    private JLabel diff, mode;
    private JButton start, back;
    private JRadioButton easy, medium, hard, square, hex;
    
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
      easy = new JRadioButton("Easy");
      medium = new JRadioButton("Medium");
      hard = new JRadioButton("Hard");
      diffGroup.add(easy);
      diffGroup.add(medium);
      diffGroup.add(hard);
      
      ButtonGroup modeGroup = new ButtonGroup();
      square = new JRadioButton("Squares");
      square.setEnabled(true);
      hex = new JRadioButton("Hexagons");
      modeGroup.add(square);
      modeGroup.add(hex);
      
      add(diff);
      add(easy);
      //add(medium);
      //add(hard);
      add(mode);
      add(square);
      //add(hex);
      add(start);
      add(back);
      easy.setSelected(true);
      square.setSelected(true);
    }
    
    private class StartGame implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //!!!
      }
    }
    
    private class GoBack implements ActionListener{
      public void actionPerformed(ActionEvent e){
        minesweep.setVisible(true);
        gameSelectMenu.setVisible(false);
      }
    }
  }
}
