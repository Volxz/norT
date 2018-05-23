package com.exclnetworks.nort.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.exclnetworks.nort.NortGame;
import com.exclnetworks.nort.entities.Player;
import com.exclnetworks.nort.matchmaking.MatchMaker;

public class PlayState extends State {

    private ShapeRenderer shapeRenderer;
    private MatchMaker matchMaker;
    private Player localPlayer;

    public PlayState(GameStateManager gsm, MatchMaker mm) {
        super(gsm);
        this.matchMaker = mm;
        this.shapeRenderer = new ShapeRenderer();
        int i = 0;
        for(Player p : matchMaker.players){
            i++;
            p.start();
        }
        System.out.println(i);
        //Rotate so local player is on the bottom
        this.rotate(90 * matchMaker.getLocalPlayer().id);
        this.localPlayer = matchMaker.getLocalPlayer();
    }

    @Override
    protected void handleInput() {
        char lastDirection = localPlayer.getDirection();
        if(Gdx.input.isKeyPressed(Input.Keys.W) && lastDirection !='N' && lastDirection !='S') {
            matchMaker.getLocalPlayer().setDirection('N');
            matchMaker.moveLocalPlayer(new Vector2(localPlayer.getX(),localPlayer.getY()), 'N');
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && localPlayer.getDirection() !='W' && localPlayer.getDirection() !='E') {
            matchMaker.getLocalPlayer().setDirection('W');
            matchMaker.moveLocalPlayer(new Vector2(localPlayer.getX(),localPlayer.getY()), 'W');

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S) && lastDirection !='S' && lastDirection !='N') {
            matchMaker.getLocalPlayer().setDirection('S');
            matchMaker.moveLocalPlayer(new Vector2(localPlayer.getX(),localPlayer.getY()), 'S');
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && localPlayer.getDirection() !='E' && localPlayer.getDirection() !='W') {
            matchMaker.getLocalPlayer().setDirection('E');
            matchMaker.moveLocalPlayer(new Vector2(localPlayer.getX(),localPlayer.getY()), 'E');
        }



    }

    @Override
    public void update(float dt) {
        handleInput();
        for(Player p: matchMaker.players){
            p.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.end();
        drawGrid();

        for(Player p : matchMaker.players){
            p.draw(sb);
        }

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.055f, 0.59f, 0.59f, 2); // Light blue line
        //Draw vertical lines ( top to bottom )
        for(int i = 0; i < NortGame.WIDTH; i += NortGame.WIDTH / NortGame.BOXSIZE){
            shapeRenderer.line(i, 0, i, NortGame.HEIGHT);
        }
        //Draw vertical lines ( left to right)
        for(int i = 0; i < NortGame.HEIGHT; i += NortGame.WIDTH / NortGame.BOXSIZE){
            shapeRenderer.line(0, i, NortGame.WIDTH, i);
        }
        shapeRenderer.end();
    }


}
