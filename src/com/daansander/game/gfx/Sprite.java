package com.daansander.game.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daan on 12-9-2015.
 */
public class Sprite {

    private final String path;
    private Graphics graphics = null;
    private int x;
    private int y;
    private int heigth;
    private int width;
    private BufferedImage image = null;

    public Sprite(String path, int x, int y, int height, int width) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = height;

        init();
    }

    public Sprite(String path) {
        this.path = path;
        this.x = 0;
        this.y = 0;
        this.width = 50;
        this.heigth = 50;

        init();
    }

    public String getPath() {
        return path;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void init() {
        try {
            image = ImageIO.read(new File(getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, getX(), getY(), getWidth(), getHeigth(), null);
    }
}
