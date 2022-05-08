package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class khorus_number extends AppCompatActivity {

    SearchView mysearchview;
    ListView mylist;
    ArrayList<Integer> list=new ArrayList<>();
    ArrayAdapter<Integer> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khorus_number);

        mysearchview=findViewById(R.id.khorus_search);
        mylist=findViewById(R.id.khorus_list);
        for(int i=1;i<=38;i++)
        {
            list.add(i);
        }
        adapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(adapter);

        mysearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                //adapter.getFilter().filter(s);
                return false;
            }
        });

    }
}