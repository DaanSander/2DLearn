package com.daansander.game;

import com.daansander.game.graphics.Screen;
import com.daansander.game.input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Daan on 12-9-2015.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public static final String NAME = "2DGame";

    private JFrame frame;
    private Screen screen;
    private InputHandler inputHandler;

    private volatile boolean running;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
            BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        init();

    }

    public static void main(String[] args) {
        new Game().start();
    }

    public synchronized void start() {
        running = true;
        new Thread(this, "Game").start();
    }

    public synchronized void stop() {

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double ns = 1000000000D / 60D;
        double delta = 0;

        int ticks = 0;
        int frames = 0;

        while (running) {
            long current = System.nanoTime();

            delta += (current - lastTime) / ns;
            lastTime = current;

            while (delta >= 1) {
                tick();
                ticks++;

                delta--;
            }

            render();
            frames++;


            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                System.out.println("FPS " + frames + " TPS " + ticks);

                frames = 0;
                ticks = 0;
            }

        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, getWidth(), getHeight());

//
//        g.setColor(Color.white);
//        g.fillRect(WIDTH / 2, HEIGHT / 2, 100, 100);

        screen.clear();
        screen.render();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();


    }

    public void tick() {
//        if(inputHandler.getKeys()[KeyEvent.VK_A]) {
//            System.out.println("A has been pressed");
//        }
    }

    public void init() {
        screen = new Screen(WIDTH, HEIGHT);
        inputHandler = new InputHandler(this);
    }
}
