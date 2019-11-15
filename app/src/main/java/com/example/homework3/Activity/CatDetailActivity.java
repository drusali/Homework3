package com.example.homework3.Activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.homework3.Model.CatDataBase;
import com.example.homework3.Model.Cats;
import com.example.homework3.Model.FavCats;
import com.example.homework3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CatDetailActivity extends AppCompatActivity {
    private TextView CatName;
    private TextView Catdesc;
    private TextView CatWeight;
    private TextView CatTemperament;
    private TextView CatOrigin;
    private TextView CatLifeSpan;
    private TextView DogFriendly;
    private TextView CatWiki;
    private String CatPicLink;
    private ImageView CatPic;
    private Button addtoFav;


@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView( R.layout.activity_cat_detail);
    Intent intent= getIntent();
    CatPic=findViewById( R.id.CatImage );

    //setting up image
    String breed= intent.getStringExtra("id");
    final String url= "https://api.thecatapi.com/v1/images/search?breed_ids=" + breed;
    final RequestQueue requestQueue=Volley.newRequestQueue( this );
    Response.Listener<String> responseListener= new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray jsonArray = new JSONArray( response );
                JSONObject jsonObject = jsonArray.getJSONObject( 0 );
                if (jsonObject.has("url")) {
                    CatPicLink = jsonObject.getString("url");
                    if (!CatPicLink.equals( "" )) {
                        Glide.with( getApplicationContext() ).load( CatPicLink ).into(CatPic);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            requestQueue.stop();
        }
    };
    Response.ErrorListener errorListener= new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Request Failed");
        }
    };
    StringRequest stringRequest= new StringRequest(Request.Method.GET, url, responseListener,errorListener);
    requestQueue.add(stringRequest);

    String Id=intent.getStringExtra( "id");
    Cats cats= CatDataBase.getCatId(Id);
    CatName=findViewById(R.id.CatType);
    Catdesc=findViewById(R.id.description);
    CatWeight= findViewById(R.id.weight);
    CatTemperament= findViewById(R.id.temperament);
    CatOrigin= findViewById(R.id.Origin);
    CatLifeSpan= findViewById(R.id.lifespan);
    DogFriendly= findViewById(R.id.doggo);
    addtoFav=findViewById( R.id.addToFavourite );
    CatWiki= findViewById( R.id.Url );


    CatName.setText(cats.getName());
    Catdesc.setText( cats.getDescription() );
    CatTemperament.setText( cats.getTemperament());
    CatOrigin.setText(cats.getOrigin());
    CatLifeSpan.setText(cats.getLife_span());
    DogFriendly.setText(cats.getDog_friendly());
    CatWeight.setText(cats.getWeight().getMetric());
    CatWiki.setText(cats.getWikipedia_url());
    addtoFav.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addToFavouritesActivity();
            Toast.makeText(getApplicationContext(),"Cat added to Favourites, return home",Toast.LENGTH_LONG).show();
        }
    });



}
    public void addToFavouritesActivity() {

        String name = (String) CatName.getText();



        MainActivity.favCats.add(new FavCats(name));
        System.out.println("Item added");


    }
}