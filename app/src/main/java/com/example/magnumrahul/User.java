package com.example.magnumrahul;

public class User {
    private String image;
    private String userName;

    public User(String image, String userName) {
        this.image = image;
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
