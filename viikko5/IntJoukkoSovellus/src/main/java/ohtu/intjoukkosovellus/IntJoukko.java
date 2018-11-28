
package ohtu.intjoukkosovellus;
import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] LukuJono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        Alusta(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        Alusta(kapasiteetti, OLETUSKASVATUS);

    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        Alusta(kapasiteetti, kasvatuskoko);

    }
    
    
    public void Alusta(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti on väärä");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko on väärä");//heitin vaan jotain :D
        }
        LukuJono = newJono(kapasiteetti);
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }
    
    private int[] newJono(int kapasiteetti){
        int[] jono = new int[kapasiteetti];
        for (int i = 0; i < jono.length; i++) {
            jono[i] = 0;
        }
        return jono;
    }
    

    public boolean lisaa(int luku) {
        if (!LoytyykoTaulukosta(luku)) {
            LukuJono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % LukuJono.length == 0) {
                int[] taulukkoOld = LukuJono;
                LukuJono = new int[alkioidenLkm + kasvatuskoko];                
                kopioiTaulukko(taulukkoOld, LukuJono);
            }
            return true;
        }
        return false;
    }

    public boolean LoytyykoTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == LukuJono[i]) {
                return true;            
        }}
        return false;
    }

    public boolean poistaTaulukosta(int luku) {
        if(LoytyykoTaulukosta(luku)){            
        int  i;
        for (i = 0; i < alkioidenLkm; i++) {
            if (luku == LukuJono[i]) {                  
                LukuJono[i] = 0;                
                for (int j = i; j < alkioidenLkm - 1; j++) {
                LukuJono[j] = LukuJono[j + 1];                
            }
            alkioidenLkm--;
            return true;               
            }
        }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + LukuJono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += LukuJono[i] + ", ";
            }
            tuotos += LukuJono[alkioidenLkm - 1] + "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = LukuJono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko JoukkoA, IntJoukko JoukkoB) {
        IntJoukko YhdisteJoukko = new IntJoukko();
        int[] aTaulu = JoukkoA.toIntArray();
        int[] bTaulu = JoukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            YhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            YhdisteJoukko.lisaa(bTaulu[i]);
        }
        return YhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko JoukkoA, IntJoukko JoukkoB) {
        IntJoukko LeikkausJoukko = new IntJoukko();
        int[] aTaulu = JoukkoA.toIntArray();
        int[] bTaulu = JoukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    LeikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return LeikkausJoukko;

    }
    
    public static IntJoukko erotus ( IntJoukko JoukkoA, IntJoukko JoukkoB) {
        IntJoukko ErotusJoukko = new IntJoukko();
        int[] aTaulu = JoukkoA.toIntArray();
        int[] bTaulu = JoukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            ErotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            ErotusJoukko.poistaTaulukosta(i);
        }
 
        return ErotusJoukko;
    }
        
}