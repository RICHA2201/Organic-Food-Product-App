package com.example.bigbasket;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


  private RecyclerView home1recycler;
    private RecyclerView home2recycler;
    private RecyclerView home3recycler;
private DatabaseReference mCatOneDatabase;
    private DatabaseReference mCatTwoDatabase;
    private DatabaseReference mCatThreeDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View myview= inflater.inflate( R.layout.fragment_home, container, false );

      mCatOneDatabase= FirebaseDatabase.getInstance().getReference().child( "CatOne" );
      mCatTwoDatabase=FirebaseDatabase.getInstance().getReference().child( "CatTwo" );
      mCatThreeDatabase=FirebaseDatabase.getInstance().getReference().child( "CatThree" );

      home1recycler=myview.findViewById( R.id.recycler_home1 );
        LinearLayoutManager layoutManager=new LinearLayoutManager( getActivity(),LinearLayoutManager.HORIZONTAL,false );
        layoutManager.setReverseLayout( true );
        layoutManager.setStackFromEnd( true );
        home1recycler.setHasFixedSize( true );
        home1recycler.setLayoutManager( layoutManager );

      home2recycler=myview.findViewById( R.id.recycler_home2 );
        LinearLayoutManager layoutManager2=new LinearLayoutManager( getActivity(),LinearLayoutManager.HORIZONTAL,false );
        layoutManager2.setReverseLayout( true );
        layoutManager2.setStackFromEnd( true );
        home2recycler.setHasFixedSize( true );
        home2recycler.setLayoutManager( layoutManager2 );


        home3recycler=myview.findViewById( R.id.recycler_home3 );
        LinearLayoutManager layoutManager3=new LinearLayoutManager( getActivity(),LinearLayoutManager.HORIZONTAL,false );
        layoutManager3.setReverseLayout( true );
        layoutManager3.setStackFromEnd( true );
        home3recycler.setHasFixedSize( true );
        home3recycler.setLayoutManager( layoutManager3);

        return myview;

    }
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Model,CatOneViewHolder> adapter1=new FirebaseRecyclerAdapter<Model, CatOneViewHolder>(
                Model.class,
                R.layout.menu,
                CatOneViewHolder.class,
                mCatOneDatabase
        ) {
            @Override
            protected void populateViewHolder(CatOneViewHolder catOneViewHolder, final Model model, int i) {
                catOneViewHolder.setTitle( model.getTitle() );
                catOneViewHolder.setImage( model.getImage() );
                catOneViewHolder.myview.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent( getActivity(),CatOneDetails.class );
                        intent.putExtra( "title",model.getTitle() );
                        intent.putExtra( "prize",model.getPrize() );
                        intent.putExtra( "image",model.getImage() );
                        intent.putExtra( "pid",model.getPid() );
                        intent.putExtra( "quantity",model.getQuantity() );

                        startActivity( intent );
                    }
                } );

            }
        };
        home1recycler.setAdapter(  adapter1);
        FirebaseRecyclerAdapter<Model,CatTwoViewHolder> adapter2=new FirebaseRecyclerAdapter<Model, CatTwoViewHolder>(
                Model.class,
                R.layout.menu,
                CatTwoViewHolder.class,
                mCatTwoDatabase
        ) {
            @Override
            protected void populateViewHolder(CatTwoViewHolder catTwoViewHolder, final Model model, int i) {
                catTwoViewHolder.setTitletwo( model.getTitle() );
                catTwoViewHolder.setImageTwo( model.getImage() );
                catTwoViewHolder.view.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent( getActivity(),CatTwoDetails.class );
                        intent.putExtra( "title",model.getTitle() );
                        intent.putExtra( "prize",model.getPrize() );
                        intent.putExtra( "image",model.getImage() );
                        intent.putExtra( "pid",model.getPid() );
                        intent.putExtra( "quantity",model.getQuantity() );

                        startActivity( intent );
                    }
                } );
            }
        };
        home2recycler.setAdapter( adapter2 );
        FirebaseRecyclerAdapter<Model,CatThreeViewHolder> adapter3=new FirebaseRecyclerAdapter<Model, CatThreeViewHolder>(
                Model.class,
                R.layout.menu,
                CatThreeViewHolder.class,
                mCatThreeDatabase
        ) {
            @Override
            protected void populateViewHolder(CatThreeViewHolder catThreeViewHolder, final Model model, int i) {
                catThreeViewHolder.setTitlethree( model.getTitle() );
                catThreeViewHolder.setImageThree( model.getImage() );
                catThreeViewHolder.mview.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent( getActivity(),CatThreeDetails.class );
                        intent.putExtra( "title",model.getTitle() );
                        intent.putExtra( "prize",model.getPrize() );
                        intent.putExtra( "image",model.getImage() );
                        intent.putExtra( "pid",model.getPid() );
                        intent.putExtra( "quantity",model.getQuantity() );

                        startActivity( intent );
                    }
                } );
            }
        };
        home3recycler.setAdapter( adapter3 );
    }
    public  static  class CatOneViewHolder extends  RecyclerView.ViewHolder{
View myview;
        public CatOneViewHolder(@NonNull View itemView) {
            super( itemView );
            myview=itemView;
        }
        public  void  setTitle(String title){
            TextView mtitle=myview.findViewById( R.id.title );
            mtitle.setText( title );
        }

        public  void setImage(final String image){
             final ImageView mimage=myview.findViewById( R.id.image );
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

    public static class CatTwoViewHolder extends  RecyclerView.ViewHolder{
        View view;
        public CatTwoViewHolder(@NonNull View itemView) {
            super( itemView );
            view=itemView;
        }
        public void setTitletwo(String title){
            TextView mtitle=view.findViewById( R.id.title );
            mtitle.setText( title );
        }
        public void setImageTwo(final String image){
            final ImageView mimageView=view.findViewById( R.id.image );
            Picasso.get().load( image ).networkPolicy( NetworkPolicy.OFFLINE ).into( mimageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
Picasso.get().load( image ).into( mimageView );
                }
            } );
        }

}
public  static class  CatThreeViewHolder extends  RecyclerView.ViewHolder{
    View mview;
    public CatThreeViewHolder(@NonNull View itemView) {
        super( itemView );
        mview=itemView;
    }
    public  void  setTitlethree(String title)
    {
        TextView mtitle=mview.findViewById( R.id.title );
        mtitle.setText( title );
    }
    public  void setImageThree(final String image)
    {
        final ImageView  imageView=mview.findViewById( R.id.image );
        Picasso.get().load( image ).networkPolicy( NetworkPolicy.OFFLINE ).into( imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
Picasso.get().load( image ).into( imageView );
            }
        } );

    }


}
}
