package hymn.book.kakotjingrwai01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hymn.book.kakotjingrwai01.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        loginBinding.progressBar2.setVisibility(View.INVISIBLE);


        loginBinding.loginbtn.setOnClickListener(V->{
            loginBinding.progressBar2.setVisibility(View.VISIBLE);
            if (!loginBinding.lusername.getText().toString().isEmpty() | loginBinding.lusername.getText().toString().contains("@")){
                if(!loginBinding.lpass.getText().toString().isEmpty()){
                    LoginUser(loginBinding.lusername.getText().toString(),loginBinding.lpass.getText().toString());
                }else{
                    //Toast
                    Toast.makeText(this, "invalid password", Toast.LENGTH_SHORT).show();
                }
            }else{
                //Toast
                Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show();
            }

        });

        loginBinding.signupbutton.setOnClickListener(V->{
            startActivity(new Intent(getApplicationContext(),Signup.class));
            finish();
        });
    }

    private void LoginUser(String email, String passwords) {
        loginBinding.loginbtn.setEnabled(false);
        Toast.makeText(this, "email"+email+" pass:  "+passwords, Toast.LENGTH_SHORT).show();

        firebaseAuth.signInWithEmailAndPassword(email,passwords).addOnCompleteListener(task -> {
              if(task.isSuccessful()){
                  Intent intent = new Intent(getApplicationContext(), user_home.class);
                  startActivity(intent);
                  finish();
              }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Login Failed Please check your login credentials "+e.getMessage(), Toast.LENGTH_SHORT).show();
        });
        loginBinding.progressBar2.setVisibility(View.INVISIBLE);
        loginBinding.loginbtn.setEnabled(true);
    }

}