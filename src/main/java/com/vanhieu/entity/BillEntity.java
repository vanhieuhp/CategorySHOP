package com.vanhieu.entity;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity{

    @Column(name = "payment", nullable = false)
    private String payment;

    @Column(name = "total", nullable = false)
    private int total;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "delivery", nullable = false)
    private String delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userOfBill;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public UserEntity getUserOfBill() {
        return userOfBill;
    }

    public void setUserOfBill(UserEntity userOfBill) {
        this.userOfBill = userOfBill;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "payment='" + payment + '\'' +
                ", total=" + total +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }
}
