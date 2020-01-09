package lt.viko.eif.studio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PomodoroActivity extends AppCompatActivity {

    public TextView timerText;
    public ProgressBar timeProgressBar;
    public Button startButton;
    public Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        timerText = findViewById(R.id.timerText);
        timeProgressBar = findViewById(R.id.timeProgressBar);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButtno);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(60000, 1000) {

                    @Override
                    public void onTick(long l) {
                        timerText.setText("" + l/1000);
                        timeProgressBar.setProgress((1/1000));
                    }

                    @Override
                    public void onFinish() {
                        timerText.setText("--:--:--");
                    }
                }.start();
            }
        });

    }
}
