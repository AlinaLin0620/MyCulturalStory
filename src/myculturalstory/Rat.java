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

// rat class
public class Rat extends ZodiacAnimal {
    // variables 
    private boolean shieldUsed = false;
    
    public Rat(PApplet app, int x, int y) {
        super(app, "rat", x, y);
    }
    
    @Override 
    // slight speed boost
    public void move(int dx) {
        if (stunned)
            return;
        if (boostActive) {
            x += dx * 1.5;
        } else {
            x += dx;
        }
    }
    
    @Override 
    // one hit immunity while boost is active 
    public void stun() {
        if (boostActive && !shieldUsed) {
            shieldUsed = true;
            return;
        }
        super.stun();
    }
    
    @Override 
    // end shield useage
    public void onBoostEnd() {
        shieldUsed = false;
    }
}
