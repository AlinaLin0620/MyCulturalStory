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
    private boolean extraJumpUsed = false;
    
    public Rabbit(PApplet app, int x, int y) {
        super(app, "rabbit", x, y);
    }
    
    @Override 
    public void jump() {
        if (onGround) {
            velocityY = -12;
            extraJumpUsed = false;
        }
        else if (boostActive && ! extraJumpUsed) {
            velocityY = -12;
            extraJumpUsed = true;
        }
    }
    
    @Override 
    public void onBoostEnd() {
        extraJumpUsed = false;
    }
}

