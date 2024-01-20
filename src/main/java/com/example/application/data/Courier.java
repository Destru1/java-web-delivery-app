package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Formula;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Courier extends AbstractEntity {

    private String fullName;

    @OneToMany(mappedBy = "courier")
    @Nullable
    private List<Delivery> couriers = new LinkedList<>();

    @Formula("(select count(c.id) from Delivery d where d.courier_id = id)")
    private int couriersCount;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Nullable
    public List<Delivery> getCouriers() {
        return couriers;
    }

    public void setCouriers(@Nullable List<Delivery> couriers) {
        this.couriers = couriers;
    }

    public int getCouriersCount() {
        return couriersCount;
    }


}
