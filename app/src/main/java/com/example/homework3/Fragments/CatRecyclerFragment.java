package com.example.homework3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homework3.Activity.MainActivity;
import com.example.homework3.CatAdapter;
import com.example.homework3.Model.CatDataBase;
import com.example.homework3.Model.Cats;
import com.example.homework3.R;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class CatRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText text;
    public Button searchbutton;

    public CatRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        text=view.findViewById(R.id.text);
        searchbutton=view.findViewById( R.id.searchbutton );

        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.thecatapi.com/v1/breeds";



        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cats[] cat = gson.fromJson(response, Cats[].class);
                        List<Cats> catList = Arrays.asList(cat);
                        catAdapter.setData(catList);
                        recyclerView.setAdapter(catAdapter);
                        CatDataBase.saveCatstoDB(catList);
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearchActivity();
            }
        } );
return view;
    }



    public void onResume() {
        super.onResume();
    }

    public void newSearchActivity() {
        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue requestqueue = Volley.newRequestQueue( getContext() );
        String catID = text.getText().toString();
        String catURL = "https://api.thecatapi.com/v1/breeds/search?q=" + catID;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] cat = gson.fromJson( response, Cats[].class );
                List<Cats> articleCat = Arrays.asList( cat );
                catAdapter.setData( articleCat );
                recyclerView.setAdapter( catAdapter );
                CatDataBase.saveCatstoDB( articleCat );
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println( "Request Failed" );
            }
        };
        StringRequest stringRequest = new StringRequest( Request.Method.GET, catURL, responseListener, errorListener );
        requestqueue.add( stringRequest );

    }
}
