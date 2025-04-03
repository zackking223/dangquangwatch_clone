package edu.it10.vuquangdung.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loai")
public class Loai {
    @Id
    @Column(name = "loai_id")
    private String loaiId;

    public String getLoaiId() {
        return loaiId;
    }

    public void setLoaiId(String loaiId) {
        this.loaiId = loaiId;
    }
}
