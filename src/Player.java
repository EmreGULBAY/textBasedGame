import java.util.Scanner;

public class Player {
        private int damage;
        private int health;
        private int money;
        private String playerName;
        private String heroName;
        private int startingHealth;
        private Inventory inventory;


    public Player(String name){
                this.playerName=name;
                this.inventory=new Inventory();
                this.startingHealth=this.getHealth();
            }
    public int getStartingHealth() {
        return startingHealth;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getTotalDamage() {
        return damage+getPlayerWeapon().getDamage();
    }
    public int getDamage(){return damage;}
    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        if(health<=0)
            health=0;
        this.health = health;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
    public void selectHero(){
                Scanner scn=new Scanner(System.in);
                Heros [] heros ={new Samurai(),new Archer(),new Knight()};
                int k=1;
                for(Heros hero: heros){
        System.out.println(k+"-"+hero.getName()+" \tHasar:"+hero.getDamage()+
                "\tSağlık:"+hero.getHealth()+" \tPara:"+hero.getMoney());
        k++;
                }
        System.out.println();
        System.out.print("Lütfen listeden bir karakter seçiniz >>");
                int selection=scn.nextInt();
                while(selection<1||selection>heros.length){
                    System.out.println("Lütfen geçerli bir değer giriniz!");
                    selection=scn.nextInt();
                }
                switch(selection){
                    case 1:
                        assignHero(new Samurai());
                        break;
                    case 2:
                        assignHero(new Archer());
                        break;
                    case 3:
                        assignHero(new Knight());
                        break;
                    default:
                        System.out.println("Otomatik samuray seçtiniz!");
                        assignHero(new Samurai());
                        break;

                }
    }
    public void playerStats(){
        System.out.println("Silahınız: "+this.getInventory().getWeapon()
                .getName()+" Zırhınız: "+this.getInventory().
                getArmor().getName()+" Bloklama: "+
                getPlayerArmor().getProtection()+
                " Hasarınız: "+this.getTotalDamage()+" Canınız: "+
                this.getHealth()+" Paranız: "+this.getMoney());
    }
    public void assignHero(Heros heros){
                this.damage=heros.getDamage();
                this.money=heros.getMoney();
                this.health=heros.getHealth();
                this.startingHealth=heros.getHealth();
                this.heroName=heros.getName();
        System.out.println("Karakter: "+heros.getName()+
                "\tHasar: "+heros.getDamage()+"\tSağlık:"
                +heros.getHealth()+"\tPara: "+heros.getMoney());
        System.out.println();
    }
    public Weapon getPlayerWeapon(){
        return this.getInventory().getWeapon();
    }
    public Armor getPlayerArmor(){
        return this.getInventory().getArmor();
    }
}
