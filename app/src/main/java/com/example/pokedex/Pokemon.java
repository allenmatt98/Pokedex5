package com.example.pokedex;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon extends AppCompatActivity {
    ImageView poke;
    TextView name,weight,height,type;
    String pokemon="";

    String pokemonName="";
    String pokemonWeight="";
    String pokemonHeight="";
    String pokemonType="";
    String pokemonImage="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        poke=(ImageView)findViewById(R.id.imageView);
        name=(TextView)findViewById(R.id.textView);
        weight=(TextView)findViewById(R.id.textView2);
        height=(TextView)findViewById(R.id.textView3);
        type=(TextView)findViewById(R.id.textView4);

        Intent intent=getIntent();
        pokemon=intent.getStringExtra("keyname");
Log.e("before","async task");
        new GetDetails().execute();

       Log.e("inside","getdetails completed");
      //  name.setText(pokemonName);
       
    }

    private class GetDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Pokemon.this, " Downloading", Toast.LENGTH_LONG).show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.e("inside","do in backgrond");
            Handler sh = new Handler();
            // Making a request to url and getting response
            String url = "http://pokeapi.co/api/v2/pokemon/"+pokemon+"/";
            String response=sh.makeServiceCall(url);


            if(response!=null)
            {
                Log.e("inside","response received");
                try{

                    final JSONObject jsonObj = new JSONObject(response);


                    String imageUrl="";

                    String sprites="";
                    sprites=jsonObj.getString("sprites");
                    Log.e("json sprites :  =",sprites);

                    JSONObject jsonSpriteObj=new JSONObject(sprites);
                    imageUrl=jsonSpriteObj.getString("front_default");




                    pokemonName=jsonObj.getString("name");
                    pokemonWeight=jsonObj.getString("weight");
                    pokemonHeight=jsonObj.getString("height");
                    pokemonImage=imageUrl;
                    final String testStr=pokemonName;
                    final String testWeight=pokemonWeight;
                    final String testHeight=pokemonHeight;
                    final String testType=pokemonType;
                  //  final String testimg=pokemonImage;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Toast.makeText(getApplicationContext(),
                                    "Succesful",
                                    Toast.LENGTH_LONG).show();
                            Log.e("Working","inside run");
                            name.setText("Name - "+testStr);
                            weight.setText("Weight - " + testWeight);
                            height.setText("Height - " + testHeight);
                            type.setText(testType);

                           Picasso.with(Pokemon.this).load(pokemonImage).placeholder(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher).into(poke);
                        }
                    });

                }
                catch(JSONException e)
                {


                    Log.e("json excp ",e.getMessage());
                }



            }
            else
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Toast.makeText(getApplicationContext(),
                                "json working wrong",
                                Toast.LENGTH_LONG).show();

                    }
                });

            }
            return null;
        }
    }

}

