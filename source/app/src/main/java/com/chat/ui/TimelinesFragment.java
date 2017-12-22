package com.chat.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chat.MainActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.chat.R;
import com.chat.data.StaticConfig;
import com.chat.model.ListTimeline;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelinesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerListFrends;
    private ListTimelinesAdapter adapter;
    private ListTimeline dataListTimeline = null;
    private ArrayList<String> listTimelineID = null;
    private LovelyProgressDialog dialogFindAllTimeline;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CountDownTimer detectTimelineOnline;
    public static int ACTION_START_CHAT = 1;
  
    private BroadcastReceiver deleteTimelineReceiver;

    private EditText editContent;
    private ImageButton btnSend;
    private ImageButton btnAddImage;

    public void init(View v){
        editContent = (EditText) v.findViewById(R.id.edit_ContentFriend);
        btnSend = (ImageButton) v.findViewById(R.id.btnSend);
        btnAddImage = (ImageButton) v.findViewById(R.id.btnAddImage);  
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_timelines, container, false);

        init(layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerListFrends = (RecyclerView) layout.findViewById(R.id.recycleListTimeline);
        recyclerListFrends.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
         
        return layout;
    }

    @Override
    public void onDestroyView (){
        super.onDestroyView();  
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); 
    }

    @Override
    public void onRefresh() {
    }
   
}

class ListTimelinesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ListTimeline listTimeline;
    private Context context;
    public static Map<String, Query> mapQuery;
    public static Map<String, DatabaseReference> mapQueryOnline;
    public static Map<String, ChildEventListener> mapChildListener;
    public static Map<String, ChildEventListener> mapChildListenerOnline;
    public static Map<String, Boolean> mapMark;
    private TimelinesFragment fragment;
    LovelyProgressDialog dialogWaitDeleting;

    public ListTimelinesAdapter(Context context, ListTimeline listTimeline, TimelinesFragment fragment) {
        this.listTimeline = listTimeline;
        this.context = context;
        mapQuery = new HashMap<>();
        mapChildListener = new HashMap<>();
        mapMark = new HashMap<>();
        mapChildListenerOnline = new HashMap<>();
        mapQueryOnline = new HashMap<>();
        this.fragment = fragment;
        dialogWaitDeleting = new LovelyProgressDialog(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rc_item_friend, parent, false);
        return new ItemTimelineViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
      return 123;
    }

    /**
     * Delete friend
     *
     * @param idTimeline
     */
    private void deleteTimeline(final String idTimeline) {

    }
}

class ItemTimelineViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView avatar;
    public TextView txtName, txtTime, txtMessage;
    private Context context;

    ItemTimelineViewHolder(Context context, View itemView) {
        super(itemView);
        avatar = (CircleImageView) itemView.findViewById(R.id.icon_avatar);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtTime = (TextView) itemView.findViewById(R.id.txtTime);
        txtMessage = (TextView) itemView.findViewById(R.id.txtMessage);
        this.context = context;
    }
}

