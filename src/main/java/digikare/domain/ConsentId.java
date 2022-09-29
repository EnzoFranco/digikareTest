package digikare.domain;

import java.io.Serializable;
import java.util.Objects;


public class ConsentId implements Serializable {

    private String id;

    private boolean enabled;

    /**
     * Constructor of the ConsentId class
     * @param id the consentId
     * @param enabled the enabled boolean
     */
    public ConsentId(String id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    /**
     * Constructor of the ConsentId class
     */
    public ConsentId() {

    }

    /**
     * Gets the consentId
     * @return the consentId
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the consentId
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the enabled boolean
     * @return the enabled boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled boolean
     * @param enabled the enabled boolean
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsentId)) return false;
        ConsentId consentId = (ConsentId) o;
        return Objects.equals(id, consentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enabled);
    }
}
