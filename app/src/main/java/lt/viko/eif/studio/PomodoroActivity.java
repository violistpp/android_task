package lt.viko.eif.studio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import lt.viko.eif.studio.databinding.ActivityPomodoroBinding;

public class PomodoroActivity extends AppCompatActivity {

//    public TextView timer1, timer2, timer3;
//    public ProgressBar timeProgressBar;
    public Button startPauseButton;
    public Button resetButton;
    private boolean timerIsRunning;
    private CountDownTimer timer;
    private static final long START_TIME_IN_MILLIS = 1500000;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private long hours, minutes, seconds;
    private ActivityPomodoroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pomodoro);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pomodoro);
        binding.setMaxProgress((int) START_TIME_IN_MILLIS);
        binding.setProgress((int) timeLeftInMillis);

        getSupportActionBar().hide();

//        timer1 = findViewById(R.id.timer1);
//        timer2 = findViewById(R.id.timer2);
//        timer3 = findViewById(R.id.timer3);
//        timeProgressBar = findViewById(R.id.timeProgressBar);
        startPauseButton = findViewById(R.id.startPauseButton);
        resetButton = findViewById(R.id.resetButtno);

        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerIsRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();

    }

    public void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millis) {
                timeLeftInMillis = millis;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerIsRunning = false;
                startPauseButton.setText("start");
                startPauseButton.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();

        timerIsRunning = true;
        startPauseButton.setText("pause");
        resetButton.setVisibility(View.INVISIBLE);
    }

    public void pauseTimer() {
        timer.cancel();
        timerIsRunning = false;
        startPauseButton.setText("start");
        resetButton.setVisibility(View.VISIBLE);
    }

    public void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        resetButton.setVisibility(View.INVISIBLE);
        startPauseButton.setVisibility(View.VISIBLE);
    }

    public void updateCountDownText() {
        hours = TimeUnit.MILLISECONDS.toHours(timeLeftInMillis);
        minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeLeftInMillis));
        seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis));

//        timer1.setText(String.format("%02d", hours));
//        timer2.setText(String.format("%02d", minutes));
//        timer3.setText(String.format("%02d", seconds));
        binding.setHour(String.format("%02d", hours));
        binding.setMinute(String.format("%02d", minutes));
        binding.setSecond(String.format("%02d", seconds));
        binding.setProgress((int) timeLeftInMillis);
    }

    public void openDialog() {
        NewAlertDialog dialog = new NewAlertDialog();
        dialog.show(getSupportFragmentManager(), "alert");
    }
}