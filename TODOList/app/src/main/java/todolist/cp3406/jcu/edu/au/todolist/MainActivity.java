package todolist.cp3406.jcu.edu.au.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter adapter;
    SharedPreferences dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, R.layout.item);
        ListView todoList = findViewById(R.id.listview);
        todoList.setAdapter(adapter);

        adapter.addAll("but milk", "wash car", "call mum");

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemView = (TextView) view;
                adapter.remove(itemView.getText().toString());
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.clear();
        Set<String> newItems = dataSource.getStringSet("items", new HashSet<String>());
        assert newItems != null;
        adapter.addAll(newItems);
    }
}
