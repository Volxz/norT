package com.exclnetworks.nort.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Vector2;
import com.exclnetworks.nort.NortGame;

import java.util.*;

public class Player {


    //Static dimensions assume the bike is upright (North Image)

    static final float BIKE_HEIGHT = 40f;
    static final float BIKE_WIDTH = 10f;

    private Color color;

    //Each direction of bike
    private Texture bike_n;
    private Texture bike_e;
    private Texture bike_s;
    private Texture bike_w;

    private float bikeSpeed = 5;

    private boolean alive;
    private boolean online;

    public int id;

    private char direction;
    private char lastDirection;

    private float x;
    private float y;

    private float xSpeed = 0f;
    private float ySpeed = 0f;

    private ShapeRenderer trailRenderer = new ShapeRenderer();

    private List<Line> trails = new ArrayList<Line>();

    public Player(int id) {
        this.id = id;

        this.color = NortGame.MM_CONFIG.PLAYER_COLORS[id];

        //Configure bike textures
        this.bike_n = new Texture("bike_" + NortGame.MM_CONFIG.TEAMNAMES[id] + "_n.png");
        this.bike_e = new Texture("bike_" + NortGame.MM_CONFIG.TEAMNAMES[id] + "_e.png");
        this.bike_s = new Texture("bike_" + NortGame.MM_CONFIG.TEAMNAMES[id] + "_s.png");
        this.bike_w = new Texture("bike_" + NortGame.MM_CONFIG.TEAMNAMES[id] + "_w.png");

        this.direction = NortGame.MM_CONFIG.STARTING_DIRECTIONS[id];
        this.lastDirection = direction;
        this.x = NortGame.MM_CONFIG.STARTING_POINTS[id].x;
        this.y = NortGame.MM_CONFIG.STARTING_POINTS[id].y;


    }

    public void draw(SpriteBatch sb) {
        //System.out.println(this.x);
        //System.out.println(this.y);
        //System.out.println(this.direction);
        drawTrail();
        sb.begin();
        //Draw the appropriate bike based on direction
        switch (direction) {
            case 'N':
                sb.draw(bike_n, x, y, BIKE_WIDTH, BIKE_HEIGHT);
                break;
            case 'E':
                sb.draw(bike_e, x, y, BIKE_HEIGHT, BIKE_WIDTH); //Flip Width and height because the bike is sideways
                break;
            case 'S':
                sb.draw(bike_s, x, y, BIKE_WIDTH, BIKE_HEIGHT);
                break;
            case 'W':
                sb.draw(bike_w, x, y, BIKE_HEIGHT, BIKE_WIDTH); //Flip Width and height because the bike is sideways
                break;
        }
        sb.end();

    }

    public void update() {
        updateTrail();
        switch (direction) {
            case 'N':
                this.ySpeed = bikeSpeed;
                this.xSpeed = 0;
                break;
            case 'E':
                this.ySpeed = 0;
                this.xSpeed = bikeSpeed;
                break;
            case 'S':
                this.ySpeed = -bikeSpeed;
                this.xSpeed = 0;
                break;
            case 'W':
                this.ySpeed = 0;
                this.xSpeed = -bikeSpeed;
                break;
        }

        this.x += xSpeed;
        this.y += ySpeed;
    }

    public void start() {
        switch (direction) {
            case 'N':
                this.ySpeed = bikeSpeed;
                break;
            case 'E':
                this.xSpeed = bikeSpeed;
                break;
            case 'S':
                this.ySpeed = -bikeSpeed;
                break;
            case 'W':
                this.xSpeed = -bikeSpeed;

        }
    }

    private void drawTrail() {

        trailRenderer.begin(ShapeRenderer.ShapeType.Line);
        trailRenderer.setColor(color);
        for (Line l : trails) {
            trailRenderer.line(l.x1, l.y1, l.x2, l.y2);
        }
        trailRenderer.end();
    }

    private void updateTrail() {

        if (trails.size() == 0) { //Create the first line if we need one
            trails.add(new Line(x + (BIKE_WIDTH / 2), y, x + (BIKE_WIDTH / 2), y));
        }

        Line mrTrail = trails.get(trails.size() - 1); // Get the most recent trail

        switch (direction) {
            case 'N':
                if (mrTrail.x2 == this.x + (BIKE_WIDTH / 2) && lastDirection == 'N') //Are we continuing a line?
                    mrTrail.y2 = this.y;
                else { //If not make a new one and connect the old one haf way
                    mrTrail.x2 = this.x + (BIKE_WIDTH / 2);
                    trails.add(new Line(mrTrail.x2, mrTrail.y2, mrTrail.x2, mrTrail.y2));
                }
                lastDirection = 'N';
                break;
            case 'E':
                if (mrTrail.y2 == this.y + (BIKE_WIDTH / 2) && lastDirection == 'E') //Are we continuing a line?
                    mrTrail.x2 = this.x;
                else { //If not make a new one and connect the old one haf way
                    mrTrail.y2 = this.y + (BIKE_WIDTH / 2);
                    trails.add(new Line(mrTrail.x2, mrTrail.y2, mrTrail.x2, mrTrail.y2));
                }
                lastDirection = 'E';
                break;
            case 'S':
                if (mrTrail.x2 == this.x + (BIKE_WIDTH / 2) && lastDirection == 'S') //Are we continuing a line?
                    mrTrail.y2 = this.y;
                else { //If not make a new one and connect the old one haf way
                    mrTrail.x2 = this.x + (BIKE_WIDTH / 2);
                    trails.add(new Line(mrTrail.x2, mrTrail.y2, mrTrail.x2, mrTrail.y2));
                }
                lastDirection = 'S';
                break;
            case 'W':
                if (mrTrail.y2 == this.y + (BIKE_WIDTH / 2) && lastDirection == 'W') //Are we continuing a line?
                    mrTrail.x2 = this.x;
                else { //If not make a new one and connect the old one haf way
                    mrTrail.y2 = this.y + (BIKE_WIDTH / 2);
                    trails.add(new Line(mrTrail.x2, mrTrail.y2, mrTrail.x2, mrTrail.y2));
                }
                lastDirection = 'W';
                break;
        }
    }

    public void setDirection(char d) {
        this.direction = d;
    }

    public void forceMove(Vector2 c, char direction){
        this.x = c.x;
        this.y = c.y;
        this.direction = direction;
    }

    public void kill(){
        this.alive = false;
        this.trails.clear();
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public void dispose() {
        trailRenderer.dispose();
        bike_n.dispose();
        bike_e.dispose();
        bike_s.dispose();
        bike_w.dispose();
    }

    public boolean isOnline(){
        return this.online;
    }

    public void setOnline(boolean b){
        this.online = b;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public char getDirection(){
        return this.direction;
    }
    class Line {
        float x1;
        float y1;
        float x2;
        float y2;

        Line(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }




}
