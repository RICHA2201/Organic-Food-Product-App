package com.example.bigbasket;

public class confirm {
    String name;
    String phone;
    String address;
    String pin;
    String amount;

    public confirm() {
    }

    public confirm(String name, String phone, String address, String pin, String amount) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.pin = pin;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
