import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class KauppaTest{
    
    
    Pankki pankki;
    Varasto varasto;
    Kauppa k;
    Viitegeneraattori viite;
    
    @Before
    public void setUp(){
    pankki = mock(Pankki.class);
    
    viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "kalja", 2));
    
    when(varasto.saldo(3)).thenReturn(0);
    when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "ruoka", 5));

    // sitten testattava kauppa 
    k = new Kauppa(varasto, pankki, viite);    
    }
    
    
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
    // luodaan ensin mock-oliot
                

    
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     
    k.tilimaksu("pekka", "12345");


    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   

}
    
    @Test
    public void ostetaanKaksiEriTuotettaJaVarmistetaanTilisiirronKutsuminen(){
    

                 

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); //ostetaan tuotetta numero 2 eli kaljaa
    k.lisaaKoriin(1);// ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("Sepi", "666");


    verify(pankki).tilisiirto(eq("Sepi"), eq(42), eq("666"), eq("33333-44455"), eq(7));   

    }
    
    @Test
    public void ostetaanKaksiSamaaTuotettaJaVarmistetaanTilisiirronKutsuminen(){
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("Keijo", "000");
        
        
        verify(pankki).tilisiirto(eq("Keijo"), eq(42), eq("000"), eq("33333-44455"), eq(4)); 
    }
    
    @Test
    public void ostetaanTuoteJokaSaatavillaJaTuoteJokaLoppuJaVarmistetaanTilisiirronKutsuminen(){
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        
        k.tilimaksu("Tarmo", "321321");
        
        verify(pankki).tilisiirto(eq("Tarmo"), eq(42), eq("321321"), eq("33333-44455"), eq(2)); 
    }
    
    
}