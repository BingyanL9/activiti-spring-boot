package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Application {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "application_type", nullable = false, length = 32)
  @Enumerated(EnumType.STRING)
  private Application_Type application_type;
  
  @Column(name = " department", length = 32)
  private String department;
  
  @Column(name = " createtime", length = 32)
  private String createtime;
  
  @ManyToOne(optional = false)
  private User owner;
  
  @Column(name = " cardnum", length = 32)
  private String cardNum;
  
  @Column(name = "hospitalName", length = 32)
  private String hospitalName;
  
  @Column(name = "illnessName", length = 32)
  private String illnessName;
  
  @OneToOne(fetch = FetchType.LAZY)
  private Payee payee;
  
  @Column(name = "paymode", length = 32)
  private String paymode;
  
  @Column(name = "cardpayee", length = 32)
  private String cardPayee;
  
  @Column(name = "travelstartdate", length = 32)
  private String travelStartDate;
  
  @Column(name = "travelenddate", length = 32)
  private String travelEndDate;
  
  @Column(name = "travelreason", length = 100)
  private String travelReason;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<DocumentItem> documentItems;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<CityTrafficItem> cityTrafficItems;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TravelItem> travelItems;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TrafficAllowance> trafficAllowances;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AbroadItem> abroadItems;
  
  @OneToOne(mappedBy = "application", optional = true)
  private AbroadOtherInfo abroadOtherInfo;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Voucher> vouchers;
  
  @Column(name = "total")
  private Double total;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private TeacherUser application_teacher;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private StudentUser application_student;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Project project;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Activity activity;
  
  @OneToOne(fetch = FetchType.LAZY)
  private Approval approval;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Application_Type getApplication_type() {
    return application_type;
  }

  public void setApplication_type(Application_Type application_type) {
    this.application_type = application_type;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public Payee getPayee() {
    return payee;
  }

  public void setPayee(Payee payee) {
    this.payee = payee;
  }

  public String getPaymode() {
    return paymode;
  }

  public void setPaymode(String paymode) {
    this.paymode = paymode;
  }

  public String getCardPayee() {
    return cardPayee;
  }

  public void setCardPayee(String cardPayee) {
    this.cardPayee = cardPayee;
  }

  public String getTravelStartDate() {
    return travelStartDate;
  }

  public void setTravelStartDate(String travelStartDate) {
    this.travelStartDate = travelStartDate;
  }

  public String getTravelEndDate() {
    return travelEndDate;
  }

  public void setTravelEndDate(String travelEndDate) {
    this.travelEndDate = travelEndDate;
  }

  public String getTravelReason() {
    return travelReason;
  }

  public void setTravelReason(String travelReason) {
    this.travelReason = travelReason;
  }

  public List<DocumentItem> getDocumentItems() {
    return documentItems;
  }

  public void setDocumentItems(List<DocumentItem> documentItems) {
    this.documentItems = documentItems;
  }

  public List<CityTrafficItem> getCityTrafficItems() {
    return cityTrafficItems;
  }

  public void setCityTrafficItems(List<CityTrafficItem> cityTrafficItems) {
    this.cityTrafficItems = cityTrafficItems;
  }

  public List<TravelItem> getTravelItems() {
    return travelItems;
  }

  public void setTravelItems(List<TravelItem> travelItems) {
    this.travelItems = travelItems;
  }

  public List<TrafficAllowance> getTrafficAllowances() {
    return trafficAllowances;
  }

  public void setTrafficAllowances(List<TrafficAllowance> trafficAllowances) {
    this.trafficAllowances = trafficAllowances;
  }

  public List<AbroadItem> getAbroadItems() {
    return abroadItems;
  }

  public void setAbroadItems(List<AbroadItem> abroadItems) {
    this.abroadItems = abroadItems;
  }

  public AbroadOtherInfo getAbroadOtherInfo() {
    return abroadOtherInfo;
  }

  public void setAbroadOtherInfo(AbroadOtherInfo abroadOtherInfo) {
    this.abroadOtherInfo = abroadOtherInfo;
  }

  public List<Voucher> getVouchers() {
    return vouchers;
  }

  public void setVouchers(List<Voucher> vouchers) {
    this.vouchers = vouchers;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public TeacherUser getApplication_teacher() {
    return application_teacher;
  }

  public void setApplication_teacher(TeacherUser application_teacher) {
    this.application_teacher = application_teacher;
  }

  public StudentUser getApplication_student() {
    return application_student;
  }

  public void setApplication_student(StudentUser application_student) {
    this.application_student = application_student;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  public Approval getApproval() {
    return approval;
  }

  public void setApproval(Approval approval) {
    this.approval = approval;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getIllnessName() {
    return illnessName;
  }

  public void setIllnessName(String illnessName) {
    this.illnessName = illnessName;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  
}
