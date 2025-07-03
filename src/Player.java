import java.io.Serializable;
import javax.swing.*;
import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private int xp = 0;
    private int gold = 100;
    private int upgradeLevel = 0;

    public void buyWeapon(Tower tower) {
        WeaponType[] options = WeaponType.values();
        WeaponType chosen = (WeaponType) JOptionPane.showInputDialog(null, "Wähle Waffe:", "Waffenladen",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (chosen != null && gold >= 50) {
            tower.setWeapon(new Weapon(chosen));
            gold -= 50;
        }
    }

    public void addXp(int amount) {
        int bonus = (upgradeLevel >= 2) ? (int)(amount * 0.2) : 0;
        xp += amount + bonus;
    }


    public void applyPermanentUpgrade() {
            if (xp >= 100) {
                xp -= 100;
                upgradeLevel++;
                if (upgradeLevel >= 3) {
                    // Turm stärker machen
                    Tower.HP_BOOST += 5;
                }
                SoundPlayer.playSound("upgrade.wav");
                JOptionPane.showMessageDialog(null, "✅ Upgrade Level " + upgradeLevel + " aktiviert!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Nicht genug XP.");
            }
        }

        public int getXp() { return xp; }
        public int getGold() { return gold; }

        public void setFrom(Player other) {
            this.xp = other.xp;
            this.gold = other.gold;
            this.upgradeLevel = other.upgradeLevel;
        }

        public int getUpgradeLevel() {
            return upgradeLevel;
        }



}
