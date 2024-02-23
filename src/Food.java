import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
  private int cordX = 100;
  private int cordY = 25;
  private final int size = 25;

  public Food() {}

  public void draw(Graphics g) {
    g.drawOval(cordX, cordY, size, size);
  }

  public int[] getPosition() {
    return new int[] {cordX, cordY};
  }

  public void move(Rectangle bounds) {
    cordX = (int) (Math.random() * bounds.width / size) * size;
    cordY = (int) (Math.random() * bounds.height / size) * size;
  }
}
