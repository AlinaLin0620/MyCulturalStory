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

public class Fish {
    // variables
    private int x, y;
    private PImage img;
    private boolean caught = false;
    private PApplet app;
    private boolean active = false;
    
    // constructor 
    public Fish(PApplet app, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        img = app.loadImage("images/fish.png");
    }
    
    public void update(ZodiacAnimal player, boolean fishingKeyPressed) {
        if (!active || caught) 
            return;
        
        draw();
        checkCatch(player, fishingKeyPressed);
    }
    
    private void draw() {
        app.image(img, x, y);
    }
    
    private void checkCatch(ZodiacAnimal player, boolean fishingKeyPressed) {
        boolean touching =
            player.getX() + player.getWidth() > x &&
            player.getX() < x + img.width;

        if (touching && fishingKeyPressed) {
            caught = true;
            player.setFishingKey(false);
        }
    }
    
    public boolean isCaught() {
        return caught;
    }
    
    public void activate() {
        active = true;
    }
}
