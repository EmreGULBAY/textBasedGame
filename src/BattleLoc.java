import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location {
    private String name;
    private Scanner scn = new Scanner(System.in);
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obs,String award,int maxObstacle) {
        super(player,name);
        this.obstacle=obs;
        this.award=award;
        this.maxObstacle=maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int number=randomObs();
        System.out.println("Şuanda "+this.getName()+"'dasiniz! Dikkatli olun " +
                "burada "+number+" tane "+this.getObstacle().getName()+" yaşıyor!");
        System.out.println("Savaşmak için s kaçmak için k giriniz:");
        String cond=scn.nextLine().toUpperCase();
            if(cond.equals("S")){
                if(this.combat(number)){
                    System.out.println("Tüm düşmanları yendiniz!");
                    this.getPlayer().getInventory().addAward(this.getAward());
                    System.out.println("Mevcut özel ödüller:");
                    this.getPlayer().getInventory().showAwards();
                    return true;
                }
            }
             if(this.getPlayer().getHealth()<=0){
                System.out.println("Öldünüz!");
                return false;
            }
            else if(cond.equals("K")){
                System.out.println("Kaçtınız!");
                return true;
            }
        return true;
    }
    public void coward(){
        System.out.println("Kaçtınız!");
        if(this.getPlayer().getMoney()>=5){
            Random rand=new Random();
            int drop=1+rand.nextInt(4);
            System.out.println("Kaçarken "+drop+" kadar altın düşürdünüz!");
            this.getPlayer().setMoney(this.getPlayer().getMoney()-drop);
        }
    }
    public boolean combat(int obsNum){
        for(int i=1;i<=obsNum;i++){
            this.getObstacle().setHealth(this.getObstacle().getFullHealth());
            if(this.getObstacle().getId()==4)
                this.getObstacle().setDamage(randomSnakeDmg());
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth()>0&&this.getObstacle().getHealth()>0){
                Random rand=new Random();
                int random=rand.nextInt(2);
                if(random==0)
                    System.out.println("İLk siz saldıracaksınız!");
                else
                    System.out.println("İlk "+this.getObstacle().getName()+" saldıracak!");
                System.out.println("Vurmak için v, kaçmak için k giriniz:");
                String opt=scn.nextLine().toUpperCase();
                if(opt.equals("V")){
                    if(random==0){
                        playerHit();
                    }
                    else {
                        obstacleHit();
                    }
                    if(this.getObstacle().getHealth()<=0){
                        System.out.println(i+"."+" "+this.getObstacle().getName()+" öldü!");
                        if(this.getObstacle().getId()!=4){
                        System.out.println(this.getObstacle().getAward()+" altın kazandınız!");
                        this.getPlayer().setMoney(this.getPlayer().getMoney()+
                                this.getObstacle().getAward());}
                        else{
                            snakeAward();
                        }
                        System.out.println("Güncel paranız: "+this.getPlayer().getMoney());
                        System.out.println();
                    }
                    if(this.getPlayer().getHealth()<=0){
                        return false;
                    }
                }
                else{
                    coward();
                    return false;
                }
            }

    }
        return true;
    }
    public void playerHit(){
        System.out.println("Vurdunuz!");
        this.getObstacle().setHealth(this.getObstacle().getHealth()-
                this.getPlayer().getTotalDamage());
        afterHit();
        if(this.getObstacle().getHealth() > 0) {
            System.out.println();
            System.out.println("Canavar Size Vurdu !");
            this.getPlayer().setHealth(this.getPlayer().getHealth() - ObsDmg());
            afterHit();
            }
        }
    private void obstacleHit() {
        System.out.println("Canavar Size Vurdu !");
        this.getPlayer().setHealth(this.getPlayer().getHealth() - ObsDmg());
        afterHit();
        if (this.getPlayer().getHealth() > 0) {
            System.out.println("Siz vurdunuz !");
            this.getObstacle().setHealth(this.getObstacle().getHealth()-
                    this.getPlayer().getTotalDamage());
            afterHit();
        }
    }
    public int ObsDmg(){
        if(this.getPlayer().getPlayerArmor().getProtection()>this.getObstacle().getDamage()){
            return 0;
        }
        else{
            return this.getObstacle().getDamage()-this.getPlayer().getPlayerArmor().getProtection();
        }
    }
    public void snakeAward(){
        Random random=new Random();
        int randomInt=random.nextInt(100);
        if(randomInt<15){
            Random random1=new Random();
            int rand=random1.nextInt(100);
            if(rand<20){
                System.out.println("Tüfek kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.weaponList()[2]);
            }
            else if(rand>20&&rand<50){
                System.out.println("Kılıç kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.weaponList()[1]);
            }
            else if(rand>50){
                System.out.println("Tabanca kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.weaponList()[0]);
            }
        }else if(randomInt>15&&randomInt<30){
            Random random2=new Random();
            int rand2=random2.nextInt(100);
            if(rand2<20){
                System.out.println("Ağır zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.armorList()[2]);
            }
            else if(rand2>20&&rand2<50){
                System.out.println("Orta zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.armorList()[1]);
            }
            else if(rand2>50){
                System.out.println("Hafif zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.armorList()[0]);
            }
        }
        else if(randomInt>30&&randomInt<55){
            Random random3=new Random();
            int rand3=random3.nextInt(100);
            if(rand3<20){
                System.out.println("10 altın kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
            }
            else if(rand3>20&&rand3<50){
                System.out.println("5 altın kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
            }
            else if(rand3>50){
                System.out.println("1 altın kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
            }
        }
        else{
            System.out.println("Hiçbir şey kazanamadınız!");
        }

    }
    public boolean FinishCondition(){

        return true;
    }
    public int randomSnakeDmg(){
        Random random=new Random();
        return 3+random.nextInt(4);
    }
    public void afterHit(){
        System.out.println("Canınız: "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+" canı: "+this.getObstacle().getHealth());
        System.out.println();
    }
    public void playerStats(){
        System.out.println("Oyuncu değerleri -->");
        System.out.println("Can: "+this.getPlayer().getHealth());
        System.out.println("Hasar: "+this.getPlayer().getTotalDamage());
        System.out.println("Silahınız: "+this.getPlayer().getPlayerWeapon().getName());
        System.out.println("Korumanız: "+this.getPlayer().getPlayerArmor().getProtection());
        System.out.println("Para: "+this.getPlayer().getMoney());
        System.out.println();
    }
    public void obstacleStats(int i){
        System.out.println(i+". "+this.getObstacle().getName()+" değerleri-->");
        System.out.println("Canı: "+this.getObstacle().getHealth());
        System.out.println("Hasarı: "+this.getObstacle().getDamage());
        System.out.println("Ödül: "+this.getObstacle().getAward());
        System.out.println();
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
    public int randomObs(){
        Random random=new Random();
        return 1+random.nextInt(this.getMaxObstacle());
    }
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
    public void assignSnakeDmg() {
        if (this.getObstacle().getId() == 4) {
            this.getObstacle().setDamage(randomSnakeDmg());
        }
    }
}
class Cave extends BattleLoc{
    public Cave(Player player){
        super(player,"Mağara",new Zombie(),"yemek",3);

    }
}
class Forest extends BattleLoc{
    public Forest(Player player){
        super(player,"Orman",new Vampire(),"odun",3);
    }
}
class River extends BattleLoc{
    public River(Player player){
        super(player,"Nehir",new Bear(),"su",2);
    }
}
class Mine extends BattleLoc{
    public Mine(Player player){
        super(player,"Maden",new Snake(),"",5);
    }
}