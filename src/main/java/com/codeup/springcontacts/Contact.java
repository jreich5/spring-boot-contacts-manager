package com.codeup.springcontacts;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50, name="first_name")
    private String firstName;

    @Column(name="middle_initial")
    private char middleInitial;

    @Column(nullable=false, length=50, name="last_name")
    private String lastName;

    @Column(length=10)
    private String suffix;

    @Column
    private String email;

    @Column(length=50, name="phone_number")
    private String phoneNumber;

    @Column(length=1000)
    private String address;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Contact() {
    }

    public Contact(String firstName, char middleInitial, String lastName, String suffix, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.suffix = suffix;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("id: %s%nfirstName: %s%nmiddleInitial: %s%nlastName: %s%nsuffix: %s%nemail: %s%nphoneNumber: %s%naddress: %s%ncreatedAt: %s%nupdatedAt: %s", this.id, this.firstName, this.middleInitial, this.lastName, this.suffix, this.email, this.phoneNumber, this.address, this.createdAt, this.updatedAt);
    }
}
