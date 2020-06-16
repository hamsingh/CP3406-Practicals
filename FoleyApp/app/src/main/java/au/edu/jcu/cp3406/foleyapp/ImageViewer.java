package au.edu.jcu.cp3406.foleyapp;

import android.media.AudioManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class ImageViewer extends AppCompatActivity {
    ImageView image;
    int audioCounter;
    au.edu.jcu.cp3406.foleyapp.AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        image = findViewById(R.id.imageView);
        audioManager = new au.edu.jcu.cp3406.foleyapp.AudioManager(this);

        Toast.makeText(ImageViewer.this, "Wait 3 seconds for Audio to Load", Toast.LENGTH_SHORT).show();
        String text = getIntent().getStringExtra("key");
        switch (text) {
            case "Airplane":
                image.setImageResource(R.drawable.image1);
                audioCounter = 0;
                break;
            case "Baby":
                image.setImageResource(R.drawable.image2);
                audioCounter = 1;
                break;
            case "Car":
                image.setImageResource(R.drawable.image3);
                audioCounter = 2;
                break;
            case "Cat":
                image.setImageResource(R.drawable.image4);
                audioCounter = 3;
                break;
            case "Cow":
                image.setImageResource(R.drawable.image5);
                audioCounter = 4;
                break;
            case "Dog":
                image.setImageResource(R.drawable.image6);
                audioCounter = 5;
                break;
            case "Frog":
                image.setImageResource(R.drawable.image7);
                audioCounter = 6;
                break;
            case "Gun":
                image.setImageResource(R.drawable.image8);
                audioCounter = 7;
                break;
            case "Heart Beat":
                image.setImageResource(R.drawable.image9);
                audioCounter = 8;
                break;
            case "Motorbike":
                image.setImageResource(R.drawable.image10);
                audioCounter = 9;
                break;
            case "Toilet":
                image.setImageResource(R.drawable.image11);
                audioCounter = 10;
                break;
            case "Whistle":
                image.setImageResource(R.drawable.image12);
                audioCounter = 11;
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "started";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "moved";
                break;
            case MotionEvent.ACTION_UP:
                action = "ended";
                break;
        }
        Log.i("TouchableActivity", String.format(Locale.getDefault(),
                "%.2f %.2f %s", x, y, action));
        if (audioManager.isLoaded() & (x > 300) & (x < 800) & (y > 700) & (y < 1200)){
            Sound sound = getSound(audioCounter);
            audioManager.playSound(sound);
        } else{
            Toast.makeText(ImageViewer.this, "Try Clicking Somewhere Else", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private Sound getSound(int value) {
        switch (value) {
            case 0:
                return Sound.AIRPLANE;
            case 1:
                return Sound.BABY;
            case 2:
                return Sound.CAR;
            case 3:
                return Sound.CAT;
            case 4:
                return Sound.COW;
            case 5:
                return Sound.DOG;
            case 6:
                return Sound.FROG;
            case 7:
                return Sound.GUN;
            case 8:
                return Sound.HEARTBEAT;
            case 9:
                return Sound.MOTORBIKE;
            case 10:
                return Sound.TOLIET;
            case 11:
                return Sound.WHISTLE;
            default:
                return Sound.AIRPLANE;
        }
    }
}