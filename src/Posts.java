public class Posts {

    String postUniqueId;
    String playerOccupyingThePostUsername;
    String consolePLayingIn;
    String gamePlaying;
    Integer timeWanted;
    String gameGenera;
    String timeToStartPlaying;
    String timeToEndPlaying;
    int price;


    public Posts(String postUniqueId, String playerOccupyingThePostUsername, int price, int timeWanted, String gameGenera, String consolePLayingIn, String gamePlaying, String timeToStartPlaying, String timeToEndPlaying) {
        this.postUniqueId = postUniqueId;
        this.playerOccupyingThePostUsername = playerOccupyingThePostUsername;
        this.price = price;
        this.timeWanted = timeWanted;
        this.gameGenera = gameGenera;
        this.consolePLayingIn = consolePLayingIn;
        this.gamePlaying = gamePlaying;
        this.timeToStartPlaying = timeToStartPlaying;
        this.timeToEndPlaying = timeToEndPlaying;

    }


}
