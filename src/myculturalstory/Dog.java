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

// dog class
public class Dog extends ZodiacAnimal {
    public Dog(PApplet app, int x, int y) {
        super(app, "dog", x, y);
    }
    
    @Override 
    public void move(int dx) {
        if (boostActive && !onGround) {
            x += dx * 4;
        } else {
            super.move(dx);
        }
    }
}
