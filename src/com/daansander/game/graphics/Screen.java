package com.daansander.game.graphics;

/**
 * Created by Daan on 12-9-2015.
 */
public class Screen {

    public int[] pixels;
    private int width;
    private int height;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xff00ff;
            }
        }
    }
}
