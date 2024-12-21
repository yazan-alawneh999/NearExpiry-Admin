package com.big0soft.nearexpireadmin.data.requests;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;

public class StoreSetupRequest implements FromJson, ToJson {
    private int id;
    private String name;
    private String description;
    private String imageLogo;
    private String backgroundImage;

    public StoreSetupRequest() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass()).fromJson(data);
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}

