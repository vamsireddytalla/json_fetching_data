package com.example.naveen.jsondata;

public class Model
{
    private String name,image_url;

    public Model() {
    }

    public Model(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
