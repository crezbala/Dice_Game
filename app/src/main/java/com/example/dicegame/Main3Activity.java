package com.example.dicegame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    EditText t1,t2;
    Button b1,b2,go;
    GoogleSignInClient mGoogleSignInClient;
    String email,password;
    ProgressDialog mprogress;
    private FirebaseAuth mAuth;
    //GoogleSignInClient mGoogleSignInClient;
    ProgressBar progressBar;
    static final int GOOGLE_SIGN=123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();

        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.Register2);
        b2=(Button)findViewById(R.id.Register1);
        go=(Button)findViewById(R.id.login);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInGoogle();
            }
        });

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email= String.valueOf(t1.getText());
                password= String.valueOf(t2.getText());
                mprogress = new ProgressDialog(Main3Activity.this);
                mAuth = FirebaseAuth.getInstance();


                if( !(email.equals("") && password.equals("") )) {

                    mprogress.setMessage("Signing In...");
                    mprogress.show();
                    signIn( email, password );


                } else if( email.equals("")){

                    Toast.makeText(getApplicationContext(), "Enter valid mail Id", Toast.LENGTH_SHORT).show();

                } else if ( password.equals( "")) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
                }



            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent = new Intent(Main3Activity.this,Register.class);
                                      startActivity(intent);
                                  }
                              }
        );


    }
    public void SignInGoogle() {

        progressBar.setVisibility(View.VISIBLE);
        //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        // startActivityForResult(signInIntent, GOOGLE_SIGN);
    }
    private void signIn(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(Main3Activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "Login Successful..", Toast.LENGTH_SHORT).show();
                    mprogress.dismiss();
                    Intent intent = new Intent( Main3Activity.this, index.class);

                    intent.putExtra("username",email.toString());

                    startActivity(intent);
                    finish();

                }else {

                    mprogress.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed...Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
