package objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: brad is weird
 * Date: 4/4/13
 * Time: 6:45 PM
 */
public class Enemy extends Entity {

    private ArrayList<Point> ClosedSet;
    private ArrayList<Point> OpenSet;
    private Point start;
    private Point goal;

    public Enemy() {
        super(Thing.enemyImg);
        //TODO: change this later
        x = 19;
        y = 19;
    }

    private int getHScore(Point current, Point goal){
        return Math.abs(goal.x - current.x) + Math.abs(goal.y - current.y);
    }

    private Point findLowestFScore(ArrayList<Point> openSet, int[][] fscore){
        Point result = new Point();
        int lowestScore = Integer.MAX_VALUE;
        for(int i = 0; i < openSet.size(); i++){
            if(fscore[openSet.get(i).y][openSet.get(i).x] < lowestScore){
                lowestScore = fscore[openSet.get(i).y][openSet.get(i).x];
                result = openSet.get(i);
            }
        }
        return result;
    }

    private Point reconstructPath(Point[][] cameFrom, Point currentPos){
        Point previous = cameFrom[currentPos.y][currentPos.x];
        if(cameFrom[previous.y][previous.x] != null && previous != null){
            Point r = reconstructPath(cameFrom, previous);
            return r;
        }
        else{
            return currentPos;
        }
    }

    private int findDirection(Point start, Point step){
        if((step.y-1) == start.y) return 4;
        if((step.y+1) == start.y) return 2;
        if((step.x-1) == start.x) return 3;
        if((step.x+1) == start.x) return 1;
        else return 0;
    }

    private void initializeValues(int[][] map){
        for(int y = 0; y < map.length; y++){
            for(int x= 0; x < map[0].length; x++){
                if(map[y][x] > 0) ClosedSet.add(new Point(x,y));
            }
        }
    }

    public int calcPath(int pX, int pY, int[][] map) {

        ClosedSet               = new ArrayList<Point>();
        OpenSet                 = new ArrayList<Point>();
        int[][] GScore          = new int[map.length][map[0].length];
        int[][] FScore          = new int[map.length][map[0].length];
        Point[][] CameFrom      = new Point[map.length][map[0].length];
        start                   = new Point(this.x,this.y);
        goal                    = new Point(pX,pY); // TODO: set this to where the player is in the map.
        Point current;
        initializeValues(map);

        OpenSet.add(start);
        int currentGScore = 0;
        FScore[start.x][start.y] = getHScore(start, goal);

        while(!OpenSet.isEmpty())
        {
            current = findLowestFScore(OpenSet, FScore);

            if(current.equals(goal)){
                return findDirection(start, reconstructPath(CameFrom, current));
            }
            OpenSet.remove(OpenSet.indexOf(current));       // Removes the current point
            ClosedSet.add(new Point(current));

            Point[] neighbours = {new Point(current.x-1, current.y), new Point(current.x, current.y - 1)
                    , new Point(current.x + 1, current.y), new Point(current.x, current.y+1)};
            for(int i = 0; i < neighbours.length; i++)
            {
                if((neighbours[i].x >= 0 && neighbours[i].x < map[0].length)
                        && (neighbours[i].y >= 0 && neighbours[i].y < map.length )){
                    // This is a + 1 because we know that the distance between current and the neighbour is always one
                    currentGScore = GScore[current.y][current.x] + 1;

                    if(ClosedSet.contains(neighbours[i]) && currentGScore >= GScore[neighbours[i].y][neighbours[i].x]){
                        continue;
                    }
                    if(!OpenSet.contains(neighbours[i]) || currentGScore < GScore[neighbours[i].y][neighbours[i].x]){
                        CameFrom[neighbours[i].y][neighbours[i].x] = current;
                        GScore[neighbours[i].y][neighbours[i].x] = currentGScore;
                        FScore[neighbours[i].y][neighbours[i].x] = getHScore(neighbours[i],goal) + currentGScore;
                        if(!OpenSet.contains(neighbours[i])){
                            OpenSet.add(new Point(neighbours[i]));
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void move(int direction) {
        switch (direction) {
            // Left
            case 1:
                x--;
                return;
            // Up
            case 2:
                y--;
                return;
            // Right
            case 3:
                x++;
                return;
            // Down
            case 4:
                y++;
        }
    }
}
