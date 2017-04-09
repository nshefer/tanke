package tanke;

/**
 * Simuliert ein Auto. Ein Auto ist gleich mit dem Autofahrer (z.B. an der Kasse) assoziiert.
 */
public class Auto {
    private int status; // 0 - default, 1 - schlange for der ZS, 2 - tanken, 3 - schlange vor der Kasse, 4 - zahlen, 5 - raeumen
    private int statusZeit;
    private int tankZeit = (int) Math.random() * (Parameter.zeitTankenMax - Parameter.zeitTankenMin + 1) + Parameter.zeitTankenMin;
    private int zahlZeit = (int) Math.random() * (Parameter.zeitZahlenMax - Parameter.zeitZahlenMin + 1) + Parameter.zeitZahlenMin;
    private int raeumZeit = (int) Math.random() * (Parameter.zeitRaeumenMax - Parameter.zeitRaeumenMin + 1) + Parameter.zeitRaeumenMin;

    /**
     * Konstruktor fuer die Klasse Auto.
     * @param aktuelleZeit Zeit der Erzeugung des Autos
     */
    public Auto(int aktuelleZeit) {
        statusZeit = aktuelleZeit;

    }

    /**
     * Set-Methode fuer statusZeit.
     * @param aktuelleZeit
     */
    public void setStatusZeit(int aktuelleZeit) {
        statusZeit = aktuelleZeit;
    }

    /**
     * Erhoeht den aktuellen Statuswert um eins.
     */
    public void setStatus() {
        status++;
    }

    /**
     * Get-Methode fuer statusZeit.
     * @return statusZeit
     */
    public int getStatusZeit() {
        return statusZeit;
    }

    /**
     * Get-Methode fuer status.
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get-Methode fuer tankZeit.
     * @return tankZeit
     */
    public int getTankZeit() {
        return tankZeit;
    }

    /**
     * Get-Methode fuer zahlZeit.
     * @return zahlZeit
     */
    public int getZahlZeit() {
        return zahlZeit;
    }

    /**
     * Get-Methode fuer raeumZeit.
     * @return raeumZeit
     */
    public int getRaeumZeit() {
        return raeumZeit;
    }

}
