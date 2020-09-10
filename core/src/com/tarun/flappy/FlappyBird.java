package com.tarun.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	//ShapeRenderer shapeRenderer;

	int flap = 0;
	Texture[] birds;
	Circle birdcircle;

	float birdY = 0;
	float velocity = 0;
	float gravity = 7;
	BitmapFont font;
	Texture gameover;

	int gamestate = 0;
	float gap = 400;
	float maxtube;
	float tubevelocity = 20;
	int score = 0;
	int scoringtube = 0;

	float distancetubes;
	int numberoftubes = 4;

	float[] tubeX = new float[numberoftubes];
	float[] tubeoffset = new float[numberoftubes];

	Rectangle[] toptuberect;
	Rectangle[] bottomtuberect;

	Random randomgenerator;

	Texture toptube;
	Texture bottomtube;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
        birdcircle = new Circle();
        //shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);

		background = new Texture("bg.png");

		birds = new Texture[2];

		gameover = new Texture("gameover.png");

		toptuberect = new Rectangle[numberoftubes];
		bottomtuberect= new Rectangle[numberoftubes];

		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");



		toptube = new Texture("toptube.png");
		bottomtube = new Texture("bottomtube.png");

		maxtube = Gdx.graphics.getHeight() / 2f - gap / 2 - 100;
		randomgenerator = new Random();

		distancetubes = Gdx.graphics.getWidth() * 3 / 4f;

		startgame();

	}

	public void startgame() {

		birdY = Gdx.graphics.getHeight() / 2f - birds[0].getHeight() / 2f;

		for(int i = 0; i<numberoftubes; i++) {

			tubeoffset[i] = ( randomgenerator.nextFloat() - 0.5f ) * (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = Gdx.graphics.getWidth() / 2f - toptube.getWidth() / 2f + Gdx.graphics.getWidth() + i * distancetubes;

			toptuberect[i] = new Rectangle();
			bottomtuberect[i] = new Rectangle();
		}

	}

	@Override
	public void render () {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gamestate == 1) {

        	if( tubeX[scoringtube] < Gdx.graphics.getWidth() / 2f) {

        		score++;

        		Gdx.app.log("Score" , String.valueOf(score));

        		if(scoringtube < numberoftubes - 1) {

        			scoringtube++;

				} else {

        			scoringtube = 0;

				}

			}

			if( Gdx.input.justTouched()) {

				velocity = -45;

			}

			for(int i = 0; i<numberoftubes; i++) {

				if( tubeX[i] < - toptube.getWidth()) {

					tubeX[i] += numberoftubes * distancetubes;
                    tubeoffset[i] = ( randomgenerator.nextFloat() - 0.5f ) * (Gdx.graphics.getHeight() - gap - 200);


                } else {

					tubeX[i] = tubeX[i] - tubevelocity;

				}

				batch.draw(toptube, tubeX[i], Gdx.graphics.getHeight() / 2f + gap / 2 + tubeoffset[i]);
				batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2f - gap / 2 - bottomtube.getHeight() + tubeoffset[i]);

				toptuberect[i] = new Rectangle(tubeX[i] , Gdx.graphics.getHeight() / 2f + gap / 2 + tubeoffset[i] , toptube.getWidth() , toptube.getHeight());
				bottomtuberect[i] = new Rectangle(tubeX[i] , Gdx.graphics.getHeight() / 2f - gap / 2 - bottomtube.getHeight() + tubeoffset[i] , bottomtube.getWidth() , bottomtube.getHeight());



			}
			if( birdY > 0 ) {

				velocity += gravity;
				birdY -= velocity;

			} else {

				gamestate = 2;

			}
		} else if(gamestate == 0) {

			if( Gdx.input.justTouched()) {

				gamestate = 1;

			}

		} else if( gamestate == 2) {

        	batch.draw(gameover , Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2 , Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2);

			if( Gdx.input.justTouched()) {

				gamestate = 1;
				startgame();
				score = 0;
				scoringtube = 0;
				velocity = 0;

			}
		}

		if (flap == 0) {

			flap = 1;
			try {

				Thread.sleep(80);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		} else {

			flap = 0;
			try {

				Thread.sleep(80);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}
		}

		font.draw(batch , String.valueOf(score) , Gdx.graphics.getWidth() / 2f , 2000);

		batch.draw(birds[flap], Gdx.graphics.getWidth() / 2f - birds[flap].getWidth() / 2f, birdY);
		batch.end();

        birdcircle.set(Gdx.graphics.getWidth() / 2f , birdY + birds[flap].getHeight() / 2f , birds[flap].getWidth() / 2f);

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.circle(birdcircle.x , birdcircle.y , birdcircle.radius);

		for(int i=0; i<numberoftubes; i++) {

//			shapeRenderer.rect(tubeX[i] , Gdx.graphics.getHeight() / 2f + gap / 2 + tubeoffset[i] , toptube.getWidth() , toptube.getHeight());
//			shapeRenderer.rect(tubeX[i] , Gdx.graphics.getHeight() / 2f - gap / 2 - bottomtube.getHeight() + tubeoffset[i] , bottomtube.getWidth() , bottomtube.getHeight());

			if(Intersector.overlaps(birdcircle , toptuberect[i]) || (Intersector.overlaps(birdcircle , bottomtuberect[i]))) {

				gamestate = 2;

			}

		}

        //shapeRenderer.end();
	}
	
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
