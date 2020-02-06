import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

//Based on Existing player class on Git
//Automated Sections can be dropped into any other class... or an Updated Player Class
//Perhaps we should have a Token or Actor class which the Player and similar classes Extend/inplement
//Aidan Mckernan

//Things Class should do:
/*
    Pathfind to Point
    Allow Player Control
    Pathfind to Target
    Die
 */

public class FriendlyAI {
    private Scene currentScene;
    private int x, y = 0;
    private int offset = 5;
    private Map map;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Pane pane;

    public FriendlyAI(Pane pane, Map map){
        this.map = map;
        this.pane = pane;
        createFriendlyAI();
    }

    public FriendlyAI(Pane pane, Map map, int x, int y){
        this.x = x;
        this.y = y;
        this.map = map;
        this.pane = pane;
        createFriendlyAI();
    }
    public void createFriendlyAI(){
        pane.getChildren().add(rect);
        rect.setFill(Color.LIGHTGRAY);
        rect.setStroke(Color.WHITE);
        rect.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);
        //movePlayer();
    }

    //Method determines a simple Rectangular path to a Point from Current position
    //Does not account for Obstacles in Path
    //Returns Queue of Movements
    //Possible Future Actions: Implement A* Pathfinding Program; Waypoints
    //Seperate method for Future Proofing
    private Queue pathfinder(int endX, int endY){
        Queue movements = new LinkedList();
        if(endX > x){
            for(int i = x; x <= endX; i++){
                movements.add(1);//Right
            }
        }else{
            for(int i = endX; endX <= x; i++){
                movements.add(2);//Left
            }
        }

        if(endY > y){
            for(int i = y; i >= endY; i++){
                movements.add(3);//Down
            }
        }else{
            for(int i = endY; i >= y; i++){
                movements.add(4);//Up
            }
        }//Simplistic

        return movements;
    }

    //Future Proofing
    private Queue pathfinder(int endX, int endY){
        Queue movements = new LinkedList();
        if(endX > x){
            for(int i = x; x <= endX; i++){
                movements.add(1);//Right
            }
        }else{
            for(int i = endX; endX <= x; i++){
                movements.add(2);//Left
            }
        }

        if(endY > y){
            for(int i = y; i >= endY; i++){
                movements.add(3);//Down
            }
        }else{
            for(int i = endY; i >= y; i++){
                movements.add(4);//Up
            }
        }//Simplistic

        return movements;
    }

    //Automatically follows path, checks fo Walls
    public boolean autoMove(int action){

            if(action == 1){
                if(map.tiles[x+1][y].getTileType() == 0) {
                    x = x + 1;
                    return true;
                }else{
                    System.out.println("AI has Hit Wall, needs New Path");
                    return false;
                }
            }else if(action == 2){
                if(map.tiles[x-1][y].getTileType() == 0) {
                    x = x - 1;
                    return true;
                }else{
                    System.out.println("AI has Hit Wall, needs New Path");
                    return false;
                }
            }else if(action == 3){
                if(map.tiles[x][y+1].getTileType() == 0) {
                    y = y + 1;
                    return true;
                }else{
                    System.out.println("AI has Hit Wall, needs New Path");
                    return false;
                }
            }else{
                if(map.tiles[x][y-1].getTileType() == 0) {
                    y = y - 1;
                    return true;
                }else{
                    System.out.println("AI has Hit Wall, needs New Path");
                    return false;
                }
            }

            //System.out.println("Key Pressed: " + ke.getCode());
            System.out.println("FriendlyAI is @: [" + x + ", " + y + "]");
            rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
            rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);

    }


    //Currently a Chunk of Pusedocode with some actual Java leading off of here
    public void friendlyAIActions(){


        if(plan){//Plan should Fire when Actor clicked on and a Target Space is clicked on

            Queue actions = pathfinder(1,2);//Dummy Values, Intentionally Overbuilt for possible Future Path Finding

            while(!actions.isEmpty()) {
                boolean zen = autoMove(actions.remove());//Intentionally Overbuilt for possible Future Path Finding
                if(!zen){
                    while(!actions.isEmpty()){
                        actions.remove();
                    }
                    System.out.println("I have Hit a Wall and Stopped");
                    System.out.println("queue cleared");
                }
                if(newPlan){
                    //Escape Loop, Execute New Plan
                }
            }
            System.out.println("AI has reached Objective")

            //iterate over queue doing actions
            //Direct Control or Point to Point
                    //Direct Control is Easy, Point to Point must be done.
                //call registerInput(KeyEvent ke)
        }else if(activeControl){
            //do registerInput
        }else{
            //Find XY Co ords of Closest Target Actor
            //Feed Co ords into Pathfinder
            //Execute
            //If Plan Issued Interrupt to Execute Plan
        }

    }

    public void registerInput(KeyEvent ke) {
        if (ke.getCode() == KeyCode.W) {
            if (y != 0) {
                y--;
            }
        }
        if (ke.getCode() == KeyCode.A) {
            if (x != 0) {
                x--;
            }
        }
        if (ke.getCode() == KeyCode.S) {
            if (y != map.getSize_y() - 1) {
                y++;
            }
        }
        if (ke.getCode() == KeyCode.D) {
            if (x != map.getSize_x() - 1) {
                x++;
            }
        }
        if(ke.getCode() == KeyCode.E){
            //Get Map Coordinates
            //Return them to Pathfinder
            //Execute Order
        }
        System.out.println("Key Pressed: " + ke.getCode());
        System.out.println("FriendlyAI is @: [" + x + ", " + y + "]");
        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }
}