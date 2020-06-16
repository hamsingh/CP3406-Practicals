package au.edu.jcu.cp3406.guesstheceleb.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class CelebrityManager
{
    private String assetPath;
    private String[] imageNames;
    private AssetManager assetManager;

    public CelebrityManager(AssetManager assetManager, String assetPath)
    {
        this.assetManager = assetManager;
        this.assetPath = assetPath;

        try
        {
            imageNames = this.assetManager.list(assetPath);
        }
        catch (IOException e)
        {
            System.out.println("Not a valid asset path or path is empty");
        }

    }

    public Bitmap get(int i)
    {
        try
        {
            InputStream stream = assetManager.open(assetPath + imageNames[i]);
            return BitmapFactory.decodeStream(stream);
        }
        catch (IOException e)
        {
            System.out.println("The file does not exist");
            return null;
        }
    }

    public String getName(int i)
    {
        String str = imageNames[i];

        return str.substring(0, str.lastIndexOf('.'));
    }

    public int count()
    {
        return imageNames.length;
    }
}
