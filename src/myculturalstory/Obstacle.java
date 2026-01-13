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

public class Obstacle {
    private int x, y;
    private int speed;
    private boolean moving;
    private PImage image;
    
    // basic static obsticle 
    public Obstacle(PApplet app, int x, int y) {
        this.x = x;
        this.y = y;
        this.moving = false;
        
        image = app.loadImage("images/stone.png");
    }
    
    // overload constructor moving obstacle 
    public Obstacle(PApplet app, int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = true;
        
        image = app.loadImage("images/stone.png");
    }
    
    public void update(ZodiacAnimal player) {
        if (moving) {
            if (player.getX() > x) 
                x += speed;
            else 
                x -= speed;
        }
    }
    
    public void draw(PApplet app) {
        app.image(image, x, y);
    }
    
    public boolean touches(ZodiacAnimal player) {
        return player.getX() < x + image.width &&
               player.getX() + player.getWidth() > x &&
               player.getY() < y + image.height &&
               player.getY() + player.getHeight() > y;
    }    
}
