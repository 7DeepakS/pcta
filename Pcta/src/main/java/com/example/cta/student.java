package com.example.cta;

public class student {
    String usn;
    String name;
    Integer roomNo;
    Integer deskNo;
    String branch;
    String examType;

    public student(String usn, String name, Integer roomNo, Integer deskNo, String branch, String examType) {
        this.usn = usn;
        this.name = name;
        this.roomNo = roomNo;
        this.deskNo = deskNo;
        this.branch = branch;
        this.examType = examType;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getDeskNo() {
        return deskNo;
    }

    public void setDeskNo(Integer deskNo) {
        this.deskNo = deskNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }
}
