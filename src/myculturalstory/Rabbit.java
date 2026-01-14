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

// rabbit class
public class Rabbit extends ZodiacAnimal {
    // variables 
    public Rabbit(PApplet app, int x, int y) {
        super(app, "rabbit", x, y);
    }
    
    @Override 
    public void jump() {
        if (onGround && !stunned) {
            if (boostActive) {
                velocityY = -18;
            } else {
                velocityY = -12;
            }
            onGround = false;
        }
    }
}

