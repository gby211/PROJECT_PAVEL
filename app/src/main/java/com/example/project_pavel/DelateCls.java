package com.example.project_pavel;

public class DelateCls {
    private String name;
    private String email;

    public DelateCls() {
    }

    public DelateCls(String name, String email) {
        this.name = name;
        this.email = email;
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

    @Override
    public String toString() {
        return "DelateCls{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
