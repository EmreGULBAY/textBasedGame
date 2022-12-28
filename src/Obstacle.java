import java.util.Random;

public class Obstacle {
    private int id;
    private int health;
    private int damage;
    private String name;
    private int award;
    private int fullHealth;

    public Obstacle(int id, int health, int damage, String name, int award) {
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.name = name;
        this.award=award;
        this.fullHealth=health;
    }
    public int getFullHealth() {
        return fullHealth;
    }
    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health<=0){
            health=0;
        }
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Zombie extends Obstacle{

    public Zombie(){
        super(1,10,3,"Zombi",4);
    }
}
class Vampire extends Obstacle {
    public Vampire() {
        super(2, 14, 4, "Vampir",7);
    }
}
class Bear extends Obstacle{
    public Bear(){
        super(3,20,7,"Ayı",12);
    }
}
class Snake extends Obstacle{

    public Snake(){
        super(4,12,3,"Yılan",0);
    }
}