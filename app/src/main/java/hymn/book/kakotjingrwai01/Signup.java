package hymn.book.kakotjingrwai01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import hymn.book.kakotjingrwai01.databinding.ActivitySignupBinding;
//
//import hymn.book.kakotjingrwai.database.DatabaseHelper;
//import hymn.book.kakotjingrwai.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {

    private EditText susername, semail, spass,age;
    private Button signup;
//    private DatabaseHelper myDB;

    //add activity binding class
    private ActivitySignupBinding signupBinding;

    private FirebaseAuth firebaseAuth;   //firebase authentication

    //FirebaseFirestore firebasestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(signupBinding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        //firebasestore = Firebasestore.getInstance();

        //susername=getCurrentUser().getUid();

        //DocumentReference documentreference=firebasestore.collection("user").document(username);

        signupBinding.signupbutton.setOnClickListener(v -> {
            if (signupBinding.susername.getText().length() >= 3 | !signupBinding.susername.getText().toString().isEmpty()) {
                if (!signupBinding.semail.getText().toString().isEmpty() | signupBinding.semail.getText().toString().contains("@")) {
                    if (!signupBinding.spassword.getText().toString().isEmpty() | signupBinding.spassword.getText().toString().length() >= 6) {
                        signup(signupBinding.susername.getText().toString(), signupBinding.semail.getText().toString().trim(), signupBinding.spassword.getText().toString().trim(),signupBinding.sage.getText().toString().trim());
                    } else {
                        Toast.makeText(this, "Enter A valid password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Enter A valid email!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter A valid username!", Toast.LENGTH_SHORT).show();
            }
        });


        //myDB = new DatabaseHelper(this);

        //insertuser();


    }

    private void signup(String username, String email, String spassword,String age) {
        signupBinding.signupbutton.setEnabled(false);
        signupBinding.progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "email" + email + "password " + spassword, Toast.LENGTH_SHORT).show();
        //for registration
        firebaseAuth.createUserWithEmailAndPassword(email,spassword)
                .addOnCompleteListener(this, response -> {
                   if(response.isSuccessful()) {
                       String userid =firebaseAuth.getUid();
                       Toast.makeText(this, "User Registered successfully "+userid, Toast.LENGTH_SHORT).show();
                       //Toast.makeText(this, "reponse"+response.getResult().toString(), Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(Signup.this,Login.class);
                       startActivity(intent);
                       finish();
                   }else{
                       Toast.makeText(this, response.getException().toString(), Toast.LENGTH_SHORT).show();
                   }
                    signupBinding.progressBar.setVisibility(View.INVISIBLE);
                    signupBinding.signupbutton.setEnabled(true);
                });
    }

    // private void insertuser() {
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//             /*   String user = susername.getText().toString();
//                String email = semail.getText().toString();
//                String pass = spass.getText().toString();
//
//                Boolean checkusers = null;
//                if (user.equals("") || email.equals("") || pass.equals("")) {
//                    Toast.makeText(Signup.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();
//                    checkusers = myDB.checkusername(user);
//                }
//
//                if (checkusers == false)
//                {
//                    Boolean insert = myDB.Signup(user,email, pass);
//                    if(insert==true)
//                    {
//                        Toast.makeText(Signup.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                        Toast.makeText(Signup.this, "Failed to Signup", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(Signup.this, "User Already Exist", Toast.LENGTH_SHORT).show();
//                }*/
//
//
//                boolean var = myDB.Signup(susername.getText().toString(), semail.getText().toString(), spass.getText().toString());
//                if (var == true)
//
//                    Toast.makeText(Signup.this, "User Register Successfuly", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(Signup.this, "Failed to Register", Toast.LENGTH_SHORT).show();
//            }
//        });
    //}
}

