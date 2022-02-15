package cs301.birthdaycake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;

public class CheckerboardController implements View.OnTouchListener{
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        CakeView real_view = (CakeView) v;

        Paint red_paint = new Paint();
        Paint green_paint = new Paint();

        red_paint.setARGB(255,255,0,0);
        green_paint.setARGB(255,0,255,0);

        // Draw the checkerboard
        real_view.setCheckerboard(x,y);
        real_view.invalidate();

        return true;
    }
}
