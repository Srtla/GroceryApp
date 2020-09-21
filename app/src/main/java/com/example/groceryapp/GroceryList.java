package com.example.groceryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class GroceryList extends AppCompatActivity {

    private String m_Text1 = "";
    private String m_Text2 = "";
    ListView listView1;
    ArrayList<String> supplierNames1 = new ArrayList<String>();
    ArrayList<String> gItems = new ArrayList<String>();
    ArrayList<Integer> highlightedItem = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        Intent intent = getIntent();
        String fname = intent.getStringExtra("name");

        //Create file
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        if (!root.exists()) {
            root.mkdirs();
        }
        final File gpxfile = new File(root, fname);
        if (gpxfile.exists()) {
            //Read text from file
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(gpxfile));
                String line;

                while ((line = br.readLine()) != null) {
                    supplierNames1.add(line);
                }
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        listView1=(ListView)findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, supplierNames1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroceryList.this);
                builder.setTitle("Item Options");
                builder.setMessage("What would you like to do?");
                final int positionToRemove = position;
                final View viewToHighlight = view;

                // add the buttons
                builder.setPositiveButton("Mark As Bought", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewToHighlight.setBackgroundColor(getColor(R.color.colorHighlight));
                        highlightedItem.add(positionToRemove);
                    }
                });
                builder.setNegativeButton("Delete Item", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) { ;
                        supplierNames1.remove(positionToRemove);

                        for (int i = 0; i < supplierNames1.size(); i++) {
                            try {
                                FileOutputStream fOut = new FileOutputStream(gpxfile, false);
                                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                                osw.write(supplierNames1.get(i)+"\n");
                                osw.flush();
                                osw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if(highlightedItem.get(0) != 0 && positionToRemove == highlightedItem.get(0)-1){
                            listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                            listView1.setItemChecked(positionToRemove, true);
                            viewToHighlight.setBackgroundColor(getColor(R.color.colorHighlight));

                            listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                            listView1.setItemChecked(positionToRemove+1, true);
                            viewToHighlight.setBackgroundColor(getColor(R.color.restoreHighlight));
                        }else if(highlightedItem.get(0) == positionToRemove){
                            listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                            listView1.setItemChecked(highlightedItem.get(0), true);
                            viewToHighlight.setBackgroundColor(getColor(R.color.restoreHighlight));
                            highlightedItem.remove(0);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.listviewbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroceryList.this);
                LayoutInflater inflater = LayoutInflater.from(GroceryList.this);
                builder.setTitle("Enter grocery Item");
                adapter.notifyDataSetChanged();

                final EditText input1 = new EditText(GroceryList.this);
                final EditText input2 = new EditText(GroceryList.this);
                input1.setInputType(InputType.TYPE_CLASS_TEXT);
                input2.setInputType(InputType.TYPE_CLASS_NUMBER);
                input1.setHint("Item");
                input2.setHint("Quantity");
                input2.setText("1");
                LinearLayout ll=new LinearLayout(GroceryList.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(input1);
                ll.addView(input2);
                builder.setView(ll);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text1 = input1.getText().toString();
                        m_Text2 = input2.getText().toString();

                        if(gItems.contains(m_Text1.toLowerCase())){
                            Toast.makeText(GroceryList.this, "Item Already Exists!",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            StringBuilder str1 = new StringBuilder();
                            gItems.add(m_Text1.toLowerCase());
                            for (int j = m_Text1.length(); j < 72 - m_Text1.length(); j++) {
                                str1.append(" ");
                            }
                            supplierNames1.add(m_Text1 + str1 + "| " + m_Text2);

                            try {
                                FileOutputStream fOut = new FileOutputStream(gpxfile, true);
                                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                                osw.write(m_Text1 + "                                    " +
                                        "                                    | " + m_Text2 + "\n");
                                osw.flush();
                                osw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
    }
}
