package com.example.bigbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class CatThreeDetails extends AppCompatActivity {
    private ImageView imageView;
    private TextView title;
    private  TextView prize;
    private Button cart_btn;

    private ElegantNumberButton numberButton;
    private  String productID="";
    private DatabaseReference databaseReference;

    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cat_three_details );
        imageView=findViewById( R.id.image );
        title=findViewById( R.id.title );
        prize=findViewById( R.id.prize );

        cart_btn=findViewById( R.id.cart_btn );
        numberButton=(ElegantNumberButton)findViewById( R.id.number_btn );
        productID=getIntent().getStringExtra( "pid" );
        databaseReference= FirebaseDatabase.getInstance().getReference("request");



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
            public void onClick(View v) {
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
