package br.com.bangu_ao_vivo.bangu.Helper;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Connection {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    private Connection() {
    }

    public static FirebaseAuth getFirebaseAuth() {

        if (firebaseAuth == null) {
            initializeFirebase();
        }

        return firebaseAuth;
    }

    private static void initializeFirebase() {

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    public static FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public static void logOut () {
        firebaseAuth.signOut();
    }
}


