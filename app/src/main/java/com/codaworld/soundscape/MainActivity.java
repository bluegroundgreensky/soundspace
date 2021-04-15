package com.codaworld.soundscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.Build.VERSION_CODES.P;

public class MainActivity extends AppCompatActivity {
     MediaPlayer p;
     AudioManager am;
     public  void play(View v)
     {
         p.start();
     }
    public  void pause(View v)

    {
        p.pause();
    }
    public  void stop(View v)
    {
        p.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p=MediaPlayer.create(this,R.raw.musiii);
        SeekBar seekvol=findViewById(R.id.seekBar2);
        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int cur=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekvol.setProgress(cur);
        seekvol.setMax(max);
       seekvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }

       });
       final SeekBar seev=findViewById(R.id.seekBar);

        seev.setMax(p.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seev.setProgress(p.getCurrentPosition());
            }
        },0,900);
        seev.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    p.seekTo(progress);

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