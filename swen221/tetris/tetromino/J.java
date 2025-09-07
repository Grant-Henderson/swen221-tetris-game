package swen221.tetris.tetromino;

import swen221.tetris.logic.Color;

public class J extends Tetromino{
  protected int rotation;

public J(int x, int y, Color color) {
    super(x, y, color);/*TODO: complete this code*/
    this.rotation=0;
    //throw new Error();
  }

@Override
public int x(int i) {
    return switch (rotation) {
        case 0 -> centerX() + baseX(i);
        case 1 -> centerX() - baseY(3 - i);
        case 2 -> centerX() - baseX(i);
        case 3 -> centerX() + baseY(i);
        default -> centerX();
    };
}

	@Override
	public int y(int i) {
    	return switch (rotation) {
        	case 0 -> centerY() + baseY(i);
        	case 1 -> centerY() + baseX(3 - i);
        	case 2 -> centerY() - baseY(i);
        	case 3 -> centerY() - baseX(i);
        	default -> centerY();
    	};
	}

  @Override
  public void rotateRight() {
  		rotation = (rotation + 3) % 4;
  	}
  
  public int baseX(int i) {
	    return (i < 3) ? i - 1 : i - 2;
	}

	public int baseY(int i) {
	    return (i < 3) ? 0 : -1;
	}
}