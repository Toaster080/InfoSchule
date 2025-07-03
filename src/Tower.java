import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
public class Tower implements Serializable {
    private static final long serialVersionUID = 1L;

    public static int HP_BOOST = 0;

    private int hp = 100 + HP_BOOST;
    private Weapon weapon = new Weapon(WeaponType.BASIC);

    public void autoAttack(List<Zombie> zombies) {
        Iterator<Zombie> it = zombies.iterator();
        while (it.hasNext()) {
            Zombie z = it.next();
            if (z.getX() < 300) {
                z.takeDamage(weapon.getDamage());
                if (z.getHealth() <= 0) {
                    SoundPlayer.playSound("zombie_hit.wav");
                    it.remove();
                }
                break;
            }
        }
    }

    public void takeDamage(int dmg) { hp -= dmg; }

    public boolean isDestroyed() { return hp <= 0; }

    public int getHp() { return hp; }

    public void setWeapon(Weapon w) { this.weapon = w; }

    public Weapon getWeapon() { return weapon; }
}
