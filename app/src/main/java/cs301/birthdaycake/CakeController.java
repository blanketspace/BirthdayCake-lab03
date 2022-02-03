package cs301.birthdaycake;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private CakeView cakeView;
    private CakeModel cakeModel;


public CakeController(CakeView cv) {
    cakeView = cv;
    cakeModel = cakeView.getCM();
}



    @Override
    public void onClick(View view) {
        this.cakeModel.lit = false;
        cakeView.invalidate();
        //TODO: make text on button switch so we can relight the candles
    }

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
}