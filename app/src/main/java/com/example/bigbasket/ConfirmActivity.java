package com.example.bigbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmActivity extends AppCompatActivity {
private EditText name,phone,address,pin;
private Button confirm;
private String totalamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_confirm );

        totalamount=getIntent().getStringExtra( "Totalamount" );
        confirm=findViewById( R.id.confirm );
        name=findViewById( R.id.name );
        phone=findViewById( R.id.phone );
        address=findViewById( R.id.address );
        pin=findViewById( R.id.pin );
        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();

            }
        } );

    }
    public  void check()
    {
        if(TextUtils.isEmpty( name.getText().toString() ))
        {
            Toast.makeText( this,"Please Provide your Full Name",Toast.LENGTH_SHORT ).show();
        }
        else if(TextUtils.isEmpty( phone.getText().toString() ))
        {
            Toast.makeText( this,"Please Provide your Phone No.",Toast.LENGTH_SHORT ).show();
        }
        else if(TextUtils.isEmpty( address.getText().toString() ))
        {
            Toast.makeText( this,"Please Provide your Address ",Toast.LENGTH_SHORT ).show();
        }
        else if(TextUtils.isEmpty( pin.getText().toString() ))
        {
            Toast.makeText( this,"Please ProvidePin Code",Toast.LENGTH_SHORT ).show();
        }
        else
        {
            sendData();
        }
    }
    public  void sendData(){
final  String savecurdate,savecurtime;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat( "MMM dd, yyyy" );
        savecurdate=currentDate.format( calendar.getTime() );
        SimpleDateFormat currentTime=new SimpleDateFormat( "HH:mm:ss a" );
        savecurtime=currentTime.format( calendar.getTime() );
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child( "orders" ).child(user.getUid());;
        HashMap<String,Object> orderMap=new HashMap<>(  );
        orderMap.put("Totalamount",totalamount);
        orderMap.put( "Name",name.getText().toString() );
        orderMap.put( "Phone",phone.getText().toString() );
        orderMap.put( "Address",address.getText().toString() );
        orderMap.put( "Pin",pin.getText().toString() );
        orderMap.put( "date",savecurdate );
        orderMap.put("time",savecurtime);

        databaseReference.updateChildren( orderMap );
        Intent intent=new Intent( ConfirmActivity.this,LastActivity.class );
        startActivity( intent );
        finish();
    }
}
