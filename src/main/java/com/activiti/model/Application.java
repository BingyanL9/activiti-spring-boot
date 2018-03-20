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
  
  @Column(name = "application_type", nullable = false, length = 11)
  @Enumerated(EnumType.STRING)
  private Application_Type application_type;
  
  @Column(name = " createtime", length = 32)
  private String createtime;
  
  @Column(name = " cardnum", length = 32)
  private String cardNum;
  
  @OneToOne(mappedBy = "id", optional = true)
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
  
  @OneToOne(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private AbroadOtherInfo abroadOtherInfo;
  
  @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Voucher> vouchers;
  
  @Column(name = "totle")
  private Double totle;
  
  @ManyToOne
  private TeacherUser application_teacher;
  
  @ManyToOne
  private StudentUser application_student;
  
  @ManyToOne
  private Project project;
  
  @ManyToOne
  private Activity activity;

}
