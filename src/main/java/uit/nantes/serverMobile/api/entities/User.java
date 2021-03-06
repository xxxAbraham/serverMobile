package uit.nantes.serverMobile.api.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * @author Djurdjevic Sacha
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idUser", nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String pseudo;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Transient
    private boolean exist;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Expense> expenseList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Event> eventAdminList;

    @ManyToMany(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "user_event",
            joinColumns = {
                @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "event_id")})
    private List<Event> eventList;

    public User() {
        super();
    }

    public User(String pseudo, String email, String password) {
        super();
        this.id = UUID.randomUUID().toString();
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public List<Event> getEventAdminList() {
        return eventAdminList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public void setEventAdminList(List<Event> eventAdminList) {
        this.eventAdminList = eventAdminList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public String getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void exist(){
        this.exist = true;
    }
    
    public void notExist(){
        this.exist = false;
    }
    
    public boolean doesExist(){
        boolean result = false;
        if(this.exist)
            result = true;
        return result;
    }
    
    public void createId(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", speudo=" + pseudo + ", email=" + email + ", password=" + password + '}';
    }

}
