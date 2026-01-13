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

// tiger class
public class Tiger extends ZodiacAnimal {
    public Tiger(PApplet app, int x, int y) {
        super(app, "tiger", x, y);
    }
    
    @Override 
    public void jump() {
        if (onGround) {
            if (boostActive) {
                velocityY = -18;
            } else {
                velocityY = -12;
            }
        }
    }
}
