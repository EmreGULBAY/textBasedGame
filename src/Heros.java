public abstract class Heros {
    private String name;
    private int damage;
    private int health;
    private int money;

    public  Heros(String name,int damage,int health,int money){
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.money=money;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public String getName(){
        return name;
    }
}
class Samurai extends Heros{
        public Samurai(){
            super("Samuray",5,21,16);
        }
    }
class Archer extends Heros{
    public Archer(){
        super("Okçu",7,18,20);
    }
}
class Knight extends Heros{
    public Knight(){
        super("Şövalye",8,24,5);
    }
}