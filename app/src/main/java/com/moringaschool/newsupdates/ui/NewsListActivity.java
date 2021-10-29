package com.moringaschool.newsupdates.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.adapters.NewsListAdapter;
import com.moringaschool.newsupdates.models.Article;
import com.moringaschool.newsupdates.network.NewsApi;
import com.moringaschool.newsupdates.network.NewsClient;
//import com.moringaschool.newsupdates.NewsScopesArrayAdapter;
import com.moringaschool.newsupdates.models.NewsUpdatesSearchResponse;
import com.moringaschool.newsupdates.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    public static final String TAG = NewsListActivity.class.getSimpleName();


//    @BindView(R.id.newsTextView) ListView mNewsTextView;
//    @BindView(R.id.editTextPersonName) TextView mEditTextPersonName;



//    RecyclerView implementation

    @BindView(R.id.recyclerView) RecyclerView mRecyclerview;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private NewsListAdapter mAdapter;

    public ArrayList<Article> top_headlines;

    //Override calling get methods in main layout and class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.NEWSUPDATES_API_KEY, null);
        if(mRecentAddress != null){
//            fetchRestaurants(mRecentAddress);
        }
        //User's next page display welcome message, Using Intents.
        Log.v("NewsScopeActivity", "In the onItemClickListener!");
        final Intent intent = getIntent();
        String user = intent.getStringExtra("user");
//        mEditTextPersonName.setText("Welcome back " + user);

        NewsApi client = NewsClient.getClient();

        Call<NewsUpdatesSearchResponse> call = client.getNews("standardmedia.co.ke", "441e5fea5c6d4f29bee20f551a8cc836");


        call.enqueue(new Callback<NewsUpdatesSearchResponse>() {
            @Override
            public void onResponse(Call<NewsUpdatesSearchResponse> call, Response<NewsUpdatesSearchResponse> response) {
                if (response.isSuccessful()) {
                    hideProgressBar();
                    top_headlines = response.body().getArticles();
                    mAdapter = new NewsListAdapter(NewsListActivity.this, top_headlines);
                    mRecyclerview.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsListActivity.this);
                    mRecyclerview.setLayoutManager(layoutManager);
                    mRecyclerview.setHasFixedSize(true);
                    showTop_headlines();
                } else {
                    showUnsuccessfulMessage();
                }
            }



            @Override
            public void onFailure(Call<NewsUpdatesSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:", t);
                hideProgressBar();
                showFailureMessage();
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showTop_headlines() {
        mRecyclerview.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}
