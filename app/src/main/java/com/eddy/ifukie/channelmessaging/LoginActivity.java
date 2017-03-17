package com.eddy.ifukie.channelmessaging;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;



public class LoginActivity extends AppCompatActivity implements OnDownloaderListener {

    //Variables
    private Button btnValider;
    private TextInputEditText editUsername;
    private ImageView mIvLogoWhite;
    private ImageView mIvLogoBlack;
    private TextInputEditText editPassword;
    private SharedPreferences tokens;
    public static final String token = "tokenKey";
    private Connect connection = new Connect();
    private Handler mHandlerTada = new Handler(); // android.os.handler
    private int mShortDelay = 4000; //milliseconds

    //Save the access token in a file
    public static final String MY_TOKENS = "MyTokensFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);    //Setting the layout

        editUsername = (TextInputEditText) findViewById(R.id.username);
        editPassword = (TextInputEditText) findViewById(R.id.password_id);
        mIvLogoWhite = (ImageView) findViewById(R.id.logoWhite);
        mIvLogoBlack = (ImageView) findViewById(R.id.logoBlack);

        //Button
        btnValider = (Button) findViewById(R.id.button);
        tokens = getSharedPreferences(MY_TOKENS, Context.MODE_PRIVATE);

        mHandlerTada.postDelayed(new Runnable(){
            public void run(){
                // Your code here

                mHandlerTada.postDelayed(this, mShortDelay);
            }
        }, mShortDelay);

        //When the button is clicked, so add whatever you want
        // accomplished by the button you put here.
        btnValider.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String username = "username";
                String password = "password";

                HashMap<String, String> params = new HashMap<String, String>();
                params.put(username, editUsername.getText().toString());
                params.put(password, editPassword.getText().toString());

                if(v.getId() == R.id.button){
                    String UserName = String.valueOf(editUsername.getText());
                    String Password = String.valueOf(editPassword.getText());

                    Downloader d =
                            new Downloader(LoginActivity.this, "http://www.raphaelbischof.fr/messaging/?function=connect", params);
                    d.setOnDownloadListener(LoginActivity.this);
                    d.execute();
                }
            }
        });

    }//End of oncreate

    //We deserialise the results downloaded here.
    @Override
    public void onDownloader(String values) {
        SharedPreferences.Editor editor = tokens.edit();
        //Gson: convert Json to Java object

        Gson gson = new Gson();
        connection = gson.fromJson(values, Connect.class);

        editor.putString(token, connection.getAccesstoken());
        connection.getAccesstoken();
        editor.commit();
        if (connection.getResponse().equalsIgnoreCase("OK")) {
            Toast.makeText(getApplicationContext(),
                    "Réussi", Toast.LENGTH_SHORT).show();

            editor.putString(token, connection.getAccesstoken());
            connection.getAccesstoken();
            editor.commit();

//            Intent ob = new Intent(LoginActivity.this, MainActivity.class);
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mIvLogoWhite, "logo").toBundle());

            startActivity(loginIntent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Erreur de connexion", Toast.LENGTH_SHORT).show();
        }
    }
}