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

// snake class
public class Snake extends ZodiacAnimal {
    // variables 
    private boolean sliding = false;
    public Snake(PApplet app, int x, int y) {
        super(app, "snake", x, y);
    }
    
    @Override 
    public void useAbility() {
        if (!boostActive) {
            boostActive = true;
            boostStartTime = app.millis();
            sliding = true;
        }
    }
    
    @Override 
    public void onBoostEnd() {
        sliding = false;
    }
    
    // smaller hitbox
    @Override
    public int getHeight() {
        if (sliding)
            return image.height / 2;
        return image.height;
    }
    
    @Override 
    public int getY() {
        return y;
    }
}
