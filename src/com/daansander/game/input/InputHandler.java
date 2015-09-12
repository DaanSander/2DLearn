package com.daansander.game.input;

import com.daansander.game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daan on 12-9-2015.
 */
public class InputHandler implements KeyListener {

    protected boolean[] keys;

    public InputHandler(Game game) {
        game.addKeyListener(this);
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Onodig
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() >= 0) && (e.getKeyCode() <= keys.length)) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() >= 0) && (e.getKeyCode() <= keys.length)) {
            keys[e.getKeyCode()] = false;
        }
    }

    public boolean[] getKeys() {
        return keys;
    }
}
