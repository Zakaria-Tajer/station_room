import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class Player<T ,I extends Number> {
    String username;
//    String lastName;
    String timeToPlay;
    int price;
    T playerUniqueId;



    public Player(String username, String timeToPlay, int price, T playerUniqueId) {
        this.username = username;
//        this.lastName = lastName;
        this.timeToPlay = timeToPlay;
        this.price = price;
        this.playerUniqueId = playerUniqueId;
    }

    public T getPlayerUniqueId() {
        return this.playerUniqueId;
    }


}
