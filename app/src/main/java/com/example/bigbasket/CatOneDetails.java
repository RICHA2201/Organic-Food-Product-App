package com.example.bigbasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CatOneDetails extends AppCompatActivity {
private ImageView imageView;
private TextView title;
private  TextView prize;
private Button cart_btn;

private ElegantNumberButton numberButton;
private  String productID="";
private  DatabaseReference databaseReference;

String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cat_one_details );
        imageView=findViewById( R.id.image );
        title=findViewById( R.id.title );
        prize=findViewById( R.id.prize );

cart_btn=findViewById( R.id.cart_btn );
numberButton=(ElegantNumberButton)findViewById( R.id.number_btn );
productID=getIntent().getStringExtra( "pid" );
databaseReference=FirebaseDatabase.getInstance().getReference("request");



        final Intent intent=getIntent();
        final String mtitle=intent.getStringExtra( "title" );
        final String mprize=intent.getStringExtra( "prize" );
        final String mimage=intent.getStringExtra( "image" );




        numberButton.setOnClickListener( new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                number =numberButton.getNumber();
            }
        } );




cart_btn.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        String  id=databaseReference.push().getKey();


      Model model=new Model( mtitle,mimage,mprize,productID,number);

databaseReference.child(id ).setValue( model );



    }
} );
        title.setText( mtitle );
        prize.setText( mprize );


        Picasso.get().load( mimage ).networkPolicy( NetworkPolicy.OFFLINE ).into( imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
Picasso.get().load( mimage ).into( imageView );
            }
        } );

    }



}
