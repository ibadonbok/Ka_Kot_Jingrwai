package hymn.book.kakotjingrwai01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class hymn_view extends AppCompatActivity {
    private static final String TAG = "hymn_view";

    private static final String  n1="number";
    private static final String t1="title";
    private static final String  l1="lyric";
    private static final String a1="author";

    TextView num,title,lyric,author;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymn_view);

        num=findViewById(R.id.viewnumber);
        title=findViewById(R.id.viewtitle);
        lyric=findViewById(R.id.viewlyric);
        author=findViewById(R.id.viewauthor);
    }
    public void one(View v)
    {
        db.collection("HymnNumber").document("1").get()
        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    Map<String, Object > note=documentSnapshot.getData();

                    num.setText("\n" + n1 + "\n" + t1  + "\n" + l1 + "\n" + a1  );
                }
                else
                {
                    Toast.makeText(hymn_view.this, "Document does not exist", Toast.LENGTH_SHORT).show();

                }


            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(hymn_view.this, "Error!!", Toast.LENGTH_SHORT).show();

                Log.d(TAG,e.toString());

            }
        });
    }
}