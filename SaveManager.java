import java.io.*;

public class SaveManager {

    private static final String FILE_NAME = "game.save";

    public static void save(Player player) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(player);
            System.out.println("💾 Spielstand gespeichert.");
        } catch (IOException e) {
            System.err.println("❌ Fehler beim Speichern.");
        }
    }

    public static Player load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("🔄 Spielstand geladen.");
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Kein gespeicherter Spielstand gefunden.");
            return new Player(); // fallback
        }
    }
}
