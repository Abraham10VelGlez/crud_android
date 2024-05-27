package com.apk.sesionesfaxevook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class bienvenido extends AppCompatActivity {

    /*esto es para google*/
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    /*esto es para google*/

    //vista
    /*esto es para ambos*/
    TextView nombre,correo;
    Button btncerrar;
    /*esto es para ambos*/

    /*esto es para facebook*/
    ImageView imageView;
    /*esto es para facebook*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        // PARA AMBOS google y facebook
        nombre = findViewById(R.id.textView3);
        correo = findViewById(R.id.textView4);
        btncerrar = findViewById(R.id.button3);

        // PARA AMBOS

        //IMAGEN PARA FACEBOOOK
        imageView = findViewById(R.id.imageView2);
        //IMAGEN PARA FACEBOOOK


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //
        //
        // este es para faceboook...
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {

                            String nomjson = object.getString("name");
                            //String correojson = object.getString("email");

                            nombre.setText(nomjson);
                            correo.setText("facebook@gmail.com");

                            // vamos a retornar la imagen del facebook
                            String url = object.getJSONObject("picture").getJSONObject("data").getString("url");

                            Picasso.get().load(url).into(imageView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
        // este es para faceboook...



        //******************************************************************************************************************************************** GOOGLE *********************************
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if( acct != null){
            String personname = acct.getDisplayName();
            String personalemail = acct.getEmail();
            nombre.setText(personalemail);
            correo.setText(personalemail);

        }

        btncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // este es para faceboook...
                LoginManager.getInstance().logOut();
                startActivity(new Intent(bienvenido.this, MainActivity.class));
                finish();
                // este es para faceboook...
                // este es para google...
                signOut();
                // este es para google...
            }

            private void signOut() {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(bienvenido.this,MainActivity.class));
                    }
                });
            }
        });
        //******************************************************************************************************************************************** GOOGLE *********************************







    }
}