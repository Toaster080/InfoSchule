import java.io.Serializable;
import java.util.List;

public class Tower implements Serializable {
    private static final long serialVersionUID = 1L;

    private int hp = 100;
    private Weapon weapon = new Weapon(WeaponType.BASIC);

    public void autoAttack(List<Zombie> zombies) {
        for (Zombie z : zombies) {
            if (z.getX() < 300) {
                z.takeDamage(weapon.getDamage());
                break;
            }
        }
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }

    public int getHp() {
        return hp;
    }

    public void setWeapon(Weapon w) {
        this.weapon = w;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
