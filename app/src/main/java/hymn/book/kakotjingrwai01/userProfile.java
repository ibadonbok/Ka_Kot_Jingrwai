package hymn.book.kakotjingrwai01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import hymn.book.kakotjingrwai01.databinding.ActivityUserProfileBinding;

public class userProfile extends AppCompatActivity {
    private ActivityUserProfileBinding userProfileBinding;

    private FirebaseFirestore userdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userProfileBinding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(userProfileBinding.getRoot());

        userdb = FirebaseFirestore.getInstance();

        loadUserData(userdb);


    }

    private void loadUserData(FirebaseFirestore userdb) {

        userdb.collection("users").document("D6lM5wDayTS4qITetQhR")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                     if (documentSnapshot.exists()){
                         userProfileBinding.profileData.setText(documentSnapshot.getData().toString());


                     }
                    }
                });



    }


}