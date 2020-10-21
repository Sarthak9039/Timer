package com.sarthak.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar settimer;
Button btn;
     TextView timetext;
     Boolean counterisactive=false;
     CountDownTimer countDownTimer;
     TextView t;
     public void resettimer()
     {
         timetext.setText("0:30");
         settimer.setProgress(30);
         countDownTimer.cancel();
         btn.setText("GO!");
         settimer.setEnabled(true);
         counterisactive=false;

     }
    public void updatetimer(int secondleft)
    {


        int minutes=(int)secondleft/60;
        int second=secondleft-minutes*60;
        String second_string=Integer.toString(second);
        if(second<9)
        {
            second_string="0"+second_string;
        }

        timetext.setText(Integer.toString(minutes)+":"+second_string);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      settimer =(SeekBar)findViewById(R.id.seekBar);
         timetext=(TextView)findViewById(R.id.textView);
      t=findViewById(R.id.hello);
         btn=(Button)findViewById(R.id.button2);
        settimer.setMax(600);

        settimer.setProgress(30);
        settimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                updatetimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       /* public void timerstart(View view)
        {

        }*/
    }
    public void timerstar(View view) {
        if (counterisactive == false)
        {
            counterisactive = true;
            settimer.setEnabled(false);
            btn.setText("Stop");
             countDownTimer= new CountDownTimer(settimer.getProgress() * 1000 , 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updatetimer((int) millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    timetext.setText("00:00");
                    //Log.i("sdd", "onFinish: ");

                    resettimer();

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                }
            }.start();
         }
         else
        {
resettimer();

        }

    }
public void show()
{
    t.setVisibility(View.VISIBLE);
}
public void hide()
{
    t.setVisibility(View.INVISIBLE);
}
}

