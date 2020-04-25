package com.learning_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.learning_application.R;

public class HomeFragment extends Fragment {
    NavController navController;
    CardView subjectCad;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        subjectCad=root.findViewById(R.id.subject_cv);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        navController=Navigation.findNavController(this.getActivity(),R.id.nav_host_fragment);
        subjectCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action_nav_home_to_subjects
                navController.navigate(R.id.action_nav_home_to_subjects);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=Navigation.findNavController(view);

    }
}
