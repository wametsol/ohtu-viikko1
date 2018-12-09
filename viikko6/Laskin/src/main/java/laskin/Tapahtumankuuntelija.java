package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;
    
 

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
    }
    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }                  
    }

}

abstract class Operaatio implements Komento{
    protected TextField tulosk;
    protected TextField syotek;
    protected Button nolla;
    protected Button und;
    protected Sovelluslogiikka sovellus;
    protected int edellinenSyote;
    
    public Operaatio(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovel){
        tulosk = tuloskentta;
        syotek = syotekentta;
        nolla = nollaa;
        und = undo;
        sovellus = sovel;
    }
    
    @Override
    public void suorita(){
        try{
            edellinenSyote = Integer.parseInt(tulosk.getText());
            
        } catch (Exception e){
            
        }
        
        int tulos = laske();
        syotek.setText("");
        tulosk.setText("" + tulos);
        if ( tulos==0) {
        nolla.disableProperty().set(true);
            } else {
        nolla.disableProperty().set(false);
        }
        und.disableProperty().set(false);
        
    }
    @Override
    public void peru(){
        if(edellinenSyote<sovellus.tulos()){
          sovellus.miinus(sovellus.tulos()-edellinenSyote);
        }else{
          sovellus.plus(edellinenSyote-sovellus.tulos());
        }
        
        syotek.setText("");
        tulosk.setText("" + sovellus.tulos());
        if (sovellus.tulos()==0) {
        nolla.disableProperty().set(true);
            } else {
        nolla.disableProperty().set(false);
        }
        und.disableProperty().set(false);
        
        
        
       
        
    }
    protected abstract int laske();
}


class Summa extends Operaatio{
    
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus){
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override 
    protected int laske(){
        try{
            sovellus.plus(Integer.parseInt(syotek.getText()));
            
        } catch (Exception e){
            
        }
        return sovellus.tulos();
    }
}
class Erotus extends Operaatio{

    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovel){
       super(tuloskentta, syotekentta, nollaa, undo, sovel);
    }
    
    @Override 
    protected int laske(){
        try{
            sovellus.miinus(Integer.parseInt(syotek.getText()));
            
        } catch (Exception e){
            
        }
        return sovellus.tulos();
    }
    
    
}

class Nollaa extends Operaatio{
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovel){
        super(tuloskentta, syotekentta, nollaa, undo, sovel);
    }
    
    @Override 
    protected int laske(){
        try{
            sovellus.nollaa();
            
        } catch (Exception e){
            
        }
        return sovellus.tulos();
    }
}



