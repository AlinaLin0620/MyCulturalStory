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
import processing.core.PImage;

public class ZodiacSketch extends PApplet {
    // initialize
    private PImage gameImg;
    private PImage selectionImg;
    private int gameState = 0;
    private ZodiacAnimal player;
    
    public static void main(String[] args) {
        PApplet.main("myculturalstory.ZodiacSketch");
    }
    
    // set frame size
    public void settings() {
        size(1000, 700);
    }
    
    // set up background and player
    public void setup() {
        gameImg = loadImage("images/river_1.png");
        selectionImg = loadImage("images/characterSelection.png");
        // default player as rat
        player = new Rat(this, width / 2 - 86, height / 2 + 54);
    }
    
    // draw in frames
    public void draw() {
        if (gameState == 0) {
            drawStartScreen();
        } else {
            drawGameScreen();
        }
    }
    
    // start screen / frame
    private void drawStartScreen() {
        // background 
        if (selectionImg != null) {
            image(selectionImg, 0, 0, width, height);
        }
        fill(0);
        textSize(40);
        text("Choose your zodiac Animal", width / 2 - 215, 70);
        
        textSize(20);
        text("1-Rat  2-Ox  3-Tiger  4-Rabbit  5-Dragon  6-Snake\n" +
             "7-Horse  8-Sheep  9-Monkey  Q-Rooster  W-Dog  E-Pig\n" +
             "Press Enter to confirm", 290, 120);
        
        if (player != null) {
            player.draw();
        }
    }
    
    // game screen / frame
    private void drawGameScreen() {
        // background 
        if (gameImg != null) {
            image(gameImg, 0, 0, width, height);
        }
        
        // draw player 
        if (player != null) {
            player.draw();
        }
        
        // player movement instructions
        fill(0);
        textSize(12);
        text("Press Arrow Keys to Move", 10, 20);
    }
    
    // key controls
    public void keyPressed() {
        // player selection
        if (gameState == 0) {
            if (key == '1') player = new Rat(this, width / 2 - 86, height / 2 + 54);
            else if (key == '2') player = new Ox(this, width / 2 - 59, height / 2 + 63);
            else if (key == '3') player = new Tiger(this, width / 2 - 60, height / 2 + 71);
            else if (key == '4') player = new Rabbit(this, width / 2 - 69, height / 2 + 53);
            else if (key == '5') player = new Dragon(this, width / 2 - 52, height / 2 + 50);
            else if (key == '6') player = new Snake(this, width / 2 - 53, height / 2 + 73);
            else if (key == '7') player = new Horse(this, width / 2 - 53, height / 2 + 52);
            else if (key == '8') player = new Sheep(this, width / 2 - 58, height / 2 + 43);
            else if (key == '9') player = new Monkey(this, width / 2 - 75, height / 2 + 75);
            else if (key == 'q' || key == 'Q') player = new Rooster(this, width / 2 - 47, height / 2 + 53);
            else if (key == 'w' || key == 'W') player = new Dog(this, width / 2 - 76, height / 2 + 73);
            else if (key == 'e' || key == 'E') player = new Pig(this, width / 2 - 61, height / 2 + 59);    
            
            // change frame
            if (keyCode == ENTER && player != null) {
                gameState = 1;
            }
        }
        
        // enable movement in new frame
        else if (gameState == 1 && player != null) {
            if (keyCode == LEFT) player.move(-5, 0);
            else if (keyCode == RIGHT) player.move(5, 0);
            else if (keyCode == UP) player.move(0, -5);
            else if (keyCode == DOWN) player.move(0, 5);
        }
    }   
}