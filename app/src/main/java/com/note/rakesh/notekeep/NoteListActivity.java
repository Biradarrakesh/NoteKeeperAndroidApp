package com.note.rakesh.notekeep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this,NoteActivity.class));
            }
        });
        initializeDisplayContent();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        arrayAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listView=(ListView) findViewById(R.id.list_notes);
        List<NoteInfo> noteInfos=DataManager.getInstance().getNotes();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,noteInfos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NoteListActivity.this,NoteActivity.class);
//                NoteInfo note=(NoteInfo)listView.getItemAtPosition(i);
                intent.putExtra(NoteActivity.NOTE_POSITION,i);

                startActivity(intent);

            }
        });
    }

}
