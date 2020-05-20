package com.papuaplus.githubuserfinder;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.papuaplus.githubuserfinder.adapters.CustomUser;
import com.papuaplus.githubuserfinder.models.GetUserData;
import com.papuaplus.githubuserfinder.models.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {
    private final String TAG="MainActivity";

    private RecyclerView listView;
    private EditText queryText;
    private ArrayList<UserData> userData;
    private static CustomUser adapter;
    private Retrofit retrofit;
    private String BASE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(RecyclerView) findViewById(R.id.listView3);
        queryText=(EditText)findViewById(R.id.editText3);
        queryText.setOnKeyListener(this);

        //setContent();
    }

    private void setContent(String githubName) {
        if(BASE_URL==null){
            BASE_URL=getString(R.string.github_base_url);
        }

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        GithubService service=retrofit.create(GithubService.class);
        Call<GetUserData> call=service.userList(githubName);
        call.enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"HASILNYA ADALAH: "+response.body()+"");
                    //JsonObject object=response.body();
                    List<UserData> data=response.body().getListUser();
                    if(data.size()>0){
                        for (UserData ud:data){
                            Log.i(TAG,"avatar: "+ud.getAvatarURL());
                            Log.i(TAG,"login: "+ud.getLoginName());
                        }

                        /*userData=new ArrayList<>();
                        userData.add(new UserData("Nama 1","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 2","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 3","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 4","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 5","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 6","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 7","https://avatars0.githubusercontent.com/u/14094824?v=4"));
                        userData.add(new UserData("Nama 8","https://avatars0.githubusercontent.com/u/14094824?v=4"));*/

                        adapter=new CustomUser(getApplicationContext(),data);

                        listView.setAdapter(adapter);

                    }
                }else{
                    Log.e(TAG,response.message());
                }
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode==KeyEvent.KEYCODE_ENTER)){
            String githubName=queryText.getText().toString();
            Log.d(TAG,"QUERY: "+githubName);

            setContent(githubName);
            return true;
        }
        return false;
    }
}
