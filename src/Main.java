import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Main {

    static List<Posts> postsList = new ArrayList<>();
    static Times time = new Times();
    static String timeToGetOff = null;
    static String currentTime = time.getCurrentTime();
    static Queue<Posts> queue = new LinkedList<>();
    static String uniqueID = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    static List<Integer> prices = new ArrayList<>();

    public static void addPlayerToPost(Posts posts) {
        postsList.add(posts);
    }

    public static void removePlayerToPost(Posts posts) {

        postsList.remove(posts);
        System.out.println("------------------------------ Player has been removed from post --------------------------------------");
        System.out.println(posts.postUniqueId);
    }


    public static void showPlayers() {
        for (Posts p : postsList) {
            System.out.println("Post uniqueId: " + p.postUniqueId);
            System.out.println("user Name: " + p.playerOccupyingThePostUsername);
            System.out.println("Console playing in: " + p.consolePLayingIn);
            System.out.println("Game playing: " + p.gamePlaying);
            System.out.println("Time Start playing: " + p.timeToStartPlaying);
            System.out.println("Time end playing: " + p.timeToEndPlaying);
            System.out.println("=========================================================== || =========================================================");
        }
    }

    public static void waitingList(Posts post) {
        queue.add(post);
        System.out.println("added a player" + post.playerOccupyingThePostUsername);
    }

    public static void handleRemovedPlayer(Posts posts) {
        List<Posts> postsTemp = new ArrayList<>();
        postsTemp.add(posts);
        System.out.println(postsTemp.size());
        for (Posts tempPost : postsTemp) {
            Posts temperoryPosts = new Posts(tempPost.postUniqueId, tempPost.playerOccupyingThePostUsername, tempPost.price, tempPost.timeWanted, tempPost.gameGenera, tempPost.consolePLayingIn, tempPost.gamePlaying, tempPost.timeToStartPlaying, tempPost.timeToEndPlaying, time.getMonths());
            addPlayerToPost(temperoryPosts);

        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to stationRoom ");

        System.out.println(String.valueOf(time.getMonths().getMonthValue()) + "  " + time.getMonths().toString().charAt(6));

        boolean timeInPM = currentTime.contains("PM");
        boolean timeInAM = currentTime.contains("AM");
        float parsedTime = Float.parseFloat(currentTime.substring(1, 5));

//        if (
//                timeInPM && parsedTime == 2 || timeInPM && parsedTime < 8 || timeInAM && parsedTime == 9 || timeInAM && parsedTime < 12
//        ) {

        System.out.println("we are open");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        do {
            if (postsList.size() == 2) {

                for (Posts p : postsList) {
                    if (!time.getCurrentTime().equals(p.timeToEndPlaying)) {
                        removePlayerToPost(p);
                        for (Posts listQueue : queue) {
                            if (listQueue.consolePLayingIn.equals(p.consolePLayingIn)) {
                                addPlayerToPost(queue.poll());
                                showPlayers();
                            }
                        }
                        showPlayers();
                    }
                }


                Consoles<String> videos = new Consoles<>();

                System.out.println("Sorry all posts are already in use");
                System.out.println("if you want to wait press 1:");
                int startQueue = scanner.nextInt();

                int QueuePrice = 0;
                String QueueTimeToPLay = null;

                String username = null;
                switch (startQueue) {
                    case 1 -> {
                        username = userInfo();

                        switch (timeChoice()) {
                            case 1 -> {
                                QueuePrice = 5;
                                QueueTimeToPLay = "2";
                                System.out.println("you have " + QueueTimeToPLay + " min");
                            }
                            case 2 -> {
                                QueuePrice = 10;
                                QueueTimeToPLay = "60";
                                System.out.println("you have 1H");
                            }
                            case 3 -> {
                                QueuePrice = 18;
                                QueueTimeToPLay = "120";
                                System.out.println("you have " + QueueTimeToPLay + " min");
                            }
                            case 4 -> {
                                QueuePrice = 40;
                                QueueTimeToPLay = "300";
                                System.out.println("you have " + QueueTimeToPLay + " min");
                            }
                            case 5 -> {
                                QueuePrice = 65;
                                QueueTimeToPLay = "540";
                                System.out.println("you have " + QueueTimeToPLay + " min");
                            }
                        }
                        System.out.println("Choose Genera of games...");
                        System.out.println("1: First-person shooter");
                        System.out.println("2: Sports");
                        System.out.println("3: Action-Adventure");
                        int gameGenera = scanner.nextInt();

                        switch (gameGenera) {
                            case 1 -> {
                                for (int i = 0; i < videos.playStation.length; i++) {
                                    System.out.println(i + 1 + ": " + videos.playStation[i]);
                                }
                                int playStationConsole = scanner.nextInt();
                                Posts playStationPost = new Posts(uniqueID, username, QueuePrice, time.minConverter(QueueTimeToPLay), videos.genera[gameGenera], "playStation", videos.playStation[playStationConsole - 1], currentTime, timeToGetOff, time.getMonths());
                                waitingList(playStationPost);
                            }
                            case 2 -> {
                                for (int i = 0; i < videos.xbox.length; i++) {
                                    System.out.println(i + 1 + ": " + videos.xbox[i]);
                                }
                                int xboxConsole = scanner.nextInt();
                                Posts xboxPost = new Posts(uniqueID, username, QueuePrice, time.minConverter(QueueTimeToPLay), videos.genera[gameGenera], "xbox", videos.xbox[xboxConsole - 1], currentTime, timeToGetOff, time.getMonths());
                                waitingList(xboxPost);
                            }
                            case 3 -> {
                                for (int i = 0; i < videos.nintendoSwitch.length; i++) {
                                    System.out.println(i + 1 + ": " + videos.nintendoSwitch[i]);
                                }

                                int nintendo = scanner.nextInt();
                                Posts xboxPost = new Posts(uniqueID, username, QueuePrice, time.minConverter(QueueTimeToPLay), "Action-Adventure", "nintendo", videos.nintendoSwitch[nintendo - 1], currentTime, timeToGetOff, time.getMonths());
                                waitingList(xboxPost);

                            }

                        }
                    }
                }


            } else {
                getInformation();

            }
            showPlayers();

        } while (choice != 9);


    }

    public static String userInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String username = scanner.nextLine();
        System.out.println("Enter your lastName: ");
        String lastName = scanner.nextLine();

        return username + " " + lastName;
    }

    public static Integer timeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: 30min");
        System.out.println("2: 1H");
        System.out.println("3: 2H");
        System.out.println("4: 5H");
        System.out.println("5: Whole Day");
        System.out.println("Press a number to select your plane: ");

        return scanner.nextInt();
    }


    public static void getInformation() {


        String uniqueID = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Scanner scanner = new Scanner(System.in);
        Consoles<String> videos = new Consoles<>();


        String username = userInfo();


        int price = 0;
        String timeToPlay = null;


        switch (timeChoice()) {
            case 1 -> {
                price = 5;
                timeToPlay = "1";
                timeToGetOff = time.timeWantedToPLay(0, Integer.parseInt(timeToPlay), Integer.parseInt(currentTime.substring(3, 5)));
                System.out.println("you have 30min");
                prices.add(price);
            }
            case 2 -> {
                price = 10;
                timeToPlay = "60";
                System.out.println("you have 1H");
                timeToGetOff = time.timeWantedToPLay(Integer.parseInt(currentTime.substring(1, 2)) + time.minConverter(timeToPlay), 0, 0);
                System.out.println(timeToGetOff);
                prices.add(price);
            }
            case 3 -> {
                price = 18;
                timeToPlay = "120";
                System.out.println("you have 2H");
                prices.add(price);
                timeToGetOff = time.timeWantedToPLay(Integer.parseInt(currentTime.substring(1, 2)) + time.minConverter(timeToPlay), 0, 0);
            }
            case 4 -> {
                price = 40;
                timeToPlay = "300";
                System.out.println("you have 5H");
                prices.add(price);
                timeToGetOff = time.timeWantedToPLay(Integer.parseInt(currentTime.substring(1, 2)) + time.minConverter(timeToPlay), 0, 0);
            }
            case 5 -> {
                price = 65;
                timeToPlay = "540";
                System.out.println("you have whole day");
                prices.add(price);
                timeToGetOff = time.timeWantedToPLay(Integer.parseInt(currentTime.substring(1, 2)) + time.minConverter(timeToPlay), 0, 0);
            }
        }

//
//            Player<String, Integer> player = new Player<>(username, timeToPlay, price, uniqueID);
//


        System.out.println("Choose Genera of games...");
        System.out.println("1: First-person shooter");
        System.out.println("2: Sports");
        System.out.println("3: Action-Adventure");
        int gameGenera = scanner.nextInt();


        switch (gameGenera) {
            case 1 -> {
                for (int i = 0; i < videos.playStation.length; i++) {
                    System.out.println(i + 1 + ": " + videos.playStation[i]);
                }
                int playStationConsole = scanner.nextInt();
                Posts playStationPost = new Posts(uniqueID, username, price, time.minConverter(timeToPlay), videos.genera[gameGenera], "playStation", videos.playStation[playStationConsole - 1], currentTime, timeToGetOff, time.getMonths());
                addPlayerToPost(playStationPost);
            }
            case 2 -> {
                for (int i = 0; i < videos.xbox.length; i++) {
                    System.out.println(i + 1 + ": " + videos.xbox[i]);
                }
                int xboxConsole = scanner.nextInt();
                Posts xboxPost = new Posts(uniqueID, username, price, time.minConverter(timeToPlay), videos.genera[gameGenera], "xbox", videos.xbox[xboxConsole - 1], currentTime, timeToGetOff, time.getMonths());
                addPlayerToPost(xboxPost);
            }
            case 3 -> {
                for (int i = 0; i < videos.nintendoSwitch.length; i++) {
                    System.out.println(i + 1 + ": " + videos.nintendoSwitch[i]);
                }

                int nintendo = scanner.nextInt();
                Posts xboxPost = new Posts(uniqueID, username, price, time.minConverter(timeToPlay), "Action-Adventure", "nintendo", videos.nintendoSwitch[nintendo - 1], currentTime, timeToGetOff, time.getMonths());
                addPlayerToPost(xboxPost);


            }
        }
        System.out.println("1: check Day balance: ");
        System.out.println("2: check month balance: ");
        int allPrices = scanner.nextInt();
        switch (allPrices) {
            case 1 -> {
                for (Posts posts : postsList) {
                    if (time.getMonths().equals(posts.dateEntered)) {
                        int i = 0;
                        i += posts.price;
                        System.out.println("current balance for the day is: " + "" + i);
                    }
                }
            }
            case 2 -> {
                for (Posts posts : postsList) {
                    if (posts.dateEntered.toString().charAt(6) == time.getMonths().toString().charAt(6)) {
                        int i = 0;
                        i+= posts.price;
                        System.out.println("Months balance is: " + i);
                    }else {
                        System.out.println("No balance for this month" );
                    }
                }
            }
        }


    }


}

