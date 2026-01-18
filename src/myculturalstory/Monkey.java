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

// monkey class
public class Monkey extends ZodiacAnimal {
    public Monkey(PApplet app, int x, int y) {
        super(app, "monkey", x, y);
    }
    
    @Override
    public void update() {
        if(boostActive) {
            if (y > 200)
                y -= 5;
            velocityY = 0;
            onGround = true;
        }
        super.update();
    }
}
