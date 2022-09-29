package digikare.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The Consent class
 */
@Entity
@Table(name = "consents")
@IdClass(ConsentId.class)
public class Consent {

    @Id
    private String id;

    @Id
    private boolean enabled;

    /**
     * Constructor of the Consent class
     * @param id the id
     * @param enabled the enabled boolean
     */
    public Consent(String id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    /**
     * Constructor of the Consent class
     */
    public Consent() {

    }

    /**
     * Gets the id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id
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
    public String toString() {
        return "Consent{" +
                "id='" + id + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
