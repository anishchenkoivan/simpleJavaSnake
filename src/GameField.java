import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameField extends JPanel implements KeyListener, ActionListener {
  private final Timer timer;
  private final Snake snake;
  private final Food food;
  private boolean gameOver;

  public GameField() {

    snake = new Snake();
    food = new Food();
    setFocusable(true);
    addKeyListener(this);

    timer = new Timer(125, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    snake.draw(g);
    food.draw(g);
    if (gameOver) {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.BOLD, 60));
      Rectangle bounds = this.getBounds();
      g.drawString("GAME OVER", (int) bounds.getWidth() / 2 - 200, (int) bounds.getHeight() / 2 - 30);
      g.drawString("Score: " + snake.getSize(), (int) bounds.getWidth() / 2 - 200, (int) bounds.getHeight() / 2 + 30);
      timer.stop();
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 37 -> snake.changeDirection(Snake.Directions.LEFT);
      case 38 -> snake.changeDirection(Snake.Directions.UP);
      case 39 -> snake.changeDirection(Snake.Directions.RIGHT);
      case 40 -> snake.changeDirection(Snake.Directions.DOWN);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void actionPerformed(ActionEvent e) {
    snake.move();
    repaint();
    checkIntersections();
  }

  private void checkIntersections() {
    if (snake.checkIntersections(this.getBounds())) {
      gameOver = true;
    }
    if (Arrays.equals(snake.getHead(), food.getPosition())) {
      snake.grow();
      food.move(this.getBounds());
    }
  }
}
