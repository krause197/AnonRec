package com.epicodus.anonrec.adapters.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.constants.MessageConstants;
import com.epicodus.anonrec.models.Message;
import com.epicodus.anonrec.models.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Guest on 12/15/16.
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
    public ValueEventListener mValueEventListener;
    private Query mCommentNumberRef;
    private Query mMessageAuthorRef;
    private Query mUserIconRef;
    String commentNumber;
    String author;

    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindMessage (Message message) {
//        TextView titleTextView = (TextView) mView.findViewById(R.id.titleTextView);
//        TextView authorTextView = (TextView) mView.findViewById(R.id.authorTextView);
//        ImageView userIconImageView = (ImageView) mView.findViewById(R.id.userIconImageView);
//        final TextView commentNumber = (TextView) mView.findViewById(R.id.commentNumber);

//        mMessageAuthorRef = FirebaseDatabase.getInstance().getReference("profiles").child("pushId").equalTo(Message.PushId());
//        author = mMessageAuthorRef;
//
//        mUserIconRef =
//
//        mCommentNumberRef = FirebaseDatabase.getInstance().getReference(MessageConstants.FIREBASE_QUERY_COMMENTS).child("messageId").equalTo(Message.getId());
//        mValueEventListener = mCommentNumberRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                commentNumber.setText("Comments: " + dataSnapshot.getChildrenCount() +"");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



//        titleTextView.setText(message.getTitle());
//        authorTextView.setText(message.getAuthor());


    }
}
