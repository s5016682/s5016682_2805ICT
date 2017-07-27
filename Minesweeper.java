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
  private boolean inGame;
  private boolean lose;

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
    inGame = true;
    lose = true;
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
          int[] temp = adjacentCells(position);
          for (int x = 0; x < temp.length; x++){
            if (field[temp[x]] != coverMineCell){
              field[temp[x]] += 1;
            }
          }
          i++;
      }
    }
  }
  
  private int[] adjacentCells(int pos){
    int inCol = (int) pos / numRows;
    int inRow = (int) pos % numRows;
    int i = 0;
    int tempPos[] = new int[8];
    if (gridType == 4){
      boolean top = inCol > 0;
      boolean bottom = inCol < numCols - 1;
      boolean left = inRow > 0;
      boolean right = inRow < numRows - 1;
      if (top){
        tempPos[i] = pos - numRows;
        i++;
        if (left){
          tempPos[i] = pos - numRows - 1;
          i++;
        }
        if (right){
          tempPos[i] = pos - numRows + 1;
          i++;
        }
      }
      if (bottom){
        tempPos[i] = pos + numRows;
        i++;
        if (left){
          tempPos[i] = pos + numRows - 1;
          i++;
        }
        if (right){
          tempPos[i] = pos + numRows + 1;
          i++;
        }
      }
      if (left){
        tempPos[i] = pos - 1;
        i++;
      }
      if (right){
        tempPos[i] = pos + 1;
        i++;
      } 
    } else if (gridType == 6){
    
    }
    int r[] = new int[i];
    for (int x = 0; x < i ; x++){
      r[x] = tempPos[x];
      System.out.println("Row:" + inRow + ",Col:" + inCol + ",nAdj:" + i + ",x:" + tempPos[x]);
    }
    return r;
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
      int cCol = x / cellSize;
      int cRow = y / cellSize;
      boolean rep = false;
      if (!inGame) {
        //newGame();
        //repaint();
      }
      if ((x < numCols * cellSize) && (y < numRows * cellSize)) {
        if (e.getButton() == MouseEvent.BUTTON3) {
          if (field[(cRow * numCols) + cCol] > mineCell) {
            rep = true;
            if (field[(cRow * numCols) + cCol] <= coverMineCell) {
                field[(cRow * numCols) + cCol] += markCell;
            } else {
              field[(cRow * numCols) + cCol] -= markCell;
            }
          }
        } else {
          if (field[(cRow * numCols) + cCol] > coverMineCell) {
            return;
          }
          if ((field[(cRow * numCols) + cCol] > mineCell) &&
              (field[(cRow * numCols) + cCol] < markMineCell)) {
            field[(cRow * numCols) + cCol] -= coverCell;
            rep = true;
            if (field[(cRow * numCols) + cCol] == mineCell){
                inGame = false;
            }
            if (field[(cRow * numCols) + cCol] == emptyCell){
              //find_empty_cells((cRow * numCols) + cCol);
            }
          }
        }
        if (rep){
          repaint();
        }
      }
    }
  }
}