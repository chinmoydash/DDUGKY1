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


public class EmpSignUpFrag extends Fragment {


    View view;
    @BindView(R.id.etempname_up)
    MaterialEditText name;
    @BindView(R.id.etempmob_up)
    MaterialEditText mob;
    @BindView(R.id.emailet_up)
    MaterialEditText email;
    @BindView(R.id.etpassword)
    MaterialEditText password;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mReference;
    FragmentActivity mActivity;

    public EmpSignUpFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_emp_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            mFirebaseAuth = FirebaseAuth.getInstance();
            mActivity=getActivity();
            mFirebaseDatabase=FirebaseDatabase.getInstance();
            mReference=mFirebaseDatabase.getReference().child("employees");
        }

        @OnClick
        void signUp() {
            final String ename =  name.getText().toString();
            final String eemail = email.getText().toString();
            final String epass = password.getText().toString();
            final String emob = mob.getText().toString();
            if (!mob.isCharactersCountValid()){
                mob.setError("Invalid Number");
                return;
            }
            mFirebaseAuth.createUserWithEmailAndPassword(eemail, epass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = authResult.getUser();
                    Toast.makeText(getActivity(), "signed in as : " + user.getEmail(), Toast.LENGTH_LONG).show();
                    getActivity().finish();
                    Users users=new Users(eemail,epass,emob);
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

