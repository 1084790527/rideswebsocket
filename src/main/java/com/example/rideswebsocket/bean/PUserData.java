package com.example.rideswebsocket.bean;

import java.util.Date;

public class PUserData {
    private int id;
    private String name;
    private String phone;
    private int roleType;
    private String loginName;
    private String loginPassword;
    private String status;
    private Date cerateAt;
    private Date updateAt;
    private int updateBy;
    private int errorCnt;

    @Override
    public String toString() {
        return "PUserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", roleType=" + roleType +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", status='" + status + '\'' +
                ", cerateAt=" + cerateAt +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", errorCnt=" + errorCnt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCerateAt() {
        return cerateAt;
    }

    public void setCerateAt(Date cerateAt) {
        this.cerateAt = cerateAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(int errorCnt) {
        this.errorCnt = errorCnt;
    }
}
