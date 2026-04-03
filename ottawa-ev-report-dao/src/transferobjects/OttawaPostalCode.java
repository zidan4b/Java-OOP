package transferobjects;

/**
 * DTO for a row in ottawapostalcodes.
 */
public class OttawaPostalCode {

    private String fsa;
    private String city;
    private String province;
    private double latitude;
    private double longitude;

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
     * @param fsa FSA value
     */
    public void setFsa(String fsa) {
        this.fsa = fsa;
    }

    /**
     * Returns city.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns province.
     *
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets province.
     *
     * @param province province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns latitude.
     *
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns longitude.
     *
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}