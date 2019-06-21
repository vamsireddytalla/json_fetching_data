package com.example.naveen.jsondata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity
{
    private List<Model> heroList;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static final String URL="https://api.myjson.com/bins/1bfwc7";

    public void setHeroList(List<Model> heroList) {
        this.heroList = heroList;
        myAdapter.setModelList(heroList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle);
        progressBar=findViewById(R.id.progressBar);
        myAdapter=new MyAdapter(heroList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        loadData();
    }
    public void loadData()
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(URL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            List<Model> modelList=new ArrayList<>();
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful())
                {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().string());
                        JSONArray jsonArray=jsonObject.getJSONArray("heroes");

                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            Model model=new Model();
                            model.setName(jsonObject1.getString("name"));
                            model.setImage_url(jsonObject1.getString("imageurl"));
                            modelList.add(model);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setHeroList(modelList);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

    }
}
