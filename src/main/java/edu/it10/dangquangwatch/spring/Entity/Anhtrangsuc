package io.github.tubean.myspringcrud.entity;  

import javax.persistence.*;  

@Entity  
@Table(name = "anhtrangsuc")
public class Anhtrangsuc {  
  @Id  
 @GeneratedValue(strategy = GenerationType.AUTO)  
  private Long maanh;  

  @Column(name = "matrangsuc")  
  private String matrangsuc;  

  @Column(name = "url")  
  private String url;  

  @Column(name = "tenanh")  
  private String tenanh;  

  public User() {}  

  public User(String matrangsuc, String url, String tenanh) {  
    this.matrangsuc = matrangsuc;  
    this.url = url;  
    this.tenanh = tenanh;  
  }  

  public Long getId() {  
    return maanh;  
  }  

  public void setId(Long maanh) {  
    this.maanh = maanh;  
  }  

  public String getMatrangsuc() {  
    return matrangsuc;  
  }  

  public void setMatrangsuc(String matrangsuc) {  
    this.matrangsuc = matrangsuc;  
  }  

  public String getUrl() {  
    return url;  
  }  

  public void setUrl(String url) {  
    this.url = url;  
  }  

  public String getTenanh() {  
    return tenanh;  
  }  

  public void setTenanh(String tenanh) {  
    this.tenanh = tenanh;  
  }  
}