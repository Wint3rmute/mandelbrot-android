package io.github.wint3rmute.mandelbrot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int scale = 270;
    int moveX = 0;
    int moveY = 0;
    SurfaceView surface;

    Paint black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        black = new Paint();
        black.setStyle(Paint.Style.FILL);
        black.setColor(Color.BLACK);

        surface = (SurfaceView) findViewById(R.id.canvas);
        surface.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                int centerX = canvas.getClipBounds().centerX();
                int centerY = canvas.getClipBounds().centerY();

                Log.e("centerX", String.valueOf(centerX));
                Log.e("centerY", String.valueOf(centerY));

                canvas.drawPoint(centerX, centerY, black);
                canvas.drawPoint(centerX+1, centerY, black);
                canvas.drawPoint(centerX+1, centerY+1, black);
                canvas.drawPoint(centerX, centerY+1, black);


                for(double i = 0; i < canvas.getHeight(); i++)
                {
                    for (double j = 0; j < canvas.getWidth(); j++)
                    {
                        Complex n = new Complex( (j-centerX) / scale, (i-centerY) / scale);
                        if(Mandelbrot.isPartOf(n,50) == 1) {
                            canvas.drawPoint((int) j, (int) i, black);
                        }
                    }
                }

                holder.unlockCanvasAndPost(canvas);
                Log.e("canvas", "finished");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }

    public void moveR(View view)
    {
        moveX+=100;
        draw();
    }
    public void moveL(View view)
    {
        moveX-=100;
        draw();
    }
    public void moveD(View view)
    {
        moveY+=100;
        draw();
    }
    public void moveU(View view)
    {
        moveY-=100;
        draw();
    }

    public void scaleU(View view)
    {
        scale*=2;
        draw();
    }

    public void scaleD(View view)
    {
        scale-=2;
        draw();
    }

    public void draw()
    {
        // Do some drawing when surface is ready
        SurfaceHolder holder = surface.getHolder();
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        int centerX = canvas.getClipBounds().centerX();
        int centerY = canvas.getClipBounds().centerY();

        Log.e("centerX", String.valueOf(centerX));
        Log.e("centerY", String.valueOf(centerY));

        canvas.drawPoint(centerX, centerY, black);
        canvas.drawPoint(centerX+1, centerY, black);
        canvas.drawPoint(centerX+1, centerY+1, black);
        canvas.drawPoint(centerX, centerY+1, black);


        for(double i = 0; i < canvas.getHeight(); i++)
        {
            for (double j = 0; j < canvas.getWidth(); j++)
            {
                Complex n = new Complex( (j-centerX+moveX) / scale, (i-centerY+moveY) / scale);
                if(Mandelbrot.isPartOf(n,10) == 1) {
                    canvas.drawPoint((int) j, (int) i, black);
                }
            }
        }

        holder.unlockCanvasAndPost(canvas);
        Log.e("canvas", "finished");


    }

}
