import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.io.Serializable;

public class Zombie implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private int health;
    private int x, y;
    private final int speed = 5;

    public Zombie(String name, int hp, int startX) {
        this.name = name;
        this.health = hp;
        this.x = startX;
        this.y = 100 + new Random().nextInt(300);
    }

    public static Zombie generateRandom() {
        return new Zombie("Zombie", 50 + new Random().nextInt(50), 700);
    }

    public void move() {
        x -= speed;
    }

    public boolean reachedTower() {
        return x <= 100;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
    }

    public int getHealth() { return health; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getName() { return name; }
}
