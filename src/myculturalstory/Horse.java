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

// horse class
public class Horse extends ZodiacAnimal {
    public Horse(PApplet app, int x, int y) {
        super(app, "horse", x, y);
    }
    
    @Override
    public void move(int dx) {
        if (boostActive) {
            int bonus = (app.millis() - boostStartTime) / 3000;
            x += dx * (1 + bonus);
        } else {
            x += dx;
        }
    }
}
