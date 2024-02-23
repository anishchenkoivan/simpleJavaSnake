import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Snake {
  public static enum Directions {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    STAY
  }

  private LinkedList<int[]> body = new LinkedList<>();

  private int cordX = 100;
  private int cordY = 100;
  private int size = 25;
  private Directions direction = Directions.STAY;

  public Snake() {
    body.add(new int[] {this.cordX, this.cordY});
  }

  public Snake(int cordX, int cordY, int size) {
    this.cordX = cordX;
    this.cordY = cordY;
    this.size = size;
    body.add(new int[] {this.cordX, this.cordY});
  }

  public int[] getHead() {
    return body.getLast();
  }

  public int getSize() {
    return body.size();
  }

  public void draw(Graphics g) {
    g.fillRect(body.getLast()[0], body.getLast()[1], size, size);
    for (int[] segment : body) {
      g.drawRect(segment[0], segment[1], size, size);
    }
  }

  public boolean checkIntersections (Rectangle bounds) {
    Iterator<int[]> iterator = body.iterator();
    int[] head  = body.getLast();
    if (head[0] < 0 || head[0] > bounds.getWidth() || head[1] < 0 || head[1] > bounds.getHeight()) {
      return true;
    }
    while (iterator.hasNext()) {
      if (Arrays.equals(head, iterator.next())) {
        if (iterator.hasNext()) {
          return true;
        }
      }
    }
    return false;
  }

  public void grow() {
    int[] tail = body.getFirst();
    if (body.size() == 1) {
      switch(direction) {
        case UP -> body.addFirst(new int[] {cordX, cordY + size});
        case DOWN -> body.addFirst(new int[] {cordX, cordY - size});
        case RIGHT -> body.addFirst(new int[] {cordX - size, cordY});
        case LEFT -> body.addFirst(new int[] {cordX + size, cordY});
      }
    } else {
      int[] prevTail = body.get(1);
      body.addFirst(new int[] {tail[0] + (tail[0] - prevTail[0]), tail[1] + (tail[1] - prevTail[1])});
    }
  }

  Snake move() {

    switch (direction) {
      case UP -> cordY -= size;
      case RIGHT -> cordX += size;
      case DOWN -> cordY += size;
      case LEFT -> cordX -= size;
    }

    body.add(new int[] {this.cordX, this.cordY});
    body.removeFirst();
    return this;
  }

  Snake changeDirection(Directions direction) {
    this.direction = direction;
    return this;
  }
}
