import java.util.Scanner;

public class Game {
    private Scanner scn=new Scanner(System.in);
    public void start(){
        System.out.print("İsminizi giriniz:");
        String name =scn.nextLine();
        System.out.println(name+" Oyuna hoşgeldiniz !");
        Player player=new Player(name);
            Location location=null;
            player.selectHero();
        while(true){
            player.playerStats();
            System.out.println();
                System.out.println("Bölgeler :");
                System.out.println("1 - Güvenli Ev\n2 - Eşya Dükkanı");
                System.out.println("3 - Mağara -- Ödül: Yemek");
                System.out.println("4 - Orman -- Ödül: Odun");
                System.out.println("5 - Nehir -- Ödül: Su");
                System.out.println("6 - Maden -- Ödül: Rasgele eşya şansı");
                System.out.println("0 - Çıkış");
                System.out.print("Gitmek istediğiniz bölgeyi seçiniz:");
                Scanner scn=new Scanner(System.in);
                int area=scn.nextInt();
                if(player.getInventory().getCount()>=3&&area==1){
                    System.out.println("Oyunu başarıyla tamamladınız!");
                    break;
                }
                else if(player.getInventory().getCount()<3&&area==1&&player.getInventory().getCount()>0){
                    System.out.println("Bütün ödülleri toplamadan eve döndüğünüz için hepsini kaybettiniz!");
                    player.getInventory().deleteAward();
                }
                switch (area){
                    case 0:
                        location=null;
                        break;
                    case 1:
                        location=new SafeHouse(player);
                        break;
                    case 2:
                        location=new ToolStore(player);
                        break;
                    case 3:
                        location=new Cave(player);
                        break;
                    case 4:
                        location=new Forest(player);
                        break;
                    case 5:
                        location=new River(player);
                        break;
                    case 6:
                        location=new Mine(player);
                        break;
                    default:
                        System.out.println("Lütfen geçerli bir bölge giriniz!");
                        location=new empty(player);
                }
                if(location==null){
                    System.out.println("Oyundan kaçtınız!");
                    break;
                }
                if(!location.onLocation()){
                    System.out.println("GAME OVER!!");
                    break;
                }
        }
    }
}
