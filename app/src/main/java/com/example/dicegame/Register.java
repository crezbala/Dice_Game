package com.example.dicegame;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText edusername,ednumber,edpass,edconpass,edmail;
    Button Registerb1;
    String mail,unumber,pass,pass2,uname;
    ProgressDialog progress;
    FirebaseAuth rAuth;
    DatabaseReference rDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edusername=(EditText)findViewById(R.id.editText6);
        edmail=(EditText)findViewById(R.id.editText);
        ednumber=(EditText)findViewById(R.id.editText3);
        edpass=(EditText)findViewById(R.id.editText4);
        edconpass=(EditText)findViewById(R.id.editText5);
        Registerb1=(Button)findViewById(R.id.Register2);
        progress=new ProgressDialog(Register.this);
        rAuth = FirebaseAuth.getInstance();

        Registerb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname= String.valueOf(edusername.getText());

               mail= String.valueOf(edmail.getText());
               unumber= String.valueOf(ednumber.getText());
               pass= String.valueOf(edpass.getText());
               pass2= String.valueOf(edconpass.getText());

                if( !( TextUtils.isEmpty(uname)
                        && TextUtils.isEmpty(mail)
                        && TextUtils.isEmpty(unumber)
                        && TextUtils.isEmpty(pass)
                        && TextUtils.isEmpty(pass2) ) ) {

                    if( pass.length() < 8 ) {

                        Toast.makeText(getApplicationContext(), "Password should have minimum 8 character", Toast.LENGTH_SHORT).show();

                    }else if(!pass.equals(pass2 ) ){
                        Toast.makeText(getApplicationContext(), "Password does'nt matched...", Toast.LENGTH_SHORT).show();
                    } else {


                        progress.setMessage("Signing Up....");
                        progress.show();

                       register(uname, mail, unumber, pass);

                    }

                } else if (TextUtils.isEmpty(uname)) {
                    Toast.makeText(getApplicationContext(), "Enter name...", Toast.LENGTH_SHORT).show();
                } else if( TextUtils.isEmpty(mail) ) {
                    Toast.makeText(getApplicationContext(), "Enter mail address...", Toast.LENGTH_SHORT).show();
                } else if( TextUtils.isEmpty(unumber)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number...", Toast.LENGTH_SHORT).show();
                } else if( TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password...", Toast.LENGTH_SHORT).show();
                } else if( TextUtils.isEmpty(pass2)) {
                    Toast.makeText(getApplicationContext(), "Password does'nt matched...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void register(final String name, final String mail, final String phone, final String password) {

        rAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ) {

                            Map<String, String> map = new HashMap<String, String>();

                            map.put("email",mail );
                            map.put("name",name);
                            map.put("password",password);
                            map.put("phone",phone);

                            rDatabase.child(rAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(getApplicationContext(), "Login Successful...", Toast.LENGTH_SHORT).show();
                                        Registerb1.setClickable(true);
                                        progress.dismiss();


                                    } else {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progress.dismiss();

                                                if( task.isSuccessful()) {
                                                    Toast.makeText(getApplicationContext(), "Login Failed...Try Again", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            });



                        } else {
                            progress.dismiss();

                            Toast.makeText(getApplicationContext(), "Sign Up failed..Try Again...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }



}
