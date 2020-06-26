package com.app.alphasucess.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.login.LoginActivity;
import com.app.alphasucess.ui.tabui.signup.UpdateFragment;
import com.app.alphasucess.utility.AlphaSharedPrefrence;
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

        View headerView = navigationView.getHeaderView(0);
        TextView headerTxt = headerView.findViewById(R.id.logedInuserName);

        headerTxt.setText("Name: "+ MyApplication.USER_NAME.toUpperCase());
         appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard,R.id.navigation_eBook, R.id.navigation_Test)
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
                UpdateFragment fragment= new UpdateFragment();
                Bundle bundle= new Bundle();
                bundle.putString("key","");
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "updateprofile");
                break;

            case R.id.nav_Invitefrds:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.app.alphasucess&hl=en");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
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
                startActivity(new Intent(HomeActivity.this, BookListActivity.class));
                break;
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);

//                builder.setBu
                builder.setMessage("Do you really want to logout?")
                        .setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AlphaSharedPrefrence.clearData();
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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
