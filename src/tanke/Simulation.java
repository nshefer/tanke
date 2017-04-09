package tanke;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<Schlange> zapfsaeulen = new ArrayList<>();
    private Schlange kasse = new Schlange();
    private int tick = 0;
    // Zeit fuer die Protokollausgabe
    private int takt = 0;

    public Simulation() {
        zapfsaeulen.add(new Schlange());
        zapfsaeulen.add(new Schlange());
        zapfsaeulen.add(new Schlange());
    }

    //TODO: 2 for-Schleifen kosten mehr, als 1 mit if-Bedingung am Ende
    //plus man soll die Simulationszeit auch flexibel halten (vielleicht eine Variable in Parameter-klasse hinzufuegen?)
    //protokollErweitern() habe ich noch mal am Anfang hinzugefügt, da sie dort von 0 min anfangen
    public void ticken() {
        protokollErweitern();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 300; i++) {
                tick = j + i * 300;

                autoKommt();
                startTanken();
                endTanken();
                startZahlen();
                endZahlen();
                end();

            }
            takt = i * 5;
            protokollErweitern();
        }
    }

    //TODO: setStatus?..
    private void autoKommt() {
        double r = Math.random();
        if (r < 1 / Parameter.abstandAutos) {
            Auto auto = new Auto(tick);
            if (zapfsaeulen.get(0).laenge() < zapfsaeulen.get(1).laenge() &&
                    zapfsaeulen.get(0).laenge() < zapfsaeulen.get(3).laenge()) {
                zapfsaeulen.get(0).addAuto(auto);
            } else if (zapfsaeulen.get(1).laenge() < zapfsaeulen.get(3).laenge()) {
                zapfsaeulen.get(1).addAuto(auto);
            } else {
                zapfsaeulen.get(2).addAuto(auto);
            }
        }
    }

    private void startTanken() {
        for (Schlange zapfsaeule : zapfsaeulen) {
            if (zapfsaeule.laenge() > 0) {
                Auto erstes = zapfsaeule.getAuto();
                if (erstes.getStatus() == 1) {
                    erstes.setStatusZeit(tick);
                    erstes.setStatus();
                }
            }
        }
    }

    //TODO: zur Kasse hinzufuegen
    private void endTanken() {
        for (Schlange zapfsaeule : zapfsaeulen) {
            if (zapfsaeule.laenge() > 0) {
                Auto erstes = zapfsaeule.getAuto();
                if ((erstes.getStatus() == 2) && (tick - erstes.getStatusZeit()) >= erstes.getTankZeit()) {
                    erstes.setStatusZeit(tick);
                    erstes.setStatus();
                    kasse.addAuto(erstes);
                }
            }
        }
    }

    private void startZahlen() {
        if (kasse.laenge() > 0) {
            Auto erstes = kasse.getAuto();
            if (erstes.getStatus() == 3) {
                erstes.setStatus();
                kasse.addWartezeit(tick - erstes.getStatusZeit());
                erstes.setStatusZeit(tick);
            }
        }
    }

    private void endZahlen() {
        if (kasse.laenge() > 0) {
            Auto erstes = kasse.getAuto();
            if (erstes.getStatus() == 4 && (tick - erstes.getStatus()) >= erstes.getZahlZeit()) {
                erstes.setStatus();
                erstes.setStatusZeit(tick);
                kasse.removeAuto();
            }
        }

    }

    private void end() {
        for (Schlange zapfsaeule : zapfsaeulen) {
            if (zapfsaeule.laenge() > 0) {
                Auto erstes = zapfsaeule.getAuto();
                if (erstes.getStatus() == 5 && (tick - erstes.getStatus()) >= erstes.getRaeumZeit()) {
                    zapfsaeule.removeAuto();
                }
            }
        }

    }

    public void header() {

        System.out.format("+------+---------------+---------------+---------------+---------------+\n");
        System.out.format("| Zeit | Warteschlange | Warteschlange | Warteschlange | Warteschlange |\n");
        System.out.format("|      | Zapfsaeule 1  | Zapfsaeule 2  | Zapfsaeule 3  | Zapfsaeule 4  |\n");
        System.out.format("+------+---------------+---------------+---------------+---------------+\n");
        //String leftAlignFormat = "| %-4d | %-13d | %-13d | %-13d | %-13d |\n";
        //System.out.format(leftAlignFormat, 0, 4, 78, 12, 19);
        //System.out.format(leftAlignFormat, 15, 16, 198, 9, 19);
    }


    private void protokollErweitern() {

        int[] warteschlangeLaenge = new int[4];
        int i=0;
        for (Schlange schlange : zapfsaeulen){
            warteschlangeLaenge[i] = schlangeLaengeBerechnen(schlange);
            i++;
        }
        warteschlangeLaenge[3] = schlangeLaengeBerechnen(kasse);
                String leftAlignFormat = "| %-4d | %-13d | %-13d | %-13d | %-13d |\n";
        System.out.format(leftAlignFormat, takt, warteschlangeLaenge[0], warteschlangeLaenge[1], warteschlangeLaenge[2], warteschlangeLaenge[3]);

    }

    //TODO: in UML hinzufuegen

    /**
     * Berechnet die Anzahl der Autos in Warteschlange.<br>
     * Wenn die Laenge der Schlange > 1 ist, muss man das Auto, die gerade bedient wurde, in der Warteschlangenlaenge nicht beruecksichtigen.
     *
     * @param schlange
     * @return Autoanzahl in der Warteschlange
     */
    private int schlangeLaengeBerechnen(Schlange schlange) {
        int autoanzahl = schlange.laenge();
        return (autoanzahl == 0 || autoanzahl == 1) ? 0 : autoanzahl - 1;
    }


    public void output() {
        // Berechnung von durschnittlichen Wartezeiten
        int summeWartezeiten = 0;
        int summeAutosVorTankstelle = 0;
        for (Schlange zapfsaeule: zapfsaeulen){
            summeWartezeiten += zapfsaeule.getGesamtWartezeit();
            summeAutosVorTankstelle += zapfsaeule.getAutoZaehler();
        }

        double durchschnittlicheWartezeitTankstelle = (double) summeWartezeiten / summeAutosVorTankstelle;
        double durchschnittlicheWartezeitAnDerKasse = (double) kasse.getGesamtWartezeit() / kasse.getAutoZaehler();

        int maximaleWartezeitTankstelle = Math.max(Math.max(zapfsaeulen.get(0).getMaximaleWartezeit(), zapfsaeulen.get(1).getMaximaleWartezeit()), zapfsaeulen.get(2).getMaximaleWartezeit());


        System.out.println();
        System.out.println("Durchschnittliche Wartezeit an der Tankstelle betraegt: " + String.format("%.1f", durchschnittlicheWartezeitTankstelle/60) + " Minuten\n");
        System.out.println("Maximale Wartezeit an der Tankstelle betraegt: " + maximaleWartezeitTankstelle/60 + "min " + maximaleWartezeitTankstelle%60 + "s"   + "\n");
        System.out.println();
        System.out.println("Durchschnittliche Wartezeit an der Kasse betraegt: " + String.format("%.1f", durchschnittlicheWartezeitAnDerKasse/60) + " Minuten\n");
        System.out.println("Maximale Wartezeit an der Tankstelle betraegt: " + kasse.getMaximaleWartezeit()/60 + "min " + kasse.getMaximaleWartezeit()%60+ "s"  + "\n");

    }



}
