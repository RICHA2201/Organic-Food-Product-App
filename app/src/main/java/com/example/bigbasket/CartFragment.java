package com.example.bigbasket;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView cartlist;
    private DatabaseReference databaseReference;
private Button order;
private  TextView totalprice;
private int total=0;
int price=0;
int a=0,b=0;
  ImageView delete;
    FirebaseRecyclerAdapter<Model,cartonevewholder> adapter;
    String myKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate( R.layout.fragment_cart, container, false );
        cartlist=myview.findViewById( R.id.cart_list );
        order=myview.findViewById( R.id.order );
        delete=myview.findViewById( R.id.delete );
        totalprice=myview.findViewById( R.id.totalprice );
order.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {

totalprice.setText( "Total Price="+total );
        Intent intent=new Intent( getActivity(),ConfirmActivity.class );
        intent.putExtra( "Totalamount",String.valueOf( total ) );
        startActivity( intent );
total=0;




    }
} );
        databaseReference= FirebaseDatabase.getInstance().getReference().child( "request" );
     LinearLayoutManager layoutManager=new LinearLayoutManager( getActivity() );
     layoutManager.setStackFromEnd( true );
     layoutManager.setReverseLayout( true );
cartlist.setLayoutManager( layoutManager );


        return  myview;
    }

    public  void onStart() {


        super.onStart();
       adapter=new FirebaseRecyclerAdapter<Model, cartonevewholder>(
                Model.class,
                R.layout.cart_layout,
                cartonevewholder.class,
                databaseReference
        ) {


           @Override
            protected void populateViewHolder(cartonevewholder cartonevewholder, Model model, final int i) {

                cartonevewholder.setTitle( model.getTitle() );
                cartonevewholder.setQuantity( model.getQuantity() );
                cartonevewholder.setPrize( model.getPrize() );
                cartonevewholder.setImage( model.getImage() );

             a=(Integer.valueOf( model.getPrize() ));
             b=(Integer.valueOf( model.getQuantity() ));
             total=total+(a*b);
                cartonevewholder.myview.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       int position=i;
                        adapter.getRef(position).removeValue();
                        adapter.notifyItemRemoved(position);


                             a=0;
                                 b=0;
                        total=0;


                    }
                } );

            }

        };
        cartlist.setAdapter( adapter );




    }




    public static class cartonevewholder extends  RecyclerView.ViewHolder{
View myview;
    public cartonevewholder(@NonNull View itemView) {
        super( itemView );
        myview=itemView;
    }
    public  void setTitle(String title)
    {
        TextView mtitle=myview.findViewById( R.id.product_name );
        mtitle.setText( title );
    }
    public void setQuantity(String quantity){
        TextView mquantity=myview.findViewById( R.id.product_quantity );
        mquantity.setText( quantity );
    }
    public void setPrize(String price){
        TextView mprice=myview.findViewById( R.id.product_price);
        mprice.setText( "Rs "+price );
    }
    public void setImage(final String image)
    {
        final ImageView mimage=myview.findViewById( R.id.product_image );
        Picasso.get().load( image ).networkPolicy( NetworkPolicy.OFFLINE ).into( mimage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load( image ).into( mimage );
            }
        } );
    }



}


}
