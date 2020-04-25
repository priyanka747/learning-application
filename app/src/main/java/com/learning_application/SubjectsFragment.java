package com.learning_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learning_application.adapter.SubjectAdapter;
import com.learning_application.model.Subject;

import java.util.ArrayList;

public class SubjectsFragment extends Fragment {

//    private SubjectsViewModel viewModel;
    private RecyclerView rv;
    public static SubjectsFragment newInstance() {
        return new SubjectsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_subjects, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView_subject);
        ArrayList<Subject> subjects=new ArrayList<Subject>();
        if(subjects.size()<=0) {
            subjects.add(new Subject("Maths"));
            subjects.add(new Subject("Science"));
            subjects.add(new Subject("Social science"));
            subjects.add(new Subject("Hindi"));
            subjects.add(new Subject("English"));
        }
        SubjectAdapter subjectAdapter= new SubjectAdapter(subjects,getContext());
        rv.setAdapter(subjectAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        rv.setOnItemClickListener((adapter, itemView, pos, id) -> {
//        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
