package com.ske.geniusnotepad.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ske.geniusnotepad.models.Note;
import com.ske.geniusnotepad.R;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {

    public NotesAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.note_grid, null);
        }

        TextView body = (TextView) v.findViewById(R.id.body);
        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView createdTime = (TextView) v.findViewById(R.id.created_time);
        TextView tags = (TextView) v.findViewById(R.id.tags);

        subject.setText(getItem(position).getSubject());
        body.setText(getItem(position).getBody());
        createdTime.setText(getItem(position).getReadableCreatedTime());
        tags.setText(joinTags(getItem(position).getTags()));

        try {
            v.setBackgroundColor(Color.parseColor(getItem(position).getColorHex()));
            // Any error in color setting. Just fallback to default color
        } catch (Exception e) {
            v.setBackgroundColor(Color.parseColor(Note.DEFAULT_COLOR));
        }

        return v;
    }

    private String joinTags(List<String> tags) {
        String delim = "";
        String tagString = "";
        for(String s : tags) {
            tagString += (delim + " " + s);
            delim = ",";
        }
        return tagString;
    }
}
