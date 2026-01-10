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
    // images
    private PImage gameImg;
    private PImage selectionImg;
    private PImage leftTri;
    private PImage rightTri;
    // frames
    private int gameState = 0;
    // player
    private ZodiacAnimal player;
    // screen selction variables 
    private int leftTriX = 150;
    private int rightTriX = 800;
    private int triY = 500;
    // character selection
    private ZodiacAnimal[] allAnimals;
    private int selectedIndex = 0;
    
    
    public static void main(String[] args) {
        PApplet.main("myculturalstory.ZodiacSketch");
    }
    
    // set frame size
    public void settings() {
        size(1000, 700);
    }
    
    // set up background and player
    public void setup() {
        // background images
        gameImg = loadImage("images/river_1.png");
        selectionImg = loadImage("images/characterSelection.png");
        // selection frame images
        leftTri = loadImage("images/left_tri.png");
        rightTri = loadImage("images/right_tri.png");
        
        // initialize animal all in order
        allAnimals = new ZodiacAnimal[] {
            new Rat(this, width / 2 - 86, height / 2 + 54),
            new Ox(this, width / 2 - 59, height / 2 + 63),
            new Tiger(this, width / 2 - 60, height / 2 + 71),
            new Rabbit(this, width / 2 - 69, height / 2 + 53),
            new Dragon(this, width / 2 - 52, height / 2 + 50),
            new Snake(this, width / 2 - 53, height / 2 + 73),
            new Horse(this, width / 2 - 53, height / 2 + 52),
            new Sheep(this, width / 2 - 58, height / 2 + 43),
            new Monkey(this, width / 2 - 75, height / 2 + 75),
            new Rooster(this, width / 2 - 47, height / 2 + 53),
            new Dog(this, width / 2 - 76, height / 2 + 73),
            new Pig(this, width / 2 - 61, height / 2 + 59)        };
        
        // default player
        selectedIndex = 0;
        player = allAnimals[selectedIndex];
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
        text("Press Enter to confirm selection", 370, 120);
        
        // draw triangles
        if (leftTri != null) image(leftTri, leftTriX, triY);
        if (rightTri != null) image(rightTri, rightTriX, triY);
        
        // draw player character
        if (player != null) player.draw();
    }
    
    // game screen / frame
    private void drawGameScreen() {
        // background 
        if (gameImg != null) {
            image(gameImg, 0, 0, width, height);
        }
        
        // draw player 
        if (player != null) {
            player.update();
            player.draw();
        }
        
        // player movement instructions
        fill(0);
        textSize(12);
        text("Press Arrow Keys to Move and Space to Jump", 10, 20);
    }
    
    private boolean isClicked(PImage img, int imgX, int imgY) {
        return mousePressed && mouseX >= imgX &&mouseX <= imgX + img.width && mouseY >= imgY && mouseY <= imgY + img.height;
    }
    // key controls
    public void keyPressed() {
        // player selection
        if (gameState == 0) {
            // change frame
            if (keyCode == ENTER && player != null) {
                gameState = 1;
                
                // new start position for player 
                int START_X = 100;
                int START_Y = 370;
                
                // center the image horizontally 
                player.x = START_X - player.getWidth() / 2;
                // place animal vetically so image touches ground
                player.y = START_Y - player.getHeight();
            }
        }
        
        // enable movement in new frame
        else if (gameState == 1 && player != null) {
            // move left and right 
            if (keyCode == LEFT) {
                player.move(-5);
            }
            else if (keyCode == RIGHT) {
                player.move(5);
            }
            
            // jump 
            if (key == ' ') {
                player.jump();
            }
        }
    }
    
    // mouse controls
    public void mousePressed() {
        if (gameState == 0) {
            // check left tri
            if (isClicked(leftTri, leftTriX,triY)) {
                selectedIndex--;
                if (selectedIndex < 0) selectedIndex = allAnimals.length - 1;
                player = allAnimals[selectedIndex];
            }
            // check right tri
            else if (isClicked(rightTri, rightTriX, triY)) {
                selectedIndex++;
                if (selectedIndex >= allAnimals.length) selectedIndex = 0;
                player = allAnimals[selectedIndex];
            }
        }
    }
}