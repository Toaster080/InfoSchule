public enum WeaponType {
    BASIC("Bogen", 10),
    FIRE("Flammenwerfer", 15),
    ICE("Eisstrahler", 12),
    ROCKET("Raketenwerfer", 25);

    private final String name;
    private final int damage;

    WeaponType(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
