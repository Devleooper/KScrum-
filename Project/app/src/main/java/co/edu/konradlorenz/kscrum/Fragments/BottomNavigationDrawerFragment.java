package co.edu.konradlorenz.kscrum.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.edu.konradlorenz.kscrum.Activities.LoginActivity;
import co.edu.konradlorenz.kscrum.R;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {

    private View view;
    private ImageView closeButton;
    private NavigationView navigationView;
    private GoogleSignInClient googleSignInClient;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bottom_navigation_drawer, container, false);

        findMaterialElements();
        menuItemsHandler();
        closeSheetHandler();

        return view;
    }

    public void menuItemsHandler(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_logout_option:
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();




                        Intent newIntent = new Intent(getContext(), LoginActivity.class);
                        newIntent.putExtra("LogOut", "logout");
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newIntent);

                        return true;
                }
                return true;
            }
        });
    }

    public void closeSheetHandler(){
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss bottom sheet
                dismiss();
            }
        });
    }

    public void findMaterialElements(){
        navigationView = view.findViewById(R.id.navigation_view);
        closeButton = view.findViewById(R.id.close_menu_sheet);
    }
}
