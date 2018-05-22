package com.exclnetworks.nort.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.exclnetworks.nort.NortGame;


public class MenuState extends State {

    private BitmapFont playText;
    private Texture background;
    private FreeTypeFontGenerator generator;
    private ShapeRenderer shapeRenderer;
    public MenuState(GameStateManager gsm){
        super(gsm);
        //Init our objects
        background = new Texture("menu-background.jpg");
        shapeRenderer = new ShapeRenderer();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));

        //Configure shape renderer for button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.setAutoShapeType(true);

        //Make our play text font
        FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.PINK;
        playText = generator.generateFont(parameter);

    }

    @Override
    public void handleInput(){
        if(Gdx.input.justTouched()){
            gsm.set(new MatchingState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt){
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb){
        //Sprite batch for text
        sb.begin();
        sb.draw(background, 0, 0, NortGame.WIDTH, NortGame.HEIGHT);
        sb.end();
        //Shape renderer for button shape
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.rect(200,150,400, 100);
        shapeRenderer.end();
        sb.begin();
        playText.draw(sb, "Click To Start Matchmaking", 210, 210);
        sb.end();
    }

    @Override
    public void dispose(){
        playText.dispose();
        background.dispose();
        generator.dispose();
        shapeRenderer.dispose();
    }

}
