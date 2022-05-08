package hymn.book.kakotjingrwai01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import hymn.book.kakotjingrwai01.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {

     private FirebaseAuth auth;
     private ActivityDashBoardBinding dashBoardBinding;
     FirebaseFirestore firestore;
     TextView yourname,youremail;
     String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dashBoardBinding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(dashBoardBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        userId =auth.getCurrentUser().getUid();

        DocumentReference documentReference=firestore.collection("user").document("semail");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                yourname.setText(value.getString("username"));
                youremail.setText(value.getString("email"));
            }
        });


        Toast.makeText(this, "Login Successfully with id "+auth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Login Successfully with email "+auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

        dashBoardBinding.logoutbut.setOnClickListener(V->{
           auth.signOut();
           startActivity(new Intent(getApplicationContext(), Login.class));
           finish();
        });

    }

}