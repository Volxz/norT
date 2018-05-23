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
import com.exclnetworks.nort.matchmaking.MatchMaker;

public class MatchingState extends State {

    private Texture background;
    private FreeTypeFontGenerator generator;

    private BitmapFont mmText;
    private BitmapFont scoreText;

    private ShapeRenderer shapeRenderer;

    private MatchMaker matchMaker;

    private Texture blueBike;
    private Texture greenBike;
    private Texture yellowBike;
    private Texture redBike;
    private Texture checkMark;

    public MatchingState(GameStateManager gsm) {
        super(gsm);
        //Load in our bike images
        this.blueBike = new Texture("bike_blue_e.png");
        this.greenBike = new Texture("bike_green_e.png");
        this.yellowBike = new Texture("bike_yellow_e.png");
        this.redBike = new Texture("bike_red_e.png");
        this.checkMark = new Texture("check.png");

        //Make a shape renderer
        shapeRenderer = new ShapeRenderer();

        //Generate Large Finding a Match text
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontParameter matchMakingText = new FreeTypeFontParameter();
        matchMakingText.size = 50;
        matchMakingText.color = Color.PINK;
        mmText = generator.generateFont(matchMakingText);

        //Generate playerboard text
        FreeTypeFontParameter scoreboardText = new FreeTypeFontParameter();
        scoreboardText.size = 15;
        scoreboardText.color = Color.PURPLE;
        scoreText = generator.generateFont(scoreboardText);

        //Configure shape renderer for button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.setAutoShapeType(true);

        background = new Texture("menu-background.jpg");

        //Connect and find a match
        matchMaker = new MatchMaker();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm, matchMaker));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Draw Background
        sb.begin();
        sb.draw(background, 0, 0, NortGame.WIDTH, NortGame.HEIGHT);
        sb.end();
        //

        //Draw scoreboard panel
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.rect(0, 200, 200, 400);
        shapeRenderer.end();

        //Draw text
        sb.begin();
        mmText.draw(sb, "Finding A Match...", 130, NortGame.HEIGHT - 50); //poor attempt at centering
        scoreText.draw(sb, "Players In Lobby", 10, 580);
        //

        //Draw bikes to rep players in the lobby
        //Bike one
        sb.draw(blueBike, 15, 500, 100, 30);
        if (matchMaker.players[0].isOnline())
            sb.draw(checkMark, 140, 500, 30, 30);
        //Bike two
        sb.draw(greenBike, 15, 450, 100, 30);
        if (matchMaker.players[1].isOnline())
            sb.draw(checkMark, 140, 450, 30, 30);
        //Bike three
        sb.draw(yellowBike, 15, 400, 100, 30);
        if (matchMaker.players[2].isOnline())
            sb.draw(checkMark, 140, 400, 30, 30);
        //Bike four
        sb.draw(redBike, 15, 350, 100, 30);
        if (matchMaker.players[3].isOnline())
            sb.draw(checkMark, 140, 350, 30, 30);

        sb.end();

    }

    @Override
    public void dispose() {
        mmText.dispose();
        scoreText.dispose();
        generator.dispose();
        background.dispose();
        shapeRenderer.dispose();
    }

}
