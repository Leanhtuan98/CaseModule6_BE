package com.casemodule6_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;


@Entity

public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"M/d/yy", "dd.MM.yyyy"})
    private Date checkIn;
    private Date checkOut;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonIgnore
    private Room room;
    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @JsonIgnore
    private Bill bill;
    private Long status;
    private int amountDay;


    public BillDetail() {
    }

    public BillDetail(Long id, Date checkIn, Date checkOut, Room room, Bill bill, Long status, int amountDay) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.bill = bill;
        this.status = status;
        this.amountDay = amountDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        this.amountDay = (int) ((this.getCheckOut().getTime() - this.getCheckIn().getTime()) / (24 * 3600 * 1000));


    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Long isStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public int getAmountDay() {
        return amountDay;
    }

    public void setAmountDay(int amountDay) {

    }
}
