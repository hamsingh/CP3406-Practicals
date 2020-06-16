package au.edu.jcu.cp3406.foleyapp;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private Map<Sound, Integer> soundIds;
    private SoundPool soundPool;
    private int loadId;
    private boolean loadedOkay = false;

    AudioManager(Context context) {
        soundIds = new HashMap<>();
        soundPool = new SoundPool(10, android.media.AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loadedOkay = status == 0;
                Sound sound = Sound.values()[loadId++];
                Log.i("AudioManager", "loaded sound: " + sound);
                soundIds.put(sound, sampleId);
            }
        });

        soundPool.load(context, R.raw.airplane, 0);
        soundPool.load(context, R.raw.baby, 0);
        soundPool.load(context, R.raw.car, 0);
        soundPool.load(context, R.raw.cat, 0);
        soundPool.load(context, R.raw.cow, 0);
        soundPool.load(context, R.raw.dog, 0);
        soundPool.load(context, R.raw.frog, 0);
        soundPool.load(context, R.raw.gun, 0);
        soundPool.load(context, R.raw.heartbeat, 0);
        soundPool.load(context, R.raw.motorbike, 0);
        soundPool.load(context, R.raw.toilet, 0);
        soundPool.load(context, R.raw.whistle, 0);
    }

    Boolean isLoaded(){
        return loadedOkay;
    }
    void playSound(Sound sound) {
        Integer id = soundIds.get(sound);
        assert id != null;
        soundPool.play(id, 1, 1, 1, 0, 1);
    }
}
