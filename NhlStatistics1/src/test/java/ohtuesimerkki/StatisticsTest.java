/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Wade
 */
public class StatisticsTest {
 
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
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void hakuOikein() {        
        assertEquals("Semenko", stats.search("Semenko").getName());
        assertEquals(null, stats.search("Sesadad"));
    }
    
    @Test
    public void listausToimii() {        
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(new Player("Yzerman", "DET", 42, 56));
        
        assertEquals(list.size(), stats.team("DET").size());
        assertEquals(list.get(0).toString(), stats.team("DET").get(0).toString());
    }
    
    @Test
    public void topScorersToimii() {        
        
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
    
}
