package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
        Statistics stats1 = new Statistics(readerStub);
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(new Player("Yzerman", "DET", 42, 56));
        System.out.println(list.get(0));
        System.out.println(stats1.topScorers(1));
        
        
        
        
        //Statistics stats = new Statistics();
        Statistics stats = new Statistics(new PlayerReader("http://nhlstats-2013-14.herokuapp.com/players.txt") );  
        
        
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("");
        
        System.out.println("Top scorers");
        for (Player player : stats.topScorers(10) ) {
            System.out.println( player );
        }       

    }
}
