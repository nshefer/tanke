package tanke;

import java.util.ArrayList;

public class Schlange {
    
    private int autoZaehler = 0;
    private int gesamtWartezeit = 0;
    private int maximaleWartezeit = 0;
    private ArrayList<Auto> autos = new ArrayList<>();
    
    public void addAuto (Auto auto){
        autoZaehler++;
        autos.add(auto);
    }
    
    public int laenge(){
        return autos.size();
    }

    public void removeAuto(){
        addWartezeit(autos.get(0).getStatusZeit());
        //removed next line, added it directly to "addWartezeit" method
        //checkMaximaleWartezeit(autos.get(0).getStatusZeit());
        autos.remove(0);
    }

    public Auto getAuto(){
        return autos.get(0);
    }
    
    public int getGesamtWartezeit(){
        return gesamtWartezeit;
    }
    
    public int getMaximaleWartezeit(){
        return maximaleWartezeit;
    }
    
    public int getAutoZaehler(){
        return autoZaehler;
    }


    public void addWartezeit(int wartezeit){
        gesamtWartezeit = gesamtWartezeit + wartezeit;
        checkMaximaleWartezeit(wartezeit);
    }
    
    private void checkMaximaleWartezeit(int weitereWartezeit){
        if(maximaleWartezeit < weitereWartezeit){
            maximaleWartezeit = weitereWartezeit;
        }
    }
    
    
}
