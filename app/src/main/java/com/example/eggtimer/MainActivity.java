package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seek;
    int t=30;
    int c=0;
    TextView tv;
    CountDownTimer count;
    public void timer(View view){
        Button b=(Button) findViewById(R.id.button);
        tv=(TextView) findViewById(R.id.t1);
        MediaPlayer m=MediaPlayer.create(this,R.raw.alarm);
        seek=(SeekBar) findViewById(R.id.seek);

        if(c==0){
            seek.setEnabled(false);
            count=new CountDownTimer(t*1000+100,1000){

                @Override
                public void onTick(long l) {
                    if(t>0) {
                        t--;
                        tv.setText(time(t));
                        seek.setProgress(t);
                    }
                }

                @Override
                public void onFinish() {
                    m.start();
                    seek.setProgress(30);
                    t=30;
                    b.setText("Start");
                    c=0;
                    seek.setEnabled(true);
                }
            }.start();

            b.setText("Stop");
            c=1;
        }

        else{
            seek.setEnabled(true);
            count.cancel();
            seek.setProgress(30);
            t=30;
            b.setText("Start");
            c=0;
        }

    }

    public String time(int n){
        int m=n/60;
        int s=n-(m*60);
        String sec=s+"";
        String min=m+"";
        if(s<10)
            sec="0"+s;
        if(m<10)
            min="0"+m;
        return min+":"+sec;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.t1);
        seek=(SeekBar) findViewById(R.id.seek);
        seek.setMin(1);
        seek.setMax(600);
        seek.setProgress(30);
        tv.setText(time(30));
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                    t=i;
                    tv.setText(time(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}