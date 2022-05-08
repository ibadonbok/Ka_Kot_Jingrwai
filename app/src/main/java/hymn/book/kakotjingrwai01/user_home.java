package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hymn.book.kakotjingrwai01.databinding.ActivityDashBoardBinding;
import hymn.book.kakotjingrwai01.databinding.ActivityNumberlistviewBinding;
import hymn.book.kakotjingrwai01.databinding.ActivityUserHomeBinding;

public class user_home extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivityUserHomeBinding homeBinding;
    Button number, khorus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_home);

        homeBinding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        homeBinding.number.setOnClickListener(view -> {
            Intent intent = new Intent(user_home.this, hymn_number.class);
            startActivity(intent);
        });


        homeBinding.khorus.setOnClickListener(view -> {
            Intent khorus = new Intent(user_home.this, khorus_number.class);
            startActivity(khorus);

        });

        homeBinding.userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(user_home.this, userProfile.class);
                startActivity(user);
            }
        });

        homeBinding.notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent note = new Intent(user_home.this, note_saved.class);
                startActivity(note);
            }
        });


        auth = FirebaseAuth.getInstance();
        Toast.makeText(this, "Login Successfully with id " + auth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Login Successfully with email " + auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

        homeBinding.logout.setOnClickListener(V -> {
            auth.signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });

    }
}