package com.learning_application.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.learning_application.R;
import com.learning_application.model.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>  {
    ArrayList<Subject> subjects;
    Context context;
    NavController navController;

    public SubjectAdapter(ArrayList<Subject> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.subject_item, parent, false);
        Button  saveEdit, cancelEdit;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

                    holder.subjectImage.setImageResource(R.drawable.ic_launcher_background);
                    holder.subjectTitle.setText(String.valueOf(subjects.get(position).getName()));

                    String subject_name=subjects.get(position).getName();
        Bundle bundle=new Bundle();
        bundle.putString("subject_name",subject_name);
                    holder.mView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_subjects_to_chapterFragment,bundle));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView subjectTitle;
        ImageView subjectImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            subjectTitle = (TextView) itemView.findViewById(R.id.subject_tv);
            subjectImage = (ImageView) itemView.findViewById(R.id.subject_img);


        }
    }
}
