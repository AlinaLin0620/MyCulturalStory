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

public class ZodiacAnimal {
    // initialize varriables
    protected PApplet app;
    protected PImage image;
    protected String name;
    protected int x;
    protected int y;
    
    // physics
    protected float velocityY = 0;
    protected final float GRAVITY = 0.8f;
    protected final float JUMP_HEIGHT = -15;
    protected boolean onGround = false;
    protected final int GROUND_Y = 370;
    
    // zodiac animal constructor
    public ZodiacAnimal(PApplet app, String name, int x, int y) {
        this.app = app;
        this.name = name;
        this.x = x;
        this.y = y;
        
        image = app.loadImage("images/" + name + ".png");
    }
    
    // draw in images
    public void draw() {
        app.image(image, x, y);
    }
    
    public void update() {
        velocityY += GRAVITY;
        y += velocityY;
        
        // stop at ground 
        if (y >= GROUND_Y) {
            y = GROUND_Y;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
    }
    
    // jump method
    public void jump() {
        if (onGround) {
            velocityY = JUMP_HEIGHT;
            onGround = false;
        }
    }
    public void move(int dx) {
        x += dx;
    }
    
    public int getWidth() {
        return image.width;
    }
    
    public int getHeight() {
        return image.height;
    }
}
