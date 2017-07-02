package com.example.chinmoydash.ddugky;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chinmoydash.ddugky.firebase.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CandidateSignUpFrag extends Fragment {

    View view;
    @BindView(R.id.etcandemail_up)
    MaterialEditText email;
    @BindView(R.id.etcandpass_up)
    MaterialEditText passs;
    @BindView(R.id.etcandmob_up)
    MaterialEditText mob;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mReference;
    FragmentActivity mActivity;

    public CandidateSignUpFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.frag_candidate_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mActivity=getActivity();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mReference=mFirebaseDatabase.getReference().child("users");
    }

    @OnClick(R.id.btncandsign_up)
    void signUp() {
        final String semail = email.getText().toString();
        final String spass = passs.getText().toString();
        final String smob=mob.getText().toString();
        if (!mob.isCharactersCountValid()){
            mob.setError("Invalid Number");
            return;
        }
        mFirebaseAuth.createUserWithEmailAndPassword(semail, spass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //authResult contains user info
                FirebaseUser user = authResult.getUser();
                Toast.makeText(getActivity(), "signed in as : " + user.getEmail(), Toast.LENGTH_LONG).show();
                getActivity().finish();
                Users users=new Users(semail,spass,smob);
                startActivity(new Intent(mActivity, UserInfoActivity.class));
                mReference.push().setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("User Database Error",e.getMessage());

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}



