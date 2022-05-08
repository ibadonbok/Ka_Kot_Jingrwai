package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import hymn.book.kakotjingrwai01.databinding.ActivityNumberlistviewBinding;

public class numberlistview extends AppCompatActivity {
    TextView number,title,lyrics,author;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityNumberlistviewBinding numberlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberlistview);

        numberlist=ActivityNumberlistviewBinding.inflate(getLayoutInflater());
        setContentView(numberlist.getRoot());

    }
}