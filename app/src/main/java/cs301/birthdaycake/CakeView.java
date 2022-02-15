package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {
    private CakeModel cakeModel;

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint balloonPaint = new Paint();



    Paint red = new Paint();


    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400f; //400.0f
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 100.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cakeModel = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFF3C52FF);  //blueee
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        balloonPaint.setColor(0xFF4287f5);
        balloonPaint.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.WHITE);  //better than black default

    }

    public void drawBalloon(Canvas canvas) {
        canvas.drawOval(cakeModel.balloonX - 50, cakeModel.balloonY + 60, cakeModel.balloonX + 50,
                cakeModel.balloonY - 60, balloonPaint);
        canvas.drawLine(cakeModel.balloonX, cakeModel.balloonY + 60,cakeModel.balloonX + 30,
                cakeModel.balloonY + 200, wickPaint);
        //top/bottom are y, left/right are x

    }

    public CakeModel getCM() {
        return cakeModel;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {

            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
            if (cakeModel.lit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);


        }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now two candles
        if(cakeModel.hasCandles) {
            if(cakeModel.numCandles!=0) {
                for (int i = 1; i <= cakeModel.numCandles; i++)
                    drawCandle(canvas, cakeLeft + i * cakeWidth / (cakeModel.numCandles + 1) - candleWidth / 2, cakeTop);
                //drawCandle(canvas, cakeLeft + 2 * cakeWidth/3 - candleWidth/2, cakeTop);
            }
            //divide cakeWidth by numCandles + 1, multiply cakeWidth by i, start i
        }

        String coords = "" + cakeModel.x + ", " + cakeModel.y;
        red.setTextSize(70.0f);
        canvas.drawText(coords, 1700.0f, 1100.0f, red);


        if (cakeModel.balloon) {
            this.drawBalloon(canvas);
        }
    }//onDraw

    public void drawText(String string, float x,float y, Paint paint){

    }

}//class CakeView

