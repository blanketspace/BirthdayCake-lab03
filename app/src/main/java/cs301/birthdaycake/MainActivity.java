package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        CakeView theCake = findViewById(R.id.cakeView);
        theCake.setOnTouchListener(new CheckerboardController());
        CakeController controller = new CakeController(theCake);
        Button b = findViewById(R.id.blowOut);
        b.setOnClickListener(controller);
        SeekBar s = findViewById(R.id.candlesSeekBar);
        s.setOnSeekBarChangeListener(controller);
        theCake.setOnTouchListener(controller);
    }

    public void goodbye(View button) {
        Log.i("Button", "Goodbye");
    }


    /**public void switchButtonText(View button) {
        Button b = findViewById(R.id.blowOut);
        b.setText("light");
    } */
}
