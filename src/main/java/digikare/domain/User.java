package digikare.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", unique = true)
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @Column(name = "consents")
    @ManyToMany
    @JoinTable(
            name = "users_consents",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = {@JoinColumn(name = "consent_id"), @JoinColumn(name = "consent_enabled")}
    )
    private Set<Consent> consents = new HashSet<Consent>();

    /**
     * Constructor of User class
     * @param email the email
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Constructor of User class
     */
    public User() {

    }

    /**
     * Gets the user ID
     * @return the user ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user ID
     * @param id the user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the user email
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email
     * @param email the user email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param consents
     */
    public void setConsents(Set<Consent> consents) {
        this.consents = consents;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", consents=" + consents +
                '}';
    }
}
