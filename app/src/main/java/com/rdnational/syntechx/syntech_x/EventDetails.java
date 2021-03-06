package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.r0adkll.slidr.Slidr;

import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_DESC;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_HEAD;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_HEAD_IMG;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_HEAD_PHONE;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_LOGO;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_NAME;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_PARTICIPANTS;
import static com.rdnational.syntechx.syntech_x.EventsFragment.EVENT_RULES;

public class EventDetails extends AppCompatActivity {

    ImageButton makeacall;
    ImageView headImage;
    ImageView event_logo;
    Toolbar event_name;
    TextView event_head;
    TextView event_desc;
    TextView event_participants;
    TextView event_rules;
    String eventHeadImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        getSupportActionBar().hide();

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Slidr.attach(this);

        event_logo = findViewById(R.id.events_details_logo);
        event_name = findViewById(R.id.events_details_toolbar);
        event_head = findViewById(R.id.developer_name);
        event_desc = findViewById(R.id.description_details);
        event_participants = findViewById(R.id.participants_details);
        event_rules = findViewById(R.id.rules_details);
        headImage = findViewById(R.id.developer_img);

        Intent i = getIntent();
        String eventLogo = i.getStringExtra(EVENT_LOGO);
        String eventName = i.getStringExtra(EVENT_NAME);
        String eventHead = i.getStringExtra(EVENT_HEAD);
        String eventDesc = i.getStringExtra(EVENT_DESC);
        String eventPart = i.getStringExtra(EVENT_PARTICIPANTS);
        String phone = i.getStringExtra(EVENT_HEAD_PHONE);
        eventHeadImg = i.getStringExtra(EVENT_HEAD_IMG);
        String eventRules = i.getStringExtra(EVENT_RULES);

        Glide.with(this).load(eventLogo).into(event_logo);
        Glide.with(this).load(eventHeadImg).into(headImage);
     //   event_name.setTitle(eventName);
        event_head.setText("\t"+eventHead);
        event_desc.setText(eventDesc);
        event_participants.setText(eventPart);
        event_rules.setText("");
        event_rules.setText(eventRules);

        final String no="tel:"+phone;
        makeacall = findViewById(R.id.call_developer);
        makeacall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeACall(no);
            }
        });

        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_image();
            }
        });
    }

    public void makeACall(String phone_no){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(phone_no));
        startActivity(callIntent);
    }
    public void view_image(){
        AlertDialog.Builder alertadd = new AlertDialog.Builder(EventDetails.this);
        LayoutInflater factory = LayoutInflater.from(EventDetails.this);
        final View view = factory.inflate(R.layout.view_profile, null);
        ImageView image= (ImageView) view.findViewById(R.id.view_profile);
        Glide.with(this).load(eventHeadImg).into(image);
        alertadd.setView(view);
        alertadd.show();
    }

}
