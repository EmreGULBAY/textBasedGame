import java.nio.file.LinkOption;
import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String name ;
    protected final Scanner scn=new Scanner(System.in);

    public Location(Player player,String name){
        this.name=name;
        this.player=player;
    }
    abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class empty extends Location{
    public empty(Player player){
        super(player,"nowhere");
    }
    @Override
    public boolean onLocation(){
        return true;
    }
}
