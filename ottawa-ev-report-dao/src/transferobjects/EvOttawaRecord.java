package transferobjects;

/**
 * DTO for joined EV + Ottawa postal code row.
 */
public class EvOttawaRecord {

    private String fsa;
    private String city;
    private int bev;
    private int phev;
    private int totalEv;

    /**
     * Returns FSA.
     *
     * @return FSA
     */
    public String getFsa() {
        return fsa;
    }

    /**
     * Sets FSA.
     *
     * @param fsa postal prefix
     */
    public void setFsa(String fsa) {
        this.fsa = fsa;
    }

    /**
     * Returns city name.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city name.
     *
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns BEV count.
     *
     * @return BEV
     */
    public int getBev() {
        return bev;
    }

    /**
     * Sets BEV count.
     *
     * @param bev BEV
     */
    public void setBev(int bev) {
        this.bev = bev;
    }

    /**
     * Returns PHEV count.
     *
     * @return PHEV
     */
    public int getPhev() {
        return phev;
    }

    /**
     * Sets PHEV count.
     *
     * @param phev PHEV
     */
    public void setPhev(int phev) {
        this.phev = phev;
    }

    /**
     * Returns total EV count.
     *
     * @return total EV
     */
    public int getTotalEv() {
        return totalEv;
    }

    /**
     * Sets total EV count.
     *
     * @param totalEv total EV
     */
    public void setTotalEv(int totalEv) {
        this.totalEv = totalEv;
    }
}