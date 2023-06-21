import javax.persistence.*;
import java.util.Date;
//import java.sql.Timestamp;

@Entity
@Table(name = "Hospital_General_Data")
public class Hospital {
    @Id
    @Column(name = "Hospital_ID", length = 64)
    private String hospitalId;

    @Column(name = "Hospital_name")
    private String hospitalName;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "password", length = 32)
    private String password;

    @Column(name = "Email_ID", length = 255)
    private String emailId;

    @Column(name = "Address_1")
    private String address1;

    @Column(name = "Address_2")
    private String address2;

    @Column(name = "State", length = 32)
    private String state;

    @Column(name = "City", length = 32)
    private String city;

    @Column(name = "Country", length = 32)
    private String country;

    @Column(name = "Zip_code")
    private Integer zipCode;

    @Column(name = "created_at")
    private Date createdAt;

    // Getters and setters
    // ...
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
