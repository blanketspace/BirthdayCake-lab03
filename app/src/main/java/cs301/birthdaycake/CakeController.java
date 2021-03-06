package cs301.birthdaycake;

import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {

    private CakeView cakeView;
    private CakeModel cakeModel;


    public CakeController(CakeView cv) {
        cakeView = cv;
        cakeModel = cakeView.getCM();
    }



    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.blowOut) {
            this.cakeModel.lit = false;
            //TODO: make text on button switch so we can relight the candles
        }
        else if(view.getId() == R.id.goodbye) {
            //TODO: close the program?
        }

        cakeView.invalidate();
    }// onClick

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        this.cakeModel.hasCandles = b;
        this.cakeView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        this.cakeModel.numCandles = i;
        cakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.cakeModel.x = motionEvent.getX();
        this.cakeModel.y = motionEvent.getY();
        this.cakeModel.balloonX = (int) motionEvent.getX();
        this.cakeModel.balloonY = (int) motionEvent.getY();
        this.cakeModel.balloon = true;

        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        CakeView real_view = (CakeView) view;

        Paint red_paint = new Paint();
        Paint green_paint = new Paint();

        red_paint.setARGB(255,255,0,0);
        green_paint.setARGB(255,0,255,0);

        // Draw the checkerboard
        real_view.setCheckerboard(x,y);

        cakeView.invalidate();
        return true;
    }
}