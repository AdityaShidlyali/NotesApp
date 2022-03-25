package com.adityashidlyali.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityashidlyali.notes.models.Note;
import com.adityashidlyali.notes.adapters.NoteAdapter;
import com.adityashidlyali.notes.viewmodels.NoteViewModel;
import com.adityashidlyali.notes.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    private ActivityResultLauncher<Intent> noteAddIntent;

    private ActivityResultLauncher<Intent> noteEditIntent;

    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_notes);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAddNote = findViewById(R.id.fab_add_note);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                noteAddIntent.launch(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);

                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());

                noteEditIntent.launch(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        noteAddIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AddEditNoteActivity.RESULT_OK) {

                            Intent data = result.getData();

                            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
                            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
                            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

                            Note note = new Note(title, description, priority);
                            noteViewModel.insert(note);

                            Toast.makeText(MainActivity.this, "Note saved", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Note not saved", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        noteEditIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AddEditNoteActivity.RESULT_OK) {

                            Intent data = result.getData();

                            int noteId = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

                            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
                            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
                            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

                            Note note = new Note(title, description, priority);
                            note.setId(noteId);
                            noteViewModel.update(note);

                            Toast.makeText(MainActivity.this, "Note updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Note not saved", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}