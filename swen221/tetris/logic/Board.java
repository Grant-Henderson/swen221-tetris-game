package swen221.tetris.logic;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A Board instance represent a board configuration for a game of Tetris.
 * It is represented as a list of row 20, where every row is a list of column 10.
 * Each column element is a Color, and each color provides
 * both an awt.Color and String representation.
 * */
public class Board {
 
  private final ArrayList<ArrayList<Color>> inner= 
    new ArrayList<>(rangeY().map(i-> newRow()).toList());  
  
  private ArrayList<Color> newRow() {
    return new ArrayList<>(Collections.nCopies(10, Color.EMPTY));
  }
  
  /** Checks if an index is in the valid range for x coordinates
   * */
  public static boolean xOk(int x) { return x >= 0 && x < 10; }

  /** Checks if an index is in the valid range for y coordinates
   * */
  public static boolean yOk(int y) { return y >= 0 && y < 20; }

  /** Streams on the valid x coordinates
   * */
  public static Stream<Integer> rangeX() { 
    return IntStream.range(0, 10).boxed();
  }
  
  /** Streams on the valid y coordinates
   * */
  public static Stream<Integer> rangeY() { 
    return IntStream.range(0, 20).boxed();
  }

  /** Streams on the valid y coordinates, in reverse order
   * */
  public static Stream<Integer> reverseRangeY() { 
    return IntStream.range(0, 20).mapToObj(y-> 19 - y);
  }
  /** Streams on the 4 tetramino sqare indexes: 0,1,2,3
   * */
  public static Stream<Integer> rangeT() {
    return Stream.of(0, 1, 2, 3);
  }


  /**
   * Write the Color c in the cell inside row y and column x.
   * @param x must be between 0-9
   * @param y must be between 0-19
   * @param c can not be null or Color.Empty
   * To insert Color.Empty, see {@link Board#clearRow(int)}
   * */
  public void write(int x, int y, Color c) {
    assert c != null && c != Color.EMPTY : "Need an actual color";
    assert xOk(x) : "column " + x + "not in 0-9";
    assert yOk(y) : "row " + y + "not in 0-19";
    assert inner.get(y).get(x) == Color.EMPTY : "cell " + x + ", " + y + " already used";
    inner.get(y).set(x, c);
  }

  /**
   * Delete the row y and add a new fully empty row at the top (row 19)
   * @param y must be between 0-19
   * */
  public void clearRow(int y) {
    assert yOk(y) : "row " + y + "not in 0-19";
    inner.remove(y);
    inner.add(newRow());
  }

  /**
   * Read the color in column x and row y
   * @return is Optional.empty() if x and/or y points out of the board.
   * */
  public Optional<Color> read(int x, int y) {
    if(!xOk(x) || !yOk(y)){ return Optional.empty(); }
    return Optional.of(inner.get(y).get(x));
  }

  /**
   * Is the row y fully filled?
   * @param y must be between 0-19
   * */  
  @SuppressWarnings("unused")
  private boolean isRowFull(int y) {
     assert yOk(y) : "row " + y + "not in 0-19";
     //TODO: completing this method may help you to complete clearFullRows!
     return IntStream.range(0, 10)
    	        .allMatch(x -> inner.get(y).get(x) != Color.EMPTY);
   }
   
  /**
   * Removes all the full rows. Full rows are identified using isRowFull.
   * */
  public void clearFullRows() {
    //hint: use clearRow
    //hint: the top row is the one with y=19
    //      the bottom row is the one with y=0
    //TODO: complete this code
	  IntStream.range(0, 20).forEach(y -> {
		    while (isRowFull(y)) {
		        clearRow(y);
		    }
		});

  }
  /**
   * The toString method generates a string representation of the entire Tetris board.
   * It works as follows:
   * 1. It processes the board rows in reverse order (from top to bottom) using reverseRangeY(),
   *    because in Tetrs, row 0 is the bottom and row 19 is the top of the visible board.
   * 2. For each row (y-coordinate), it calls printLine(y) to generate a string representation
   *    of that specific row.
   * 3. The stream of row strings is collected into a single string with:
   *    - Each row separated by a newline character ("\n")
   *    - The entire output wrapped between no prefix ("") and a suffix ("\n")
   *    - The joining is done using Collectors.joining()
   * The final output resembles the actual Tetris board display, with the top row first
   * and bottom row last, and each row enclosed in pipe characters ("|").
   */
  public String toString() {
    return reverseRangeY()
      .map(x-> printLine(x))
      .collect(Collectors.joining("\n", "", "\n"));
  }
  
  /**
   * The printLine method generates a string representation of a single row of the Tetris board.
   * It works as follows:
   * 1. Processes each cell in the row (x-coordinate from 0 to 9) using rangeX()
   * 2. For each cell (x,y), gets the color's string representation (color.s)
   * 3. Joins all cell representations with:
   *    - Each cell separated by a pipe character ("|")
   *    - The entire row enclosed in pipe characters at both ends
   *    - The joining is done using Collectors.joining() with:
   *      * A delimiter of "|" between cells
   *      * A prefix of "|" at the start
   *      * A suffix of "|" at the end
   * The result is a string like "| | |X|X| | | | | | |" where X represents colored cells
   * and spaces represent empty cells (Color.EMPTY).
   */
  private String printLine(int y){
    return rangeX()
      .map(x-> inner.get(y).get(x).s)
      .collect(Collectors.joining("|", "|", "|"));
  }
}