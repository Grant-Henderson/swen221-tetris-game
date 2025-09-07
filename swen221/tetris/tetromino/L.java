package swen221.tetris.tetromino;

import swen221.tetris.logic.Color;

public class L extends J{
  
public L(int x, int y, Color color) {
    super(x, y,color);/*TODO: complete this code*/
  }
  
public int baseX(int i) {
    return (i < 3) ? i - 1 : i - 4;
}

  //TODO: add more methods if needed
}


