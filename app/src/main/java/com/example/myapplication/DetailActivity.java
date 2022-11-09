package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class DetailActivity extends AppCompatActivity {
    Adpt pol =new Adpt();

    class PrimeThread extends Thread {

        public void run() {
            rec("https://perso.isima.fr/~aucatinon/jsonformation.json");

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
        Log.i("bien","test1");
        PrimeThread p =new PrimeThread();
        p.start();
        Log.i("bien","test2");

        RecyclerView u=findViewById(R.id.recycler);
        u.setAdapter(pol);
        u.setLayoutManager(new LinearLayoutManager(this));


    }

    String streamToString (InputStream i) {
        Scanner s = new Scanner(i).useDelimiter( "\\a");
        return s.hasNext() ? s.next() : "";
    }
    void rec(String l){
        Log.i("bien","test3");
        String s="";
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(l);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            s = streamToString(in);
            JSONObject j = new JSONObject( s );
            runOnUiThread(()->{
                pol.getjson(j);
                pol.notifyDataSetChanged();
            });
        } catch (IOException | JSONException e) {
            Log.e("erreur","g(g");
            e.printStackTrace();
        } finally {
            if (urlConnection!=null) urlConnection.disconnect();
        }
        Log.i("bien",s);



    }

    class Adpt extends RecyclerView.Adapter<case1>{
        JSONObject json=null;
        void getjson(JSONObject d){
            json = d;
        }
        @NonNull
        @Override
        public case1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater =LayoutInflater.from(parent.getContext());
            View vi =inflater.inflate(R.layout.case1,parent,false);

            return new case1(vi);
        }

        @Override
        public void onBindViewHolder(@NonNull case1 holder, int position) {
            try {
                if(json!=null) {
                    JSONArray jsonar = json.getJSONArray("Liste");
                    JSONObject json1 = jsonar.getJSONObject(position);
                    holder.update(json1.getString("name"), json1.getString("property"));
                }
            } catch (JSONException e) {
                holder.update("","inconnu");
            }



        }

        @Override
        public int getItemCount() {
            int o=0;
            try {
                if(json!=null) {
                    JSONArray jsonar = json.getJSONArray("Liste");
                    o = jsonar.length();

                }
            } catch (JSONException e) {}

            return o;
        }
    }
    class case1 extends RecyclerView.ViewHolder{
        TextView t1;
        TextView t2;
        public  case1 (View v){
            super(v);
            t1 = v.findViewById(R.id.case2);
            t2 =v.findViewById((R.id.case3));

        }
        void update(String i,String j){
            t1.setText(i);
            t2.setText(j);


        }
    }
}

