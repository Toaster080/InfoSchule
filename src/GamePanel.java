import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final Tower tower;
    private final List<Zombie> zombies;
    private int wave = 1;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setLayout(null);

        // 🔁 Lade Spielstand
        player = SaveManager.load();
        tower = new Tower();
        zombies = new ArrayList<>();

        // 🧠 Buttons
        JButton nextWave = new JButton("➡️ Nächste Welle");
        nextWave.setBounds(600, 500, 150, 30);
        nextWave.addActionListener(e -> spawnWave());
        add(nextWave);
        JButton weaponButton = new JButton("🔫 Waffe wechseln");
        weaponButton.setBounds(30, 20, 160, 30);
        weaponButton.addActionListener(e -> player.buyWeapon(tower));
        add(weaponButton);
        JButton upgradeButton = new JButton("⭐ XP-Upgrade");
        upgradeButton.setBounds(30, 60, 160, 30);
        upgradeButton.addActionListener(e -> player.applyPermanentUpgrade());
        add(upgradeButton);
        JButton saveButton = new JButton("💾 Speichern");
        saveButton.setBounds(30, 100, 160, 30);
        saveButton.addActionListener(e -> SaveManager.save(player));
        add(saveButton);

        JButton loadButton = new JButton("🔄 Laden");
        loadButton.setBounds(30, 140, 160, 30);
        loadButton.addActionListener(e -> {
            Player loaded = SaveManager.load();
            player.setFrom(loaded); // NEU: Daten übernehmen
            repaint();
        });
        add(loadButton);


        // 🧟 Starte mit 1. Welle
        spawnWave();

        // ✅ Swing Timer (nicht java.util.Timer!)
        timer = new Timer(100, this);
        timer.start();
    }

    private void spawnWave() {
        player.addXp(50);
        for (int i = 0; i < wave; i++) {
            zombies.add(Zombie.generateRandom());
        }
        wave++;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 🧟 Zombie Bewegung & Tower Attack
        Iterator<Zombie> it = zombies.iterator();
        while (it.hasNext()) {
            Zombie z = it.next();
            z.move();
            if (z.reachedTower()) {
                tower.takeDamage(10);
                it.remove();
            }
        }

        // 💥 Tower schießt automatisch
        tower.autoAttack(zombies);

        // 💀 Game Over
        if (tower.isDestroyed()) {
            timer.stop();
            SaveManager.save(player);
            JOptionPane.showMessageDialog(null, "💀 Du wurdest überrannt!");
            System.exit(0);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int offset = 140;

        g.drawString("🛡️ HP: " + tower.getHp(), panelWidth - offset, 30);
        g.drawString("💰 Gold: " + player.getGold(), panelWidth - offset, 50);
        g.drawString("⭐ XP: " + player.getXp(), panelWidth - offset, 70);
        g.drawString("⬆️ Upgrades: " + player.getUpgradeLevel(), panelWidth - offset, 90); // NEU

        // 🧍 HUD oben rechts
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 14);
        g.setFont(font);

        g.drawString("🛡️ HP: " + tower.getHp(), panelWidth - offset, 30);
        g.drawString("💰 Gold: " + player.getGold(), panelWidth - offset, 50);
        g.drawString("⭐ XP: " + player.getXp(), panelWidth - offset, 70);

        // 🏰 Tower zeichnen
        g.setColor(Color.GREEN);
        g.fillRect(50, 250, 40, 80);

        // 🧟 Zombies
        for (Zombie z : zombies) {
            g.setColor(Color.RED);
            g.fillRect(z.getX(), z.getY(), 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(z.getHealth() + " HP", z.getX(), z.getY() - 5);
        }
    }


}