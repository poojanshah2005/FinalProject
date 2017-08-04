package com.example.shahp.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.example.shahp.finalproject.Fragment.DisplayDrinkFragment;
import com.example.shahp.finalproject.Fragment.DrinksFragment;
import com.example.shahp.finalproject.MVP.DisplayDrinks;
import com.example.shahp.finalproject.MVP.IMusicListPresenter;
import com.example.shahp.finalproject.MVP.IMusicListView;
import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.categoryList.Category;
import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.drinksResult.Drink;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.Glass;
import com.example.shahp.finalproject.Models.glassList.GlassResults;
import com.example.shahp.finalproject.Models.ingredientResults.Ingredient;
import com.example.shahp.finalproject.Models.ingredientResults.IngredientResults;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMusicListView {

    Menu menu;
    ProgressBar progressBar;
    static InteractorImpl interactor_ = new InteractorImpl();
    static IMusicListPresenter iMusicListPresenter;
    IMusicListView iMusicListView;
    static android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.iMusicListView = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        interactor_ = new InteractorImpl();
        iMusicListPresenter = new DisplayDrinks(interactor_);
        iMusicListPresenter.attachView(this);


        interactor_.getCategoryList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessgGetCategoryList, MainActivity.this::OnError);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                interactor_.getCategoryList()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.newThread())
//                        .subscribe(MainActivity.this::onSuccess, MainActivity.this::OnError);
//
//                interactor_.getByCategory("Cocktail")
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.newThread())
//                        .subscribe(MainActivity.this::onSuccess, MainActivity.this::OnError);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

                drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void onSuccessgGetCategoryList(CategoryResults categoryResults) {

        progressBar.setVisibility(View.VISIBLE);

        SubMenu CategoriesMenu = menu.addSubMenu("Categories");

        progressBar.setMax(categoryResults.getCategories().size());
        int i = 1;

        for(Category c: categoryResults.getCategories()){
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            Log.i("Progress", String.valueOf(progressBar.getProgress()) + "/ " + progressBar.getMax());
            CategoriesMenu.add(c.getStrCategory()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    displayResults(c.getStrCategory());
                    Answers.getInstance().logCustom(new CustomEvent("Category Selected")
                            .putCustomAttribute("Drink", c.getStrCategory()));
//                    interactor_.getByCategory(c.getStrCategory())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribeOn(Schedulers.newThread())
//                        .subscribe(MainActivity.this::onDisplayCategoryListSuccess, MainActivity.this::OnError);
                    onDisplayCategoryList(c.getStrCategory());
                    return false;
                }
            });
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void onDisplayCategoryList(String strCategory) {
        interactor_.getByCategory(strCategory).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                        .subscribe(MainActivity.this::onDisplayCategoryListSuccess, MainActivity.this::OnError);
    }



    private void displayResults(String value) {
        iMusicListPresenter.attachView(iMusicListView);
        iMusicListPresenter.performListDisplay(value);

    }

    private void onDisplayCategoryListSuccess(DrinksResult drinksResult) {
        Bundle args = new Bundle();
        args.putParcelable("drinksResult", drinksResult);
        DrinksFragment drinksFragment = new DrinksFragment ();
        drinksFragment.onAttach(MainActivity.this);
        drinksFragment.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, drinksFragment)
                .addToBackStack(drinksFragment.getClass().getName())
                .commit();
    }

    private void OnError(Throwable throwable) {
        Log.i("OnError", throwable.getMessage());
        progressBar.setVisibility(View.INVISIBLE);
    }
    


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /*
Handle navigation view item clicks here.
int id = item.getItemId();

if (id == R.id.nav_camera) {
// Handle the camera action
} else if (id == R.id.nav_gallery) {

} else if (id == R.id.nav_slideshow) {

} else if (id == R.id.nav_manage) {

} else if (id == R.id.nav_share) {

} else if (id == R.id.nav_send) {

}
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFetchDataSuccess(DrinksResult drinksResult) {
        Log.i("onFetchDataSuccess","onFetchDataSuccess");
    }

    @Override
    public void onFetchDataFailure(Throwable throwable) {
        Log.i("onFetchDataFailure","onFetchDataFailure");
        Log.i("onFetchDataFailure",throwable.getMessage());

    }

    @Override
    public void onFetchDataCompleted() {
        Log.i("ClassTrack","onFetchDataCompleted");
    }

    @Override
    public void onFetchDataInProgress() {
        Log.i("ClassTrack","onFetchDataInProgress");
    }

    public static void displayDrink(String idDrink) {
        interactor_.getDrinkById(idDrink).enqueue(new Callback<DrinkResult>() {
            @Override
            public void onResponse(Call<DrinkResult> call, Response<DrinkResult> response) {
                DrinkResult drinkResult = response.body();
                Log.i("",drinkResult.getDrinks().get(0).getStrDrink());
                if(drinkResult.getDrinks().size() > 0) {
                    com.example.shahp.finalproject.Models.drinkResult.Drink drink = drinkResult.getDrinks().get(0);
                    Answers.getInstance().logCustom(new CustomEvent("Displaying Drink")
                            .putCustomAttribute("Drink", drink.getStrDrink()));
                    Bundle args = new Bundle();
                    args.putParcelable("drink", drink);
                    DisplayDrinkFragment displayDrinkFragment = new DisplayDrinkFragment();
                    displayDrinkFragment.setArguments(args);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_main, displayDrinkFragment)
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<DrinkResult> call, Throwable t) {
                Log.i("Onfailure",t.getMessage());

            }
        });
    }

    public static void displayDrinkByIngredient(String ingredient) {
        interactor_.getCallByIngredient(ingredient).enqueue(new Callback<DrinksResult>() {
            @Override
            public void onResponse(Call<DrinksResult> call, Response<DrinksResult> response) {
                DrinksResult drinkResult = response.body();
                if(drinkResult.getDrinks().size() > 0) {
                    Bundle args = new Bundle();
                    args.putParcelable("drinksResult", drinkResult);
                    DrinksFragment displayDrinkFragment = new DrinksFragment();
                    displayDrinkFragment.setArguments(args);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_main, displayDrinkFragment)
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<DrinksResult> call, Throwable t) {
                Log.i("Onfailure",t.getMessage());
            }
        });
    }

}
