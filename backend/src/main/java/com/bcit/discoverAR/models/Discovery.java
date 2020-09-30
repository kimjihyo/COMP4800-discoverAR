package com.bcit.discoverAR.models;

import com.bcit.discoverAR.models.enums.DiscoveryType;
import com.bcit.discoverAR.models.enums.ShapeType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Discovery implements Serializable {
    @NotNull
    @Enumerated(EnumType.STRING)
    private DiscoveryType type;

    @Enumerated(EnumType.STRING)
    private ShapeType shape;

    private String url;

    private String text;

    private String color;

    private int x;

    private int y;

    public Discovery() {}

    public Discovery(String url, String text, String color, String shape, String type, int x, int y) {
        this.url = url;
        this.text = text;
        this.color = color;
        this.shape = ShapeType.valueOf(shape);
        this.type = DiscoveryType.valueOf(type);
        this.x = x;
        this.y = y;
    }

    public DiscoveryType getType() {
        return type;
    }

    public void setType(DiscoveryType type) {
        this.type = type;
    }

    public ShapeType getShape() {
        return shape;
    }

    public void setShape(ShapeType shape) {
        this.shape = shape;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int xCoord) {
        this.x = xCoord;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
