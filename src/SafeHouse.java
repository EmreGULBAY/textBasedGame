public class SafeHouse extends NormalLocation {
        private int id=1;
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean onLocation(){
        System.out.println("Güvenli Evdesiniz!\nCanınız yenilendi!");
        this.getPlayer().setHealth(this.getPlayer().getStartingHealth());
        return true;
    }

}
