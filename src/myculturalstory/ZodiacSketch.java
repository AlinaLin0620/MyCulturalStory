/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Main class that handles in the loading of all screens, character and objects
 * Also does user inputs like mousePressed, keys etc
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ZodiacSketch extends PApplet {
    // initialize variables
    private PApplet app;
    // images
    private PImage gameImg;
    private PImage selectionImg;
    private PImage leftTri;
    private PImage rightTri;
    
    // frame system
    private int gameState = STATE_START;
    private static final int STATE_START = 0;
    private static final int STATE_GAME = 1;
    private static final int STATE_END = 2;
    private PImage[] frames;
    private int currentFrame = 0;
    private final int TOTAL_FRAMES = 11;
    
    // player
    private ZodiacAnimal player;
    
    // screen selction variables 
    private int leftTriX = 350;
    private int rightTriX = 600;
    private int triY = 500;
    
    // character selection
    private ZodiacAnimal[][] allAnimals;
    private int selectedRow = 0;
    private int selectedCol = 0;
    private final int ROWS = 3;
    private final int COLS = 4;
    
    // villager quests
    private boolean fishing = false;
    private VillagerBoys villagerB;
    private VillagerOldMan villagerO;
    private VillagerGirls villagerG;
    
    // quest objects 
    private Firewood[] firewood;
    private Fish fish;
    private LostPendant lostItem;
    
    // input for quest 
    private boolean interactPressed = false;
    
    // timer system 
    private int startTime = 0;
    private int endTime = 0;
    private boolean timerRunning = false;
    
    // final screen stats
    private int jumpCount = 0;
    private int villagersSaved = 0;
    private String fileTime = "";
    private int fileJumps = 0;
    private int fileVillagers = 0;
    private int fileStuns = 0;
    private final String statsFile = "endStats.txt";
    
    // obstacles array
    private Obstacle[] obstacles = new Obstacle[6];
    
    
    
    public static void main(String[] args) {
        PApplet.main("myculturalstory.ZodiacSketch");
    }
    
    // set frame size
    public void settings() {
        // widow dimensions
        size(1000, 700);
    }
    
    // set up background and player
    public void setup() {
        // background images
        selectionImg = loadImage("images/characterSelection.png");
        // selection frame images
        leftTri = loadImage("images/left_tri.png");
        rightTri = loadImage("images/right_tri.png");
        // set up animal player 
        resetAllAnimals();
        // default player
        selectedRow = 0;
        selectedCol = 0;
        player = allAnimals[selectedRow][selectedCol];
        // load frame images
        // intializes backgorund arrays
        frames = new PImage[TOTAL_FRAMES];
        // loops thru and laods each background 1-11
        for (int i = 0; i < TOTAL_FRAMES; i++) {
            frames[i] = loadImage("images/background_" + (i+1) + ".png");
        }
        // quest objects 
        // firewood
        // sets up array
        firewood = new Firewood[3];
        // sets each firewood and their position
        firewood[0] = new Firewood(this, 260, 430);
        firewood[1] = new Firewood(this, 450, 430);
        firewood[2] = new Firewood(this, 640, 430);
        
        // sets up fish + position
        fish = new Fish(this, 600, 650);
        
        // sets up jade pendant + position
        lostItem = new LostPendant(this, 510, 290);
        
        // creates villagers of differnt child classe
        // boy villager who needs firewood
        villagerB = new VillagerBoys(this, 500, 350, firewood);
        // old man villager who lost something
        villagerO = new VillagerOldMan(this, 200, 350, lostItem);
        // girl villager who needs fish
        villagerG = new VillagerGirls(this, 700, 350, fish);
        
    }
    
    // draw in frames
    public void draw() {
        // switchs b/w screens depending on game state
        if (gameState == STATE_START) {
            // shows selction menu (start screen)
            showStartScreen();
        } else if (gameState == STATE_GAME) {
            // shows the main gameplay screens
            showGameScreen();
        } else if (gameState == STATE_END) {
            // shows the end screen w/ stats
            showEndScreen();
        }
    }
    
    /**
     * Displays the animal selection screen
     */
    private void showStartScreen() {
        // background 
        if (selectionImg != null) {
            // draws in menu backdrop
            image(selectionImg, 0, 0, width, height);
        }
        // black text
        fill(0);
        // text size
        textSize(40);
        // sets text at mid point and up high
        text("Choose your zodiac Animal", width / 2 - 215, 70);
        // change text size
        textSize(20);
        // sets text just below previous text
        text("Press Enter to confirm selection", 370, 120);
        
        // draw triangles
        if (leftTri != null) 
            // draw left triangle at same Y value as right triangle
            image(leftTri, leftTriX, triY);
        if (rightTri != null) 
            // draw right triangle at same Y value as left triangle
            image(rightTri, rightTriX, triY);
        
        // character info box 
        // white box
        fill(255);
        // draw in rec in centre jsut under previous text
        rect(370, 180, 260, 100);
        
        // black text
        fill(0);
        // text size
        textSize(26);
        // displays animal name depending on selected animal
        text(getAnimalName(selectedRow, selectedCol), 380, 205);
        // change text size
        textSize(16);
        // displays animal description depending on selected animal
        text(getAnimalDescription(selectedRow, selectedCol), 380, 220, 560, 150);
        
        // draw player character
        if (player != null) player.draw();
    }
    
    /**
     * Displays and handles main game play items (quest updates, frames, transitions etc)
     */
    private void showGameScreen() {
        // background 
        /**
         * draws in frames based on this logic
         * if current frame is not shown and is more or equal to frame 0 and is less then
         * the total frame length
         * draw it in at 0,0 (made sure image fit the whole screen)
         */
        if (frames != null && currentFrame >= 0 && currentFrame < frames.length) {
            image(frames[currentFrame], 0, 0, width, height);
        }
        
        // if player reaches last frame, go to end screen
        if (currentFrame == TOTAL_FRAMES - 1) {
            // changes to end screen
            gameState = STATE_END;
            
            // stop timer 
            // marks the final time 
            endTime = millis();
            // stops the timer clock 
            timerRunning = false;
            
            // save and load to display on end screen
            // write any attibutes needed to endStats.txt file
            saveStatsToFile();
            // reads from txt file
            loadStatsFromFile();
        }
        // add in obstacle loop to draw in obstacles
        // loops thru all obstacles
        for (int i = 0; i < obstacles.length; i++) {
            // check if obstacle has been loaded yet
            if (obstacles[i] != null) {
                // updates obstacles (for moving ones)
                obstacles[i].update();
                // draw in obstacles 
                obstacles[i].draw(this);
                // checks for collisions w/ player
                obstacles[i].handleCollision(player);
            }
        }
        
        // draw in all quest objects 
        // draw in all firewood on frame 1 (background #2)
        if (currentFrame == 1) {
            // loops thru all firewood
            for(int i = 0; i < firewood.length; i++) {
                // makes wood appear
                firewood[i].activate();
                // checks if player picks up wood
                firewood[i].update(player, player.isCollectKeyPressed());
            }
            // reset key
            player.setCollectKey(false);
        }
        
        // draw in lost item at frame 4 (background # 5)
        if (currentFrame == 4) {
            // makes pendant appear
            lostItem.activate();
            // checks if pendant is picked up
            lostItem.update(player);
        }
        
        // draw in fish at frame 7 (background 8)
        if (currentFrame == 7) {
            // make pendant appear
            fish.activate();
            // checks if player fished up the fish
            fish.update(player, player.isFishingKeyPressed());
            
            player.setFishingKey(false);
        }
        
        // load in villagers at specific frames 
        // loads in boy at frame 0 (background # 1)
        if (currentFrame == 0) {
            // updates villager boy quest
            villagerB.update(player, interactPressed);
        }
        // loads in old man at frame 3 (background # 4)
        if (currentFrame == 3) {
            // updates villager old man quest
            villagerO.update(player, interactPressed);
        }
        // loads in villager girl at frame 6 )background #7)
        if (currentFrame == 6) {
            // updates villager girl quest
            villagerG.update(player, interactPressed);
        }
        // checks that player hasnt been loaded yet
        if (player != null) {
            // starts player physics (movement, jumps etc)
            player.update();
            // draws in player
            player.draw();
        }
        
        // reset each frame 
        interactPressed = false;
        
        // player movement instructions
        // black text
        fill(0);
        // text size
        textSize(20);
        // set text to what is below
        text("Arrow Keys = Move | Space = Jump | F = Ability | Q = Fish | W = Collect Wood || E = Talk", 10, 20);
        
        // draw timer 
        if (timerRunning) {
            // black text
            fill(0);
            // text size
            textSize(16);
            // place in timer using getElapsedTime method and set it in the top right 
            text("Time: " + getElapsedTime(), 890, 20);
        }
    }
    /**
     * Displays the finals stats loaded from the saved file
     */
    private void showEndScreen() {
        // dark blue background
        background(30,30,80);   
        
        // white text
        fill(255);
        // text size
        textSize(50);
        // game over txt places in top centre
        text("Game Over", 375, 100);
        // change text size
        textSize(30);
        // draw in stats in centre jsut below each other
        text("Final Time: " + fileTime, 380, 250);
        text("Total Jumps: " + fileJumps, 380, 325);
        text("Villagers Saved: " + fileVillagers, 380, 400);
        text("Times Stunned: " + fileStuns, 380, 475);
        // change text size
        textSize(20);
        // let users know to press r to restart and place just under everything else
        text("Press R to restart", 410, 600);
    }
    /**
     * Detects if the mouse is clicking in a specific image's boundaries
     * @param img the PImage that gets checked
     * @param imgX the x cord of the image
     * @param imgY the y cord of the image
     * @return true if mouse clicks the image returns true
     */
    private boolean isClicked(PImage img, int imgX, int imgY) {
        // check mouse state and its cords vs the images boundaries
        return mousePressed 
               && mouseX >= imgX 
               && mouseX <= imgX + img.width 
               && mouseY >= imgY 
               && mouseY <= imgY + img.height;
    }
    // key controls
    public void keyPressed() {
        // player selection screen controls
        if (gameState == STATE_START) {
            // confrim character and start game is ENTER is pressed and there is a char selected
            if (keyCode == ENTER && player != null) {
                // set game state to the game world
                gameState = STATE_GAME;
                
                // new start position for player 
                int START_X = 100;
                int START_Y = 370;
                
                // center the image horizontally 
                player.x = START_X - player.getWidth() / 2;
                // place animal vetically so image touches ground
                player.y = START_Y - player.getHeight();
                
                // start timer 
                /**
                 * millis() is a PAppler method which i learned from PApple documentation 
                 * millis() allows me to incorpeate time in the form of milliseconds
                 * returns milliseconds since program started
                */
                // records start time
                startTime = millis();
                // allows time clock to run and be shown
                timerRunning = true;
                
                // rest stats for new run
                jumpCount = 0;
                villagersSaved = 0;
                
                // spawn in obstacles 
                setupObstacles();
            }
        }
        
        // enable movement in new frame
        else if (gameState == 1 && player != null) {
            // move left
            if (keyCode == LEFT) {
                // moves character x to left by 5
                player.move(-5);
                // if the current frame is 0 and player is on left most part of screen
                if (currentFrame == 0 && player.x < 0) {
                    // stops player at left edge
                    player.x = 0;
                // if current frame is greater than 0 and the player isnt on left most edge
                } else if (currentFrame > 0 && player.x < 0) {
                    // go to previous frame
                    currentFrame--;
                    // move player to right most side without crossing the boundaries
                    player.x = width - player.getWidth();
                    
                    // spawn in obstacles form previous screen (the new current screen)
                    setupObstacles();
                }
            }
            // move right
            else if (keyCode == RIGHT) {
                // moves character x to right by 5
                player.move(5);
                /** if the current frame 1 less that total frames
                 * and the players current x cord is greater than the the width 
                 * of the screen minus the players width
                 */
                if (currentFrame == TOTAL_FRAMES - 1 && player.x > width - player.getWidth()) {
                    // stops player at the end of the race
                    player.x = width - player.getWidth();
                /** if the current frame is less than total -1 and players x cord
                 * is greater than the width of the screen - width of the player
                 */
                } else if (currentFrame < TOTAL_FRAMES -1 && player.x > width - player.getWidth()) {
                    // move to next frame
                    currentFrame++;
                    // load player in on the left most side of the next frame
                    player.x = 0;
                    
                    // spawn in obstacles for new screen 
                    setupObstacles();
                }
            }
            
            // jump logic
            // if space bar (which mean this ' ')
            if (key == ' ') {
                // allow jumping physics
                player.jump(); 
                // add to jump count
                jumpCount++;
            }
            
            // special ability
            // if f upper or lower case is pressed
            if (key == 'f' || key == 'F') {
                // allow animal special ability
                player.useAbility();
            }
            
            // fishing 
            // if q uper or lower case is pressed
            if (key == 'q' || key == 'Q') {
                // allow fishing key input 
                player.setFishingKey(true);
            }
            
            // villager interaction
            // if e upper or lower case is pressed
            if (key == 'e' || key == 'E') {
                // start interactions
                interactPressed = true;
            }
            
            // collect wood
            // if w upper or lower case is pressed
            if (key == 'w' || key == 'W') {
                // allow pick up attempts
                player.setCollectKey(true);
            }
        }
        // restart logic from end screen
        if (gameState == STATE_END) {
            // if r upper or lower case is pressed
            if (key == 'r' || key == 'R') {
                // back to start screen
                gameState = STATE_START;
                // reset frames
                currentFrame = 0;
                
                // reset player posistion
                resetAllAnimals();
                
                // reset back to first animal in array 
                player = allAnimals[0][0];
                // clear physics code and timers
                player.resetStatus();
                // respawn villagers and objects
                resetGameObjects();
                // set clock timer to false to turn off
                timerRunning = false;
                // reset start time to 0
                startTime = 0;
                
                // clear obstacles  
                setupObstacles();
            }
        }
    }
    
    // mouse controls
    public void mousePressed() {
        // checks if its start screen
        if (gameState == STATE_START) {
            // check left tri image is clicked
            if (isClicked(leftTri, leftTriX,triY)) {
                // move to previous animal in the index
                selectedCol--;
                // if we go past the left most index move back to end and up one row
                if (selectedCol < 0) {
                    selectedCol = COLS - 1;
                    selectedRow--;
                }                   
                // checks if you surpass the index and brings you back to the last index
                if (selectedRow < 0) 
                    selectedRow = ROWS - 1;
            
            // check right tri image is clicked
            } else if (isClicked(rightTri, rightTriX, triY)) {
                // move to animal in the next index
                selectedCol++;
                // if we go past the right most index move back to end and down one row
                if (selectedCol >= COLS) {
                    selectedCol = 0;
                    selectedRow++;
                }
                // check if you surpass the index and brings you back to the first index
                if (selectedRow >= ROWS) 
                    selectedRow = 0;
            }
            // update player to selected index
            player = allAnimals[selectedRow][selectedCol];
            
            // reset status 
            player.resetStatus();
            
        }
    }
    
     /**
      * Gets the animal name using 2D array
      * @param row The current row in the array
      * @param col The current column in the array
      * @return The name of the animal at the chosen index
      */
    private String getAnimalName(int row, int col) {
        // set up 2D array that matched name to character
        String[][] names = {
                {"Rat", "Ox", "Tiger", "Rabbit"},
                {"Dragon", "Snake", "Horse", "Sheep"},
                {"Monkey", "Rooster", "Dog", "Pig"}
        };
        // return the name of selected index
        return names[row][col];
    }
    
    /**
     * Gets the animal descriptions using 2D array
     * @param row The current row in the array
     * @param col The current column in the array
     * @return The description of the animal at the chosen index
     */
    private String getAnimalDescription(int row, int col) {
        // set up 2D array that matches description to character
        String[][] description = {
            {"Special Ability: \nOne hit shield \nSmall speed boost",
            "Special Ability: \nTotal immunity to stuns",
            "Special Ability: \n2.5X speed sprint",
            "Special Ability: \nSuper high jump"
            },
            {"Special Ability: \nDouble Jump",
            "Special Ability: \nSlither(smaller hit box)",
            "Special Ability: \nSpeed increase over time",
            "Special Ability: \nCan move while stunned"
            },
            {"Special Ability: \nFloats higher",
            "Special Ability: \nMedium speed boost \nAnti-stun",
            "Special Ability: \nLong jump",
            "Special Ability: \nFast fall \nAnti-stun"
            },
        };
        // return the description of the selected index
        return description[row][col];
    }
    /**
     * Formats the elapsed game time into a readable string
     * @return Time in MM:SS:mm format
     */
    private String getElapsedTime() {
        int elapsed;
        // if the timer is going
        if (timerRunning) {
            // timer since started is equal to the current time - the time the game started (preseed enter)
            elapsed = millis() - startTime;
        } else {
            // time at end of the game is equal to the endtime - the start time
            elapsed = endTime - startTime;
        }
    
    // display time in MM:SS:MM
    // mintues = time elapsed / 1000 * 60 (the amount of mm in a minute)
    int minutes = (elapsed / (1000 * 60));
    // seconds = time divied by 1000 to get total seconds, then %60 keeps only the leftover seconds and removes full mintues
    int seconds = (elapsed / 1000) % 60;
    // milsec = %1000 keeps only the leftovers so only shows the remainder mm that don't equal up to a second
    int milliseconds = elapsed % 1000;
    // rounds mm to 2 digits
    milliseconds = Math.round(milliseconds / 10.0f);
    // return in format of minutes:seconds:milliseconds
    return minutes + ":" + seconds + ":" + milliseconds;
    }
    
    /**
     * Increased the count of villagers saved
     */
    public void countVillagersSaved() {
        villagersSaved++;
    }
    /**
     * Writes current game stats to a text file (endStats.txt)
     */
    private void saveStatsToFile() {
        // try-catch to allow proccessing of rest of code if error is encountered
        try {
            // create file object
            File file = new File(statsFile);
            // creates file writer and overwrites exisiting file
            FileWriter writer = new FileWriter(file, false);
            // write in the format of time,jumps,villagers,stuns using commas to seperate
            writer.write(getElapsedTime() + "," + jumpCount + "," + villagersSaved + "," + player.getStunCount() + "\n");
            
            // close writer
            writer.close();
        // catch and show if errors occured
        } catch (IOException e) {
            System.out.println("Java exception" + e);
        }
    }
    /**
     * Read the saved stats back for the end screen
     */
    private void loadStatsFromFile() {
        // try-catch to allow proccessing of rest of code if error is encountered
        try {
            // get from created file
            File file = new File(statsFile);
            // initalize scanner for this file
            Scanner fileInput = new Scanner(file);
            
            // loop thru lines
            while (fileInput.hasNextLine()) {
                // reads lines
                String output = fileInput.nextLine();
                // split line by comma
                String[] info = output.split(",");
                
                // store into display varibales 
                if (info.length >= 4) {
                    // create time string and trim
                    fileTime = info[0].trim();
                    // sets jump file and changes string into int and trim
                    fileJumps = Integer.parseInt(info[1].trim());
                    // sets villager file and changes string into int and trim
                    fileVillagers = Integer.parseInt(info[2].trim());
                    // sets stun file and changes string into int and trim
                    fileStuns = Integer.parseInt(info[3].trim());
                }
            }
            // close reader
            fileInput.close();
        // catch and show if errors occured
        } catch (IOException e) {
            System.out.println("JAVA exception " + e);
        }
    }
    /**
     * Reset villager and quest objects to original states
     */
    private void resetGameObjects() {
        // reset firewood items
        firewood[0] = new Firewood(this, 260, 430);
        firewood[1] = new Firewood(this, 450, 430);
        firewood[2] = new Firewood(this, 640, 430);
        
        // reset fish
        fish = new Fish(this, 600, 650);
        // reset lost pendant
        lostItem = new LostPendant(this, 510, 290);
        
        // reset all villagers
        villagerB = new VillagerBoys(this, 500, 350, firewood);
        villagerO = new VillagerOldMan(this, 200, 350, lostItem);
        villagerG = new VillagerGirls(this, 700, 350, fish);
        
        // clear inputs
        interactPressed = false;
        fishing = false;
    }
    /**
     * Create all 12 animal instances in a 2D array for selection menu
     */
    private void resetAllAnimals() {
        // initialize animal all in order with specifc width and length to centre them
        allAnimals = new ZodiacAnimal[ROWS][COLS];
        allAnimals[0][0] = new Rat(this, width / 2 - 72, height / 2 + 85);
        allAnimals[0][1] = new Ox(this, width / 2 - 53, height / 2 + 83);
        allAnimals[0][2] = new Tiger(this, width / 2 - 55, height / 2 + 87);
        allAnimals[0][3] = new Rabbit(this, width / 2 - 57, height / 2 + 83);
            
        allAnimals[1][0] = new Dragon(this, width / 2 - 43, height / 2 + 83);
        allAnimals[1][1] = new Snake(this, width / 2 - 48, height / 2 + 88);
        allAnimals[1][2] = new Horse(this, width / 2 - 44, height / 2 + 83);
        allAnimals[1][3] = new Sheep(this, width / 2 - 44, height / 2 + 85);
            
        allAnimals[2][0] = new Monkey(this, width / 2 - 70, height / 2 + 85);
        allAnimals[2][1] = new Rooster(this, width / 2 - 38, height / 2 + 84);
        allAnimals[2][2] = new Dog(this, width / 2 - 70, height / 2 + 85);
        allAnimals[2][3] = new Pig(this, width / 2 - 52, height / 2 + 84);  
    }
    
    /**
     * Spawn obstacles to specific frames
     */
    private void setupObstacles() {
        // loop thru obstacles
        for (int i = 0; i < obstacles.length; i++)
            // sets all obstacles to null until obstacles are initalized in switch statement
            obstacles[i] = null;
        //spawn in obstacles to the frames corisponding to the case number
        switch(currentFrame) {
            // frame 1
            case 1:
                // create static obstacle
                obstacles[0] = new Obstacle(this, 700, 540);
                // create moving obstacle
                obstacles[1] = new Obstacle(this, 1200, 480, 18);
                break;
            // frame 2
            case 2:
                obstacles[0] = new Obstacle(this, 200, 540);
                obstacles[1] = new Obstacle(this, 500, 540);
                obstacles[2] = new Obstacle(this, 800, 540);
                obstacles[3] = new Obstacle(this, 1200, 480, 18);
                break;
            // frame 4
            case 4:
                obstacles[0] = new Obstacle(this, 200, 540);
                obstacles[1] = new Obstacle(this, 800, 540);
                obstacles[2] = new Obstacle(this, 1200, 480, 18);
                break;
            // frame 5
            case 5:
                obstacles[0] = new Obstacle(this, 470, 540);
                obstacles[1] = new Obstacle(this, 1200, 480, 18);
                obstacles[2] = new Obstacle(this, 1700, 300, 18);
                break;
            // frame 7
            case 7:
                obstacles[0] = new Obstacle(this, 250, 540);
                obstacles[1] = new Obstacle(this, 750, 540);
                obstacles[2] = new Obstacle(this, 1200, 480, 18);
                break;
            // frame 8  
            case 8:
                obstacles[0] = new Obstacle(this, 300, 540);
                obstacles[0] = new Obstacle(this, 700, 540);
                obstacles[2] = new Obstacle(this, 1700, 300, 18);
                break;
            // frame 9
            case 9:
                obstacles[0] = new Obstacle(this, 400, 540);
                obstacles[1] = new Obstacle(this, 1200, 480, 18);
                obstacles[2] = new Obstacle(this, 1700, 300, 18);
                break;
                
        }
    }
}