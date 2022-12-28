public class Armor {
    private String name;
    private int id;
    private int protection;
    private int price;

    public Armor(String name, int id, int protection, int price) {
        this.name = name;
        this.id = id;
        this.protection = protection;
        this.price = price;
    }
    public static Armor [] armorList(){
        Armor[] armors=new Armor[3];
        armors[0]=new Armor("Hafif zırh",1,1,20);
        armors[1]=new Armor("Orta zırh",2,2,30);
        armors[2]=new Armor("Ağır zırh",3,4,40);
        return armors;
    }
    public static Armor selectArmor(int id){
        for(Armor a:armorList()){
            if(a.getId()==id)
                return a;
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
