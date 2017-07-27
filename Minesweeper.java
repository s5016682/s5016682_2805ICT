import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.io.*;

public class Minesweeper extends JPanel{
  private int numMines, gridType, numRows, numCols, numCells;
  private final int numImages = 13;
  private final int cellSize = 16;
  
  private final int coverCell = 10;
  private final int markCell = 10;
  private final int emptyCell = 0;
  private final int mineCell = 9;
  private final int coverMineCell = mineCell + coverCell;
  private final int markMineCell = coverMineCell + markCell;
  
  private final int drawMine = 9;
  private final int drawCover = 10;
  private final int drawMark = 11;
  private final int drawWrongMark = 12;
  
  private int[] field;
  private int mines_left;
  private Image[] img;

  Minesweeper(int bs, int nm, int gt){
    super();
    String prefix = "s";
    numRows = bs;
    numCols = bs;
    numCells = numRows * numCols;
    numMines = nm;
    gridType = gt;
    img = new Image[numImages];
    
    if (gridType == 6){
      prefix = "h";
    }
    
    for (int i = 0; i < numImages; i++) {
      img[i] = (new ImageIcon(prefix + i + ".png")).getImage();
    }
    
    setDoubleBuffered(true);
    
    addMouseListener(new MinesAdapter());
    newGame();
  }
  
  private void newGame(){
    Random random;
    int current_col;
    int i = 0;
    int position = 0;
    int cell = 0;
    random = new Random();
    mines_left = numMines;
    field = new int[numCells];
    for (i = 0; i < numCells; i++)
      field[i] = coverCell;
    i = 0;
    while (i < numMines) {
      position = (int) (numCells * random.nextDouble());
      if ((position < numCells) && (field[position] != coverMineCell)) {
          field[position] = coverMineCell;
          i++;
      }
    }
  }
  
  @Override
  public void paintComponent(Graphics g) {
    int cell = 0;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        cell = field[(i * numCols) + j];
        if (false) {
          if (cell == coverMineCell) {
            cell = drawMine;
          } else if (cell == markMineCell) {
            cell = drawMark;
          } else if (cell > coverMineCell) {
            cell = drawWrongMark;
          } else if (cell > mineCell) {
            cell = drawCover;
          }
        } else {
          if (cell > coverMineCell)
            cell = drawMark;
          else if (cell > mineCell) {
            cell = drawCover;
          }
        }
        int y = j * cellSize;
        int x = i * cellSize;
        if (gridType == 6 && j % 2 == 1){
          x += (cellSize / 2);
        } 
        g.drawImage(img[cell], y, x, this);
      }
    }
  }
  
  class MinesAdapter extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      
    }
  }
}