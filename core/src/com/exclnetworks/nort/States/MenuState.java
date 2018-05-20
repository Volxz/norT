package com.exclnetworks.nort.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.exclnetworks.nort.NortGame;

public class MenuState extends State {

    BitmapFont playText;
    private Texture background;

    public MenuState(GameStateManager gsm){
        super(gsm);
        playText =  new BitmapFont();
        background = new Texture("menu-background.jpg");
    }

    @Override
    public void handleInput(){

    }

    @Override
    public void update(float dt){

    }

    @Override
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(background, 0, 0, NortGame.WIDTH, NortGame.HEIGHT);
        playText.draw(sb, "Play Game", 400, 400);
        sb.end();
    }

}
