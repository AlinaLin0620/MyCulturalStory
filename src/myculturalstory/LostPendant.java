/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 *
 * @author alina
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;

public class LostPendant {
    private int x, y;
    private PImage img;
    private boolean collected = false;
    private PApplet app;
    private boolean active = false; 

    // constructor
    public LostPendant(PApplet app, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        img = app.loadImage("images/jade_pendant.png");
    }

    // update method
    public void update(ZodiacAnimal player) {
        if (!active || collected) return;

        draw();
        checkCollection(player);
    }

    private void draw() {
        if (!collected) {
            app.image(img, x, y);
        }
    }

    public void checkCollection(ZodiacAnimal player) {
        if (!collected &&
            player.getX() < x + img.width &&
            player.getX() + player.getWidth() > x &&
            player.getY() < y + img.height &&
            player.getY() + player.getHeight() > y) {
            collected = true;
        }
    }

    public boolean isCollected() {
        return collected;
    }

    public void activate() {
        active = true;
    }

    // getters
    public int getX() { 
        return x; 
    }
    public int getY() { 
        return y; 
    }
    public int getWidth() { 
        return img.width; 
    }
    public int getHeight() { 
        return img.height; 
    }
}

