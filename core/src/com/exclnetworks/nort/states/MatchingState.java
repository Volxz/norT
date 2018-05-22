package com.exclnetworks.nort.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.exclnetworks.nort.NortGame;

public class MatchingState extends State {

    private Texture background;
    private FreeTypeFontGenerator generator;

    private BitmapFont mmText;

    public MatchingState(GameStateManager gsm){
        super(gsm);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.PINK;
        mmText = generator.generateFont(parameter);

        background = new Texture("menu-background.jpg");
    }

    @Override
    public void handleInput(){

    }

    @Override
    public void update(float dt){
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(background, 0, 0, NortGame.WIDTH, NortGame.HEIGHT);
        mmText.draw(sb, "Finding A Match...", 130, NortGame.HEIGHT - 50); //poor attempt at centering
        sb.end();
    }

    @Override
    public void dispose(){
        mmText.dispose();
        generator.dispose();
        background.dispose();
    }

}
