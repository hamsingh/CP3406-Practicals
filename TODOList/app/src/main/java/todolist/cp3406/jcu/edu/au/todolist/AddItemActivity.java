package todolist.cp3406.jcu.edu.au.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {

    SharedPreferences dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        dataSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:


                Set<String> items = dataSource.getStringSet("items", new HashSet<String>());
                assert items != null;

                dataSource.edit().clear().putStringSet("items", items).apply();
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
