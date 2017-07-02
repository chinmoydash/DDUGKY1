package com.example.chinmoydash.ddugky;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CandidateSignInFrag extends Fragment {


    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @BindView(R.id.candemail_si)
    EditText email;
    @BindView(R.id.candpass_si)
    EditText pass;
    View view;

    public CandidateSignInFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.frag_candidate_signin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.btncandsignin)
    void signIn() {
        String semail = email.getText().toString();
        String spass = pass.getText().toString();
        mFirebaseAuth.signInWithEmailAndPassword(semail, spass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                FirebaseUser user = authResult.getUser();
                Toast.makeText(getActivity(), "signed-in as" + user.getEmail(), Toast.LENGTH_SHORT).show();
                getActivity().finish();
                startActivity(new Intent(getActivity(), UserInfoActivity.class));

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
