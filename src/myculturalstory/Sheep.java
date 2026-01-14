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

// sheep class
public class Sheep extends ZodiacAnimal {
    public Sheep(PApplet app, int x, int y) {
        super(app, "sheep", x, y);
    }
    
    @Override 
    public void update() {
        if (boostActive) {
            velocityY += GRAVITY *0.4f;
        } else {
            velocityY += GRAVITY;
        }
        
        y += velocityY;
        
        if (y >= GROUND_Y) {
            y = GROUND_Y;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
        updateStun();
    }
}
