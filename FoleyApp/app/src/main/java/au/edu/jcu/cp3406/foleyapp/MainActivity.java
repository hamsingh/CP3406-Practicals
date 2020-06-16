package au.edu.jcu.cp3406.foleyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    String[] names = {
            "Airplane",
            "Baby",
            "Car",
            "Cat",
            "Cow",
            "Dog",
            "Frog",
            "Gun",
            "Heart Beat",
            "Motorbike",
            "Toilet",
            "Whistle"
    };
    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomGrid adapter = new CustomGrid(MainActivity.this, names, imageId);
        grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + names [+ position], Toast.LENGTH_SHORT).show();
                Intent startIntent = new Intent(MainActivity.this, ImageViewer.class);
                startIntent.putExtra("key", names[+ position]);
                startActivity(startIntent);

            }
        });
    }
}
