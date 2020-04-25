package com.learning_application;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learning_application.model.Chapter;
import com.learning_application.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterFragment extends Fragment {

    private RecyclerView rv;
    public static ChapterFragment newInstance() {
        return new ChapterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_chapter, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView_chapter);
        ArrayList<Chapter> chapters=new ArrayList<Chapter>();
        if(chapters.size()<=0) {
            chapters.add(new Chapter(1,"Chapter one"));
            chapters.add(new Chapter(2,"Chapter two"));
            chapters.add(new Chapter(3,"Chapter three"));
            chapters.add(new Chapter(4,"Chapter four"));
            chapters.add(new Chapter(5,"Chapter five"));
        }
        ChapterAdapter subjectAdapter= new ChapterAdapter(chapters,getContext());
        rv.setAdapter(subjectAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String subject_name= getArguments().getString("subject_name");
        Log.e("nm",subject_name);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(subject_name);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
