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

public class Firewood {
    // variables 
    private int x, y;
    private boolean collected = false;
    private PImage img;
    private PApplet app;
    
    // constructor 
    public Firewood(PApplet app, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        img = app.loadImage("images/wood.png");
    }
    
    public void update(ZodiacAnimal player) {
        if (!collected) {
            draw();
            checkPickup(player);
        }
    }
    
    private void draw() {
        app.image(img, x, y);
    }
    
    private void checkPickup(ZodiacAnimal player) {
        if (player.getX() < x + img.width &&
            player.getX() + player.getWidth() > x &&
            player.getY() < y + img.height &&
            player.getY() + player.getHeight() > y) {
            collected = true;
            }
    }
    
    public boolean isColelcted() {
        return collected;
    }
}
