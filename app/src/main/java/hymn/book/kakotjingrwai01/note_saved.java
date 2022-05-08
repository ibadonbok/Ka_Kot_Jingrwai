package hymn.book.kakotjingrwai01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.HashMap;
import java.util.Map;

public class note_saved extends AppCompatActivity {
    private static final String TAG = "note_saved";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    private EditText editTextTitle;
    private EditText editTextDescription;
    TextView loadv;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteref=db.collection("Notebook").document("notes");
   // private ListenerRegistration notelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_saved);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        loadv = findViewById(R.id.loadview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //notelistener=
                noteref.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                {
                    Toast.makeText(note_saved.this, "Error while Loading", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, error.toString() );
                    return;
                }
                if(value.exists())
                {
                    String title=value.getString(KEY_TITLE);
                    String description=value.getString(KEY_DESCRIPTION);

                    //Map<String, Object > note=documentSnapshot.getData();

                    loadv.setText("Title: "+title + "\n" + "Description: " + description);

                }
            }
        });
    }

  /*  @Override
    protected void onStop() {
        super.onStop();
        notelistener.remove();
    }*/

    public void saveNote(View v) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        note.put(KEY_DESCRIPTION, description);

       noteref.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(note_saved.this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(note_saved.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    public void loadview(View v)
    {
        noteref.get()
        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    String title=documentSnapshot.getString(KEY_TITLE);
                    String description=documentSnapshot.getString(KEY_DESCRIPTION);

                    //Map<String, Object > note=documentSnapshot.getData();

                    loadv.setText("Title: "+title + "\n" + "Description: " + description);

                }
                else
                {
                    Toast.makeText(note_saved.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(note_saved.this, "Error!!", Toast.LENGTH_SHORT).show();

                Log.d(TAG,e.toString());
            }
        });
    }
}