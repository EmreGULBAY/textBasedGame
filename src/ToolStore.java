import java.util.concurrent.TimeUnit;

public class ToolStore extends NormalLocation {
    private int id = 2;
    private Player player;

    public ToolStore(Player player) {
        super(player, "Eşya Dükkanı");
    }

    @Override
    public boolean onLocation() {
        boolean key = true;
        while (key) {
            System.out.println();
            System.out.println("Eşya dükkanındasınız !");
            System.out.println("1 - Silah\n2 - Zırh\n3 - Çıkış");
            System.out.println("Seçim :");
            int opt = scn.nextInt();
            while (opt < 1 || opt > 3) {
                System.out.println("Geçersiz değer girdiniz. Lütfen tekrar " +
                        "giriniz!");
                opt = scn.nextInt();
            }
            switch (opt) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Eşya dükkanından çıkyorsunuz...");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    key = false;
                    return true;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("Silahlar :");
        for (Weapon w : Weapon.weaponList()) {
            System.out.println(w.getId() + "-" + w.getName() +
                    " değer " + w.getPrice() + " hasar " + w.getDamage());
        }
        System.out.println("Çıkmak için 0 giriniz.");
    }
    public void buyWeapon() {
        System.out.println("Silah seçiniz :");
        int selection = scn.nextInt();
        while (selection < 0 || selection > Weapon.weaponList().length) {
            System.out.print("Geçersiz değer.Lütfen tekrar giriniz :");
            selection = scn.nextInt();
        }
        if (selection != 0) {
            Weapon selectedWeapon = Weapon.selectWeapon(selection);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz!");
                } else if (selectedWeapon.getName() == this.getPlayer().getInventory()
                        .getWeapon().getName()) {
                    System.out.println("Zaten " + this.getPlayer().getInventory()
                            .getWeapon().getName() + "'a sahipsiniz!");
                } else {
                    System.out.println(selectedWeapon.getName() + " aldınız!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki silahınız :" + this.getPlayer().
                            getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahınız :" + this.getPlayer().
                            getInventory().getWeapon().getName());
                }
            }
        }
    }

    public void printArmor() {
        System.out.println("Zırhlar");
        for (Armor a : Armor.armorList()) {
            System.out.println(a.getId() + "-" + a.getName() +
                    " değer " + a.getPrice() + " koruma " + a.getProtection());
        }
        System.out.println("Çıkmak için 0 giriniz.");
    }

    public void buyArmor() {
        System.out.print("Zırh seçiniz:");
        int selection = scn.nextInt();
        while (selection < 0 || selection > Armor.armorList().length) {
            System.out.println("Geçersiz değer girdiniz! Lütfen tekrar " +
                    "giriniz:");
            selection = scn.nextInt();
        }
        if (selection != 0) {
            Armor selectedArmor = Armor.selectArmor(selection);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney())
                    System.out.println("Bakiyeniz yetersiz!");
                else if (this.getPlayer().getInventory().getArmor().getName()
                        == selectedArmor.getName()) {
                    System.out.println("Zaten " + selectedArmor.getName() + " 'a sahipsiniz!");
                } else {
                    System.out.println(selectedArmor.getName() + " aldınız!");
                    int balance = this.getPlayer().getMoney() -
                            selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki zırhınız: " + this.getPlayer()
                            .getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Yeni zırhınız: " + this.getPlayer()
                            .getInventory().getArmor().getName());
                }

            }
        }
    }
}
