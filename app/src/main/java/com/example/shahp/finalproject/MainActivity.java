package com.example.shahp.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ProgressBar;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.example.shahp.finalproject.Fragment.Drink.DisplayDrinkFragment;
import com.example.shahp.finalproject.Fragment.Drinks.DrinksFragment;
import com.example.shahp.finalproject.MVP.Drink.DisplayDrink;
import com.example.shahp.finalproject.MVP.Drink.IDrinkPresenter;
import com.example.shahp.finalproject.MVP.Drink.IDrinkView;
import com.example.shahp.finalproject.MVP.Drinks.DisplayAlcoholicDrinks;
import com.example.shahp.finalproject.MVP.Drinks.DisplayCategoryDrinks;
import com.example.shahp.finalproject.MVP.Drinks.DisplayGlassDrinks;
import com.example.shahp.finalproject.MVP.Drinks.DisplayIngredientDrinks;
import com.example.shahp.finalproject.MVP.Drinks.IDrinksPresenter;
import com.example.shahp.finalproject.MVP.Drinks.IDrinksView;
import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.AlcoholicResult.Alcoholic;
import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.CategoryList.Category;
import com.example.shahp.finalproject.Models.CategoryList.CategoryResults;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.GlassList.Glass;
import com.example.shahp.finalproject.Models.GlassList.GlassResults;
import com.example.shahp.finalproject.Models.IngredientResults.Ingredient;
import com.example.shahp.finalproject.Models.IngredientResults.IngredientResults;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IDrinksView, IDrinkView {

    Menu menu;
    ProgressBar progressBar;
    static InteractorImpl interactor_;
    static IDrinksPresenter iDrinksPresenter;
    static IDrinkPresenter iDrinkPresenter;
    static IDrinksView iDrinksView;
    static IDrinkView iDrinkView;
    static android.support.v4.app.FragmentManager fragmentManager;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interactor_ = new InteractorImpl(getApplicationContext());
        this.iDrinksView = this;
        this.iDrinkView = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        iDrinksPresenter = new DisplayCategoryDrinks(interactor_);
        iDrinksPresenter.attachView(this);


        interactor_.getCategoryList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessgGetCategoryList, MainActivity.this::OnError);

        interactor_.getAlcoholicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessgGetAlcoholicList, MainActivity.this::OnError);

        interactor_.getGlassList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessgGetGlassList, MainActivity.this::OnError);

        interactor_.getIngredientList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessgGetIngredientList, MainActivity.this::OnError);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

                drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        displayDrinkByCategory("Ordinary Drink");

    }

    private void onSuccessgGetIngredientList(IngredientResults ingredientResults) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu ingredientMenu = menu.addSubMenu("Ingredients");
        ingredientMenu.setHeaderTitle("Ingredients");
        progressBar.setMax(ingredientResults.getIngredients().size());
        int i = 1;

        for(Ingredient ingredient: ingredientResults.getIngredients()){
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            ingredientMenu.add(ingredient.getStrIngredient1()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    Answers.getInstance().logCustom(new CustomEvent("Ingredient Selected")
                            .putCustomAttribute("Ingredient",ingredient.getStrIngredient1()));
                    iDrinksPresenter =  new DisplayIngredientDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(ingredient.getStrIngredient1());
                    return false;
                }
            });
        }
        progressBar.setVisibility(View.INVISIBLE);
    }


    private void onSuccessgGetGlassList(GlassResults glassResults) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu glassMenu = menu.addSubMenu("Glass");
        glassMenu.setHeaderTitle("Glass");
        progressBar.setMax(glassResults.getGlass().size());
        int i = 1;
        for(Glass g: glassResults.getGlass()){
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            glassMenu.add(g.getStrGlass()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Answers.getInstance().logCustom(new CustomEvent("Glass Selected")
                            .putCustomAttribute("Glass",g.getStrGlass()));
                    iDrinksPresenter =  new DisplayGlassDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(g.getStrGlass());
                    return false;
                }
            });
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void onSuccessgGetAlcoholicList(AlcoholicResult alcoholicResult) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu alcoholicMenu = menu.addSubMenu("Alcoholic");
        alcoholicMenu.setHeaderTitle("Alcoholic");
        progressBar.setMax(alcoholicResult.getAlcoholics().size());
        int i = 1;
        Log.i("alcoholicResult", String.valueOf(alcoholicResult.getAlcoholics().size()));
        for(Alcoholic a: alcoholicResult.getAlcoholics()) {
            Log.i("alcoholicResult", a.getStrAlcoholic().toString() + " " + String.valueOf(a.getStrAlcoholic().isEmpty()));
            Log.i("alcoholicResult", String.valueOf(a.getStrAlcoholic().isEmpty()));
            if (!a.getStrAlcoholic().isEmpty()) {
                progressBar.setProgress(i);
                progressBar.setSecondaryProgress(i);
                i++;
                alcoholicMenu.add(a.getStrAlcoholic()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Answers.getInstance().logCustom(new CustomEvent("Alcoholic Selected")
                                .putCustomAttribute("Alcoholic", a.getStrAlcoholic()));
                        iDrinksPresenter = new DisplayAlcoholicDrinks(interactor_);
                        iDrinksPresenter.attachView(iDrinksView);
                        iDrinksPresenter.performListDisplay(a.getStrAlcoholic());
                        return false;
                    }
                });
            }
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void onSuccessgGetCategoryList(CategoryResults categoryResults) {

        progressBar.setVisibility(View.VISIBLE);
        SubMenu categoriesMenu = menu.addSubMenu("Categories");
        categoriesMenu.setHeaderTitle("Categories");
        progressBar.setMax(categoryResults.getCategories().size());
        int i = 1;

        for(Category c: categoryResults.getCategories()){
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            categoriesMenu.add(c.getStrCategory()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    iDrinksPresenter =  new DisplayCategoryDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(c.getStrCategory());
                    Answers.getInstance().logCustom(new CustomEvent("Category Selected")
                            .putCustomAttribute("Category", c.getStrCategory()));
                    return false;
                }
            });
        }

        progressBar.setVisibility(View.INVISIBLE);
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
//            super.onBackPressed();
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
        Bundle args = new Bundle();
        args.putParcelable("drinksResult", drinksResult);
        DrinksFragment drinksFragment = new DrinksFragment ();
        drinksFragment.onAttach(MainActivity.this);
        drinksFragment.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, drinksFragment)
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onFetchDataSuccess(DrinkResult drinkResult) {

            com.example.shahp.finalproject.Models.DrinkResult.Drink drink = drinkResult.getDrinks().get(0);
            Answers.getInstance().logCustom(new CustomEvent("Displaying Drink")
                    .putCustomAttribute("Drink", drink.getStrDrink()));
            Bundle args = new Bundle();
            args.putParcelable("drink", drink);
            DisplayDrinkFragment displayDrinkFragment = new DisplayDrinkFragment();
            displayDrinkFragment.setArguments(args);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_main, displayDrinkFragment)
                    .disallowAddToBackStack()
                    .commit();

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
        iDrinkPresenter =  new DisplayDrink(interactor_);
        iDrinkPresenter.attachView(iDrinkView);
        iDrinkPresenter.performDrinkDisplay(idDrink);
    }

    public static void displayDrinkByIngredient(String ingredient) {
        Log.i("DISPLAYDRINKS",ingredient);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByIngredient")
                .putCustomAttribute("Ingredient", ingredient));
        iDrinksPresenter =  new DisplayIngredientDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(ingredient);
    }

    public static void displayDrinkByAlcoholic(String alcoholic) {
        Log.i("DISPLAYDRINKS",alcoholic);
        iDrinksPresenter =  new DisplayAlcoholicDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(alcoholic);
    }

    public static void displayDrinkByCategory(String category) {
        Log.i("DISPLAYDRINKS",category);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByCategory")
                .putCustomAttribute("Category", category));
        iDrinksPresenter =  new DisplayCategoryDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(category);
    }

    public static void displayDrinkByGlass(String glass) {
        Log.i("DISPLAYDRINKS",glass);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByGlass")
                .putCustomAttribute("Glass", glass));
        iDrinksPresenter =  new DisplayGlassDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(glass);
    }



}
