package com.nt.ouat1;

public class Student {
    private String name;
    private String rollNo;
    private String physicalAddress;
    private String gender;
    private String dob;
    private int age;
    private String mobileNo;
    private String emailId;
    private String signature;
    private String remarks;
    private String photo;

    public Student() {
    }

    public Student(String name, String rollNo, String physicalAddress, String gender, String dob, int age,
                   String mobileNo, String emailId, String signature, String remarks, String photo) {
        this.name = name;
        this.rollNo = rollNo;
        this.physicalAddress = physicalAddress;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.signature = signature;
        this.remarks = remarks;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
