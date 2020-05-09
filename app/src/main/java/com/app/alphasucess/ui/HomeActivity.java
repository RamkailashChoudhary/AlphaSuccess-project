package com.app.alphasucess.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.app.alphasucess.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view1);
        setupDrawerContent(navigationView);
        BottomNavigationView navView = findViewById(R.id.nav_view);

         appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard,R.id.navigation_eBook, R.id.navigation_Test,R.id.navigation_Download)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        Intent resourceView = new Intent(this,WebBaseActivity.class);;

        switch(menuItem.getItemId()) {

            case R.id.navigation_Profile:
            case R.id.nav_Orders:
            case R.id.nav_Invitefrds:
            case R.id.nav_Contactus:

                resourceView.putExtra("View-Name","Contact Us");
                startActivity(resourceView);
                break;
            case R.id.nav_Aboutus:

                resourceView.putExtra("View-Name","About Us");
                startActivity(resourceView);
                break;
            case R.id.nav_Privacypolicy:

                resourceView.putExtra("View-Name","Privacy Policy");
                startActivity(resourceView);
                break;
            case R.id.nav_termsServices:

                resourceView.putExtra("View-Name","Terms & Conditions");
                startActivity(resourceView);
                break;
            case R.id.nav_refunds:
            case R.id.nav_logout:
                break;
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
