package local.sgs.javatodos.models;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Auditable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// User is considered the parent entity

@Entity
@Table(name = "users")
public class User implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, List<UserRoles> userRoles){
        setUsername(username);
        setPassword(password);
        for(UserRoles ur : userRoles){
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);//Encrypts Password
    }

    public void setPasswordNoEncrypt(String password){
        this.password = password;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<SimpleGrantedAuthority> getAuthority(){//Required by Spring

        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for(UserRoles r : this.userRoles){
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }

    @Override
    public Optional getCreatedBy() {
        return Optional.empty();
    }

    @Override
    public void setCreatedBy(Object o) {

    }

    @Override
    public Optional getCreatedDate() {
        return Optional.empty();
    }

    @Override
    public void setCreatedDate(TemporalAccessor temporalAccessor) {

    }

    @Override
    public Optional getLastModifiedBy() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedBy(Object o) {

    }

    @Override
    public Optional getLastModifiedDate() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedDate(TemporalAccessor temporalAccessor) {

    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
