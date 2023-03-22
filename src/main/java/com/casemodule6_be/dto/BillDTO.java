package com.casemodule6_be.dto;

import com.casemodule6_be.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;




public class BillDTO {
    private long idAccount;
    private List<DataDTO> data;
    private Double totalPrice;
    private Date date;

    public BillDTO(long idAccount, List<DataDTO> data, Double totalPrice, Date date) {
        this.idAccount = idAccount;
        this.data = data;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public BillDTO() {
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
