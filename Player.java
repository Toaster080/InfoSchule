import java.io.Serializable;
import javax.swing.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private int xp = 0;
    private int gold = 100;
    private int upgradeLevel = 0;

    public int getXp() { return xp; }
    public int getGold() { return gold; }
    public void addXp(int xp) { this.xp += xp; }
    public void addGold(int g) { this.gold += g; }

    public void buyWeapon(Tower tower) {
        WeaponType[] options = WeaponType.values();
        WeaponType chosen = (WeaponType) JOptionPane.showInputDialog(null, "Wähle Waffe:", "Waffenladen",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (chosen != null && gold >= 50) {
            tower.setWeapon(new Weapon(chosen));
            gold -= 50;
        }
    }

    public void applyPermanentUpgrade() {
        if (xp >= 100) {
            xp -= 100;
            upgradeLevel++;
            gold += 50 * upgradeLevel;
            JOptionPane.showMessageDialog(null, "Upgrade angewendet! (Level " + upgradeLevel + ")");
        }
    }
}
