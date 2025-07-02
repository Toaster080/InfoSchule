import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

        player = SaveManager.load(); // 🧠 Lade vorherigen Spielstand
        tower = new Tower();
        zombies = new ArrayList<>();

        JButton nextWave = new JButton("➡️ Nächste Welle");
        nextWave.setBounds(600, 500, 150, 30);
        nextWave.addActionListener(e -> spawnWave());
        add(nextWave);

        spawnWave();

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
        Iterator<Zombie> it = zombies.iterator();
        while (it.hasNext()) {
            Zombie z = it.next();
            z.move();
            if (z.reachedTower()) {
                tower.takeDamage(10);
                it.remove();
            }
        }

        tower.autoAttack(zombies);

        if (tower.isDestroyed()) {
            timer.stop();
            SaveManager.save(player); // vor Game Over speichern
            JOptionPane.showMessageDialog(null, "💀 Du wurdest überrannt!");
            System.exit(0);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(50, 250, 40, 80); // Turm

        g.setColor(Color.WHITE);
        g.drawString("HP: " + tower.getHp(), 50, 240);
        g.drawString("Gold: " + player.getGold(), 50, 260);
        g.drawString("XP: " + player.getXp(), 50, 280);

        for (Zombie z : zombies) {
            g.setColor(Color.RED);
            g.fillRect(z.getX(), z.getY(), 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(z.getHealth() + " HP", z.getX(), z.getY() - 5);
        }
    }
}
