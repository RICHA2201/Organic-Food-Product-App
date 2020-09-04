package com.example.bigbasket;


import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


  List<String> list;
  ArrayAdapter<String> arrayAdapter;
EditText enter;
Button add;
ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

list=new ArrayList<>(  );
arrayAdapter=new ArrayAdapter<>( getActivity(),R.layout.listlayout,list );
        final View myview= inflater.inflate( R.layout.fragment_search, container, false );
        listView=myview.findViewById( R.id.list );
        listView.setAdapter( arrayAdapter );
        enter=myview.findViewById( R.id.enter );
        add=myview.findViewById( R.id.addlist );

        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddItem(v);
            }
        } );


        setupListViewListener();






        return  myview;
    }

    public void onAddItem(View v) {
        
        String itemText = enter.getText().toString();
      list.add(itemText);

       enter.setText("");

    }

    private void setupListViewListener() {
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        list.remove(pos);
                        // Refresh the adapter
                       arrayAdapter.notifyDataSetChanged();

                        return true;
                    }

                });
    }
}
