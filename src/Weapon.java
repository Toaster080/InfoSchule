public class Weapon {
    private final WeaponType type;

    public Weapon(WeaponType type) {
        this.type = type;
    }

    public int getDamage() {
        return type.getDamage();
    }

    public String getName() {
        return type.getName();
    }
}
