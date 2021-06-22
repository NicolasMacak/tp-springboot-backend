package backend.tpservices.Models.UserTypes;
import Config.Constants.*;
import backend.tpservices.Models.Embedded.Address;
import backend.tpservices.Models.Embedded.Reviews.CompanyReview;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private CompanyType type;
    private String ico;
    private String dic;
    private String icDPH;
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private List<CompanyReview> reviewList = new ArrayList<>();;

//    @OneToMany(cascade = CascadeType.PERSIST)
//    private List<Product> productList;

    public Company(){}
    public Company(String name, CompanyType type, String ico, String dic, String icDPH, Date creationDate) {
        this.name = name;
        this.type = type;
        this.ico = ico;
        this.dic = dic;
        this.icDPH = icDPH;
        this.creationDate = creationDate;
    }
    public Company(String name, CompanyType type, String ico, String dic,
                   String icDPH, Date creationDate, Address address) {
        this.name = name;
        this.type = type;
        this.ico = ico;
        this.dic = dic;
        this.icDPH = icDPH;
        this.creationDate = creationDate;
        this.address = address;
     }

    public boolean isValid() {
         return this.address.isValid() &&
                Pattern.matches(Regex.StringWithSpaces, this.name) &&
                Pattern.matches(Regex.Ico, this.ico) &&
                Pattern.matches(Regex.Dic, this.dic) &&
                Pattern.matches(Regex.IcDPH, this.icDPH);
    }

    public boolean isModifyValid() {
        return this.address.isModifyValid() &&
                (this.name == null || Pattern.matches(Regex.StringWithSpaces, this.name)) &&
                (this.ico == null || Pattern.matches(Regex.Ico, this.ico)) &&
                (this.dic == null || Pattern.matches(Regex.Dic, this.dic)) &&
                (this.icDPH == null || Pattern.matches(Regex.IcDPH, this.icDPH));
    }

    public void update(Company company) {

        this.name = company.getName() != null ? company.getName() : this.name;
        this.type = company.getType() != null ? company.getType() : this.type;
        this.ico = company.getIco() != null ? company.getIco() : this.ico;
        this.dic = company.getDic() != null ? company.getDic() : this.dic;
        this.icDPH = company.getIcDPH() != null ? company.getIcDPH() : this.icDPH;
        this.creationDate = company.getCreationDate() != null ? company.getCreationDate() : this.creationDate;
        this.address.update(company.getAddress());
    }

    public void addReview(CompanyReview review) { this.reviewList.add(review);}

    //-------------------------------------------------

    public Long getId() { return id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CompanyType getType() {
        return type;
    }
    public void setType(CompanyType type) {
        this.type = type;
    }
    public String getIco() {
        return ico;
    }
    public void setIco(String ico) {
        this.ico = ico;
    }
    public String getDic() {
        return dic;
    }
    public void setDic(String dic) {
        this.dic = dic;
    }
    public String getIcDPH() {
        return icDPH;
    }
    public void setIcDPH(String icDPH) {
        this.icDPH = icDPH;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
     public Address getAddress() {
         return address;
     }
     public void setAddress(Address address) {
         this.address = address;
     }
    public List<CompanyReview> getReviewList() {
        return reviewList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", ico='" + ico + '\'' +
                ", dic='" + dic + '\'' +
                ", icDPH='" + icDPH + '\'' +
                ", creationDate=" + creationDate +
                ", address=" + address +
                '}';
    }
}  