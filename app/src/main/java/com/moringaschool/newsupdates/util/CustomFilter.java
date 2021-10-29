package com.moringaschool.newsupdates.util;

import android.widget.Filter;

import com.moringaschool.newsupdates.adapters.NewsListAdapter;
import com.moringaschool.newsupdates.models.Article;

import java.util.ArrayList;

public class CustomFilter extends Filter{

    NewsListAdapter adapter;
    ArrayList<Article> filterList;

    public CustomFilter(ArrayList<Article> filterList,NewsListAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Article> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint) || filterList.get(i).getAuthor().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.mTop_headlines= (ArrayList<Article>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}

