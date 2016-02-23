package phoenix.idex.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import phoenix.idex.AdapterClassModel.ItemPost;
import phoenix.idex.Adapters.PostRecycleViewAdapter;
import phoenix.idex.Decoration.PostDivider;
import phoenix.idex.Decoration.PostSpacing;
import phoenix.idex.R;

/**
 * Created by Ravinder on 2/19/16.
 */
public class PostListFragment extends android.support.v4.app.Fragment implements FloatingActionButton.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private List<ItemPost> itemPosts;
    private PostRecycleViewAdapter recycleViewAdapter;
    private RecyclerView postReyclerView;
    private FloatingActionButton postWidget;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postWidget:
                Toast.makeText(getActivity(), "POST SOMETHING...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public PostListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.post_recyclerview, container, false);
        postReyclerView = (RecyclerView) v.findViewById(R.id.postRecyclerView);
        postWidget = (FloatingActionButton) v.findViewById(R.id.postWidget);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refreshLayout);

        postWidget.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(this);

        postReyclerView.addItemDecoration(new PostDivider(getActivity()));
        postReyclerView.addItemDecoration(new PostSpacing(20));

        itemPosts = new ArrayList<>();
        itemPosts.add(new ItemPost("Not too often do i get to be the first poster, so i will make my first " +
                "post of Idex. You can't stop me. Not too often do i get to be the first poster, so i will make my first \" +\n" +
                "\"post of Idex. You can't stop me. Not too often do i get to be the first poster, so i will make my first \" +\n" +
                "\"post of Idex. You can't stop me^=^ ", "5h", "Leonardo da Vinci"));
        itemPosts.add(new ItemPost("A multi-purpose bag-pack for student. It can be used for daily backpack" +
                "or it can turn into a sleeping tent. Sleep wherever you study because why not!?", "2h", "Ortemis"));
        itemPosts.add(new ItemPost("I promise this is not a spam.", "3h", "FlightEagle"));
        itemPosts.add(new ItemPost("I have this idea that a flying car will use only solar power through" +
                "breaking down a particular molecule from sunlight.", "3h", "SpaceMan123"));
        itemPosts.add(new ItemPost("Wont you please just skip over this post. This developer sucks. He didn't" +
                "include a functionality for deleting a post. Totally giving this app 1 star.", "5h", "Obama"));
        itemPosts.add(new ItemPost("Not too often do i get to be the first poster, so i will make my first " +
                "post of Idex. You can't stop me. ^=^ ", "5h", "Leonardo da Vinci"));


        recycleViewAdapter = new PostRecycleViewAdapter(getActivity(), itemPosts);
        postReyclerView.setAdapter(recycleViewAdapter);
        postReyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
