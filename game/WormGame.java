package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import static wormgame.Direction.DOWN;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;
        worm = new Worm(width / 2, height / 2, DOWN);

        createApple();

        addActionListener(this);
        setInitialDelay(2000);

    }

    private void createApple() {
        apple = null;
        while (apple == null) {
            Apple someApple = new Apple(new Random().nextInt(width), new Random().nextInt(height));
            if (!worm.runsInto(someApple)) {
                apple = someApple;
            }
        }
    }

    public Worm getWorm() {
        return worm;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        worm.move();
        if (worm.getX()==0||worm.getX()==width||worm.getY()==0||worm.getY()==height) {
            continues=false;
        }
        if (worm.runsIntoItself()) {
            continues = false;
        }
        if (worm.runsInto(apple)) {
            apple = null;
            worm.grow();
            createApple();
        }
        
        if (!continues) {
            return;
        }
        updatable.update();
        setDelay(1000 / worm.getLength());
    }

}
