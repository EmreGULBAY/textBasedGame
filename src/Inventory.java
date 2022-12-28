import java.util.Arrays;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private String[] awards;
    int count=0;
    public Inventory(){
        this.weapon=new Weapon("yumruk",-1,0,0);
        this.armor=new Armor("Kıyafet",-1,0,0);
        awards = new String[4];
    }
    public String[] getAwards() {
        return awards;
    }
    public void deleteAward(){
        Arrays.fill(awards, null);
        count=0;
    }
    public void addAward(String award){
        awards[count]=award;
        count++;
    }
    public void setAwards(String[] awards) {
        this.awards = awards;
    }
    public void control(String [] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j< arr.length;j++){
                if(arr[i]!=null) {
                    if (arr[i].equals(arr[j]))
                        arr[j] = null;
                }
            }
        }
    }
    public int getCount(){
        return count;
    }
    public void showAwards(){
        control(awards);
        for(String a:awards){
            if(a!=null)
            System.out.println(a);
        }
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Armor getArmor(){
        return armor;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setArmor(Armor armor){
        this.armor=armor;
    }
}
