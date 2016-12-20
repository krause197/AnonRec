package com.epicodus.anonrec.ui.meetups;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.meetups.SavedEventListAdapter;
import com.epicodus.anonrec.adapters.meetups.SavedEventViewHolder;
import com.epicodus.anonrec.constants.MeetupConstants;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.util.OnStartDragListener;
import com.epicodus.anonrec.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedEventListFragment extends Fragment implements OnStartDragListener {
    private DatabaseReference mEventReference;
    private SavedEventListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerview;


    public SavedEventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_saved_event_list, container, false);
        ButterKnife.bind(this, view);
        setupFirebaseAdapter();
        return view;
    }

    private void setupFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference(MeetupConstants.FIREBASE_CHILD_EVENTS).child(uid).orderByChild(MeetupConstants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new SavedEventListAdapter(Event.class, R.layout.event_list_item_drag, SavedEventViewHolder.class, query, this, getActivity());

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                    mFirebaseAdapter.notifyDataSetChanged();
                }

        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
