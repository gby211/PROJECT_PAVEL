package com.example.project_pavel;

public class User {
    private String name;
    private String email;
    private String password;
    private Integer admin;
    private String fCom;
    private String id;

    public User() {
    }

    public User (String name, String email, String password, Integer admin, String fCom, String id){
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.fCom = fCom;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getfCom() {
        return fCom;
    }

    public void setfCom(String fCom) {
        this.fCom = fCom;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", fCom='" + fCom + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
