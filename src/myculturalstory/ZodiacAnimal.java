/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 *
 * @author alina
 */
import processing.core.PApplet;
import processing.core.PImage;
public class ZodiacAnimal {
    // initialize varriables
    protected PApplet app;
    protected PImage image;
    protected String name;
    protected int x;
    protected int y;
    
    public ZodiacAnimal(PApplet app, String name, int x, int y) {
        this.app = app;
        this.name = name;
        this.x = x;
        this.y = y;
        
        image = app.loadImage("images/" + name + "png");
    }
    
    public void draw() {
        app.image(image, x, y);
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
