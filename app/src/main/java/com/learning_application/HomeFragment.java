package com.learning_application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.learning_application.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    NavController navController;
    //CardView subjectCad;
    TextView username;
    EditText youtubeLink,pdfLink;
    Button btnUpdateYoutube,btnUpdatePdf;
    FirebaseFirestore fStore;
    String standard, subject, chapter;
    String userId;
    FirebaseAuth fAuth;
    Spinner standarddrop,subjectdrop,chapterdrop;
    ArrayList <String> standard_arraylist;
    ArrayList <String> subject_9th_arraylist,subject_10th_arraylist,
            subject_11th_arraylist,subject_12th_arraylist;
    ArrayList <String> chapter_9th_Science_arraylist,chapter_9th_Maths_arraylist,chapter_9th_SocialScience_arraylist,
            chapter_10th_Maths_arraylist,chapter_10th_Science_arraylist,chapter_10th_SocialScience_arraylist,
            chapter_11th_Physics_arraylist,chapter_11th_Chemistry_arraylist,chapter_11th_Maths_arraylist,
            chapter_12th_Physics_arraylist,chapter_12th_Chemistry_arraylist,chapter_12th_Maths_arraylist;
    ArrayAdapter<String> standard_adapter,subject_adapter,chapter_adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //subjectCad=root.findViewById(R.id.subject_cv);
        username = root.findViewById(R.id.username);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        ///------DropDown menus------------
        subjectdrop = (Spinner) root.findViewById(R.id.subjectdrop);
        standarddrop = (Spinner) root.findViewById(R.id.standarddrop);
        chapterdrop = (Spinner) root.findViewById(R.id.chapterdrop);
        youtubeLink = (EditText) root.findViewById(R.id.youtubeLink);
        pdfLink = (EditText) root.findViewById(R.id.pdfLink);
        btnUpdatePdf = (Button) root.findViewById(R.id.btnUpdatePdf);
        btnUpdateYoutube = (Button) root.findViewById(R.id.btnUpdateYoutube);


        standard_arraylist= new ArrayList<>();
        standard_arraylist.add("9th");
        standard_arraylist.add("10th");
        standard_arraylist.add("11th");
        standard_arraylist.add("12th");


        standard_adapter  = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,standard_arraylist);
        standarddrop.setAdapter(standard_adapter);
        //
        //===================Subject Spinners coding starts======

        subject_9th_arraylist = new ArrayList<>();
        subject_9th_arraylist.add("Maths");
        subject_9th_arraylist.add("Science");
        subject_9th_arraylist.add("SocialScience");

        subject_10th_arraylist = new ArrayList<>();
        subject_10th_arraylist.add("Maths");
        subject_10th_arraylist.add("Science");
        subject_10th_arraylist.add("SocialScience");

        subject_11th_arraylist = new ArrayList<>();
        subject_11th_arraylist.add("Maths");
        subject_11th_arraylist.add("Physics");
        subject_11th_arraylist.add("Chemistry");

        subject_12th_arraylist = new ArrayList<>();
        subject_12th_arraylist.add("Maths");
        subject_12th_arraylist.add("Physics");
        subject_12th_arraylist.add("Chemistry");


        //===================Subject Spinners coding ends======

        //===================Chapters Spinners coding starts======

        chapter_9th_Maths_arraylist = new ArrayList<>();
        chapter_9th_Maths_arraylist.add("9th Maths Chapter");
        chapter_9th_Maths_arraylist.add("9th Maths Chapter - 2");

        chapter_9th_Science_arraylist = new ArrayList<>();
        chapter_9th_Science_arraylist.add("9th Science Chapter");
        chapter_9th_Science_arraylist.add("9th Science Chapter-2");

        chapter_9th_SocialScience_arraylist = new ArrayList<>();
        chapter_9th_SocialScience_arraylist.add("9th Social Science Chapter");
        chapter_9th_SocialScience_arraylist.add("9th Social Science Chapter - 2");

        chapter_10th_Maths_arraylist = new ArrayList<>();
        chapter_10th_Maths_arraylist.add("10th Maths Chapter");
        chapter_10th_Maths_arraylist.add("10th Maths Chapter-2");

        chapter_10th_Science_arraylist = new ArrayList<>();
        chapter_10th_Science_arraylist.add("10th Science Chapter");
        chapter_10th_Science_arraylist.add("10th Science Chapter-2");

        chapter_10th_SocialScience_arraylist = new ArrayList<>();
        chapter_10th_SocialScience_arraylist.add("10th Social Science Chapter");
        chapter_10th_SocialScience_arraylist.add("10th Social Science Chapter-2");

        chapter_11th_Physics_arraylist = new ArrayList<>();
        chapter_11th_Physics_arraylist.add("11th_Physics Chapter");
        chapter_11th_Physics_arraylist.add("11th_Physics Chapter-2");

        chapter_11th_Chemistry_arraylist = new ArrayList<>();
        chapter_11th_Chemistry_arraylist.add("11th_Chemistry Chapter");
        chapter_11th_Chemistry_arraylist.add("11th_Chemistry Chapter-2");

        chapter_11th_Maths_arraylist = new ArrayList<>();
        chapter_11th_Maths_arraylist.add("11th Maths Chapter");
        chapter_11th_Maths_arraylist.add("11th Maths Chapter - 2");

        chapter_12th_Physics_arraylist = new ArrayList<>();
        chapter_12th_Physics_arraylist.add("12th_Physics Chapter");
        chapter_12th_Physics_arraylist.add("12th_Physics Chapter-2");

        chapter_12th_Chemistry_arraylist = new ArrayList<>();
        chapter_12th_Chemistry_arraylist.add("12th Chemistry Chapter");
        chapter_12th_Chemistry_arraylist.add("12th Chemistry Chapter-2");

        chapter_12th_Maths_arraylist = new ArrayList<>();
        chapter_12th_Maths_arraylist.add("12th Maths Chapter");
        chapter_12th_Maths_arraylist.add("12th Maths Chapter-2");


        //===================Chapters Spinners coding ends======


        standarddrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                standard = parent.getItemAtPosition(position).toString();//Fetching Standard

                switch (position){
                    case 0 : //------case 0 starts------------------------
                        subject_adapter  = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_spinner_item,subject_9th_arraylist);
                        subjectdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                subject = parent.getItemAtPosition(position).toString();//Fetching Subject

                                switch (position){
                                    case 0:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_9th_Maths_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends
                                        break;
                                    case 1:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_9th_Science_arraylist);

                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 2:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_9th_SocialScience_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    default: Log.d("tag", "Please choose first");
                                }
                                chapterdrop.setAdapter(chapter_adapter);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;
                    //------case 1 ends------------------------


                    case 1 : //------case 0 starts------------------------
                        subject_adapter  = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_spinner_item,subject_10th_arraylist);
                        subjectdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                subject = parent.getItemAtPosition(position).toString();//Fetching Subject

                                switch (position){
                                    case 0:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_10th_Maths_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 1:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_10th_Science_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 2:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_10th_SocialScience_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    default: Log.d("tag", "Please choose first");
                                }
                                chapterdrop.setAdapter(chapter_adapter);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    //------case 1 ends------------------------


                    case 2 : //------case 2 starts------------------------
                        subject_adapter  = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_spinner_item,subject_11th_arraylist);
                        subjectdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                subject = parent.getItemAtPosition(position).toString();//Fetching Subject

                                switch (position){
                                    case 0:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_11th_Maths_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 1:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_11th_Physics_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 2:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_11th_Chemistry_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    default: Log.d("tag", "Please choose first");
                                }
                                chapterdrop.setAdapter(chapter_adapter);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    //------case 2 ends------------------------


                    case 3 : //------case 3 starts------------------------
                        subject_adapter  = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_spinner_item,subject_12th_arraylist);
                        subjectdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                subject = parent.getItemAtPosition(position).toString();//Fetching Subject

                                switch (position){
                                    case 0:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_12th_Maths_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 1:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_12th_Physics_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    case 2:chapter_adapter  = new ArrayAdapter<>(getActivity(),
                                            android.R.layout.simple_spinner_item,chapter_12th_Chemistry_arraylist);
                                        ///Fetching Chapter process starts
                                        chapterdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                chapter = parent.getItemAtPosition(position).toString();//Fetching Chapter
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        ///Fetching Chapter process ends


                                        break;
                                    default: Log.d("tag", "Please choose first");
                                }
                                chapterdrop.setAdapter(chapter_adapter);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    //------case 3 ends------------------------


                    default: Log.d("tag", "Please choose first");
                }//switch ends
                subjectdrop.setAdapter(subject_adapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        //////----------------

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    username.setText(documentSnapshot.getString("fName"));

                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }


        });
        //////////////////////---------------------

        ///////--------Button update PDF Link Starts-----------

        btnUpdatePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ///-------------------Starting uploading links------------

                DocumentReference documentReference = fStore.collection(standard).document(subject);

                Map<String,Object> user = new HashMap<>();

                documentReference.set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("qwerty", "Submitted Data "+ userId);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("qwerty", "onFailure: " + e.toString());
                    }
                });
                documentReference.update(chapter+" PDF Links", FieldValue.arrayUnion(pdfLink.getText().toString()));

                ///-------------------Ends uploading links------------



            }//onclick method for pdf button ends here
        });


        ///////--------Button update PDF Link ends-----------




        ///////--------Button update You-Tube Link Starts-----------

        btnUpdateYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///-------------------Starting uploading links------------

                DocumentReference documentReference = fStore.collection(standard).document(subject);
                Map<String,Object> user = new HashMap<>();
                documentReference.set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("qwerty", "Submitted Data "+ userId);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("qwerty", "onFailure: " + e.toString());
                    }
                });
                documentReference.update(chapter+" Youtube Links", FieldValue.arrayUnion(youtubeLink.getText().toString()));

                ///-------------------Ends uploading links------------



            }//onclick method for pdf button ends here
        });


        ///////--------Button update You-Tube Link ends-----------
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    //    @Override
    //    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    //        super.onActivityCreated(savedInstanceState);
    ////        navController=Navigation.findNavController(this.getActivity(),R.id.nav_host_fragment);
    //        subjectCad.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                //action_nav_home_to_subjects
    //                navController.navigate(R.id.action_nav_home_to_subjects);
    //            }
    //        });
    //    }

    //    @Override
    //    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    //        super.onViewCreated(view, savedInstanceState);
    //        navController=Navigation.findNavController(view);
    //
    //    }
}//ends class
