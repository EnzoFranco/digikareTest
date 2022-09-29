package digikare.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "consents")
    @ManyToMany
    @JoinTable(
            name = "events_consents",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = {@JoinColumn(name = "consent_id"), @JoinColumn(name = "consent_enabled")}
    )
    private Set<Consent> consents;

    /**
     * Gets the id
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the user
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the set of consents
     * @return the set of consents
     */
    public Set<Consent> getConsents() {
        return consents;
    }

    /**
     * Sets the set of consents
     * @param consents the set of consents
     */
    public void setConsents(Set<Consent> consents) {
        this.consents = consents;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", consents=" + consents +
                '}';
    }

}
