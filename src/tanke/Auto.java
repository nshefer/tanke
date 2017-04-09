package tanke;

public class Auto {
    private int status; // 0 - default, 1 - schlange for der ZS, 2 - tanken, 3 - schlange vor der Kasse, 4 - zahlen, 5 - raeumen
    private int statusZeit;
    private int tankZeit = (int) Math.random() * (Parameter.zeitTankenMax - Parameter.zeitTankenMin + 1) + Parameter.zeitTankenMin;
    private int zahlZeit = (int) Math.random() * (Parameter.zeitZahlenMax - Parameter.zeitZahlenMin + 1) + Parameter.zeitZahlenMin;
    private int raeumZeit = (int) Math.random() * (Parameter.zeitRaeumenMax - Parameter.zeitRaeumenMin + 1) + Parameter.zeitRaeumenMin;

    public Auto(int aktuelleZeit) {
        statusZeit = aktuelleZeit;

    }

    public void setStatusZeit(int aktuelleZeit) {
        statusZeit = aktuelleZeit;
    }

    public void setStatus() {
        status++;
    }

    public int getStatusZeit() {
        return statusZeit;
    }

    public int getStatus() {
        return status;
    }

    public int getTankZeit() {
        return tankZeit;
    }

    public int getZahlZeit() {
        return zahlZeit;
    }

    public int getRaeumZeit() {
        return raeumZeit;
    }

}
