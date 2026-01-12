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
    public Rat(PApplet app, int x, int y) {
        super(app, "rat", x, y);
    }
    
    @Override 
    public void useAbility() {
        if (hasGoodDeed) {
            x += 20;
            hasGoodDeed = false;
        }
    }
}
