package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void toStringToimiiOikein() {
        Varasto varasto2 = new Varasto(10);
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto2.toString());
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(-5);
        // edelleen vaikka yritetään lisätä negatiivinen
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
        saatuMaara = varasto.otaVarastosta(-2);
        //otettu määrä 0, kun negatiivinen
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaLiikaa() {
        varasto.lisaaVarastoon(2);

        varasto.otaVarastosta(8);

        // varastossa pitäisi olla tilaa 10 - 2 + 9 = 11, eli 9
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisätäLiikaa() {
        varasto.lisaaVarastoon(5);

        varasto.lisaaVarastoon(8);

        // varastossa pitäisi olla tilaa 10 - 2 + 9 = 11, eli 9
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoAlkuSaldollaToimii() {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(5, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
        
        varasto2 = new Varasto(5, 15);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
        
        varasto2 = new Varasto(-5, -15);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
        
        
    }

}