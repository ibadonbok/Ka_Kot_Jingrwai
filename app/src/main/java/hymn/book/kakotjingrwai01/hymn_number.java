package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityHymnNumberBinding;
import hymn.book.kakotjingrwai01.databinding.ActivityLoginBinding;

public class hymn_number extends AppCompatActivity {
    private static final String TAG = "hymn_number";

    private static final String number = "number";
    private static final String title = "title";
    private static final String lyric = "lyric";
    private static final String author = "author";

    SearchView mysearchview;
    ListView mylist;

    /*public static ArrayList<search> shapeList = new ArrayList<search>();

    private ListView listView;

    private String selectedFilter = "all";
    private String currentSearchText = "";*/


    ActivityHymnNumberBinding hymnNumberBinding;

    ArrayList<Integer> list = new ArrayList<>();
    ArrayAdapter<Integer> adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymn_number);

        hymnNumberBinding = ActivityHymnNumberBinding.inflate(getLayoutInflater());
        setContentView(hymnNumberBinding.getRoot());

        //hymnNumberBinding.numberSearch.setOnSearchClickListener();

        /*initSearchWidgets();
        setUpOnclickListener();*/

        mylist = findViewById(R.id.number_list);
        for (int i = 1; i <= 639; i++) {
            list.add(i);
        }

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, list);
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

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int num = Integer.parseInt(mylist.getItemAtPosition(i).toString());

                Intent intent = new Intent(hymn_number.this, hymn_view.class);
                //   Toast.makeText(hymn_number.this, ""+text, Toast.LENGTH_SHORT).show();
                intent.putExtra("number", num);
                startActivity(intent);
            }
        });
    }

    /*private void initSearchWidgets()
    {
        mysearchview = (SearchView) findViewById(R.id.number_search);

        mysearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
                ArrayList<search> filteredShapes = new ArrayList<search>();

                for(search shape: shapeList)
                {
                    if(search.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilter.equals("all"))
                        {
                            filteredShapes.add(shape);
                        }
                        else
                        {
                            if(search.getName().toLowerCase().contains(selectedFilter))
                            {
                                filteredShapes.add(shape);
                            }
                        }
                    }
                }
                searchadapter adapter = new searchadapter(getApplicationContext(), 0, filteredShapes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                search selectShape = (search) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), hymn_view.class);
                showDetail.putExtra("id",selectShape.getId());
                startActivity(showDetail);
            }
        });

    }*/
}