package io.github.tubean.myspringcrud.entity;  

import javax.persistence.*;  

@Entity  
@Table(name = "anhdongho")
public class Anhdongho {  
  @Id  
 @GeneratedValue(strategy = GenerationType.AUTO)  
  private Long maanh;  

  @Column(name = "madongho")  
  private String madongho;  

  @Column(name = "url")  
  private String url;  

  @Column(name = "tenanh")  
  private String tenanh;  

  public User() {}  

  public User(String madongho, String url, String tenanh) {  
    this.madongho = madongho;  
    this.url = url;  
    this.tenanh = tenanh;  
  }  

  public Long getId() {  
    return maanh;  
  }  

  public void setId(Long maanh) {  
    this.maanh = maanh;  
  }  

  public String getMadongho() {  
    return madongho;  
  }  

  public void setMadongho(String madongho) {  
    this.madongho = madongho;  
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