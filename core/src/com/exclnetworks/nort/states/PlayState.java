package com.exclnetworks.nort.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.exclnetworks.nort.NortGame;
import com.exclnetworks.nort.entities.Player;

public class PlayState extends State {

    private ShapeRenderer shapeRenderer;
    private Player p;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        p = new Player(3);
        p.start();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            p.setDirection('N');
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            p.setDirection('W');
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            p.setDirection('S');
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            p.setDirection('E');
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.end();
        drawGrid();
        p.draw(sb);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        p.dispose();
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
