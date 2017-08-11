package com.example.shahp.finalproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
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
import com.example.shahp.finalproject.Fragment.Offline.DrinksOfflineFragment;
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
import com.example.shahp.finalproject.MVP.OfflineDrinks.DrinksOffline;
import com.example.shahp.finalproject.MVP.OfflineDrinks.IDrinksPresenterOffline;
import com.example.shahp.finalproject.MVP.OfflineDrinks.IDrinksViewOffline;
import com.example.shahp.finalproject.MVP.Service.LoadData;
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

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * MainActivity where presenters are called from
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IDrinksView, IDrinkView, IDrinksViewOffline {

    static Menu menu;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    static InteractorImpl interactor_;
    static IDrinksPresenter iDrinksPresenter;
    static IDrinkPresenter iDrinkPresenter;
    static IDrinksPresenterOffline iDrinksPresenterOffline;
    static IDrinksView iDrinksView;
    static IDrinkView iDrinkView;
    static IDrinksViewOffline iDrinksViewOffline;
    static android.support.v4.app.FragmentManager fragmentManager;
    LoadData loadData;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    static Context context;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Creating the menu and population them and inflating the view
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        interactor_ = new InteractorImpl(getApplicationContext());
        this.iDrinksView = this;
        this.iDrinkView = this;
        this.iDrinksViewOffline = this;
        this.context = getApplicationContext();

        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        iDrinksPresenter = new DisplayCategoryDrinks(interactor_);
        iDrinksPresenter.attachView(this);


        interactor_.getCategoryList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessGetCategoryList, MainActivity.this::OnError);

        interactor_.getAlcoholicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessGetAlcoholicList, MainActivity.this::OnError);

        interactor_.getGlassList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessGetGlassList, MainActivity.this::OnError);

        interactor_.getIngredientList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(MainActivity.this::onSuccessGetIngredientList, MainActivity.this::OnError);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);


        menuOfflineDrinks();


        showHome();




//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                // run AsyncTask here.
//                loadData = new LoadData(getApplicationContext());
//                loadData.execute();
//
//
//            }
//        }, 3000);// 3 seconds delay




    }

    /**
     * displaying init home page
     */
    private void showHome() {
        displayDrinksOffline();
        displayDrinkByCategory("Ordinary Drink");

    }

    /**
     * displaying init home page, when user selects delete from realm
     */
    public static void showHomestatic() {
        displayDrinksOffline();
//        if(isNetworkAvailable(context)){
        displayDrinkByCategory("Ordinary Drink");
//        } else{
//            displayDrinksOffline();
//        }
    }

    /**
     * init offline menu
     */
    private void menuOfflineDrinks() {
        menu.add("Offline Drinks").setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(menuItem -> {
            Answers.getInstance().logCustom(new CustomEvent("Offline Drinks")
                    .putCustomAttribute("Offline Drinks", "Offline Drinks"));
            displayDrinksOffline();
            return false;
        });
    }

    /**
     * init Ingredient Menu
     *
     * @param ingredientResults
     */
    private void onSuccessGetIngredientList(IngredientResults ingredientResults) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu ingredientMenu = menu.addSubMenu("Ingredients");
        ingredientMenu.setHeaderTitle("Ingredients");
        progressBar.setMax(ingredientResults.getIngredients().size());
        int i = 1;

        for (Ingredient ingredient : ingredientResults.getIngredients()) {
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            ingredientMenu.add(ingredient.getStrIngredient1()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    Answers.getInstance().logCustom(new CustomEvent("Ingredient Selected")
                            .putCustomAttribute("Ingredient", ingredient.getStrIngredient1()));
                    iDrinksPresenter = new DisplayIngredientDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(ingredient.getStrIngredient1());
                    return false;
                }
            });
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * init Glass Menu
     *
     * @param glassResults
     */
    private void onSuccessGetGlassList(GlassResults glassResults) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu glassMenu = menu.addSubMenu("Glass");
        glassMenu.setHeaderTitle("Glass");
        progressBar.setMax(glassResults.getGlass().size());
        int i = 1;
        for (Glass g : glassResults.getGlass()) {
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            glassMenu.add(g.getStrGlass()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Answers.getInstance().logCustom(new CustomEvent("Glass Selected")
                            .putCustomAttribute("Glass", g.getStrGlass()));
                    iDrinksPresenter = new DisplayGlassDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(g.getStrGlass());
                    return false;
                }
            });
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * init Alcoholic Menu
     *
     * @param alcoholicResult
     */
    private void onSuccessGetAlcoholicList(AlcoholicResult alcoholicResult) {
        progressBar.setVisibility(View.VISIBLE);
        SubMenu alcoholicMenu = menu.addSubMenu("Alcoholic");
        alcoholicMenu.setHeaderTitle("Alcoholic");
        progressBar.setMax(alcoholicResult.getAlcoholics().size());
        int i = 1;
        Log.i("alcoholicResult", String.valueOf(alcoholicResult.getAlcoholics().size()));
        for (Alcoholic a : alcoholicResult.getAlcoholics()) {
            Log.i("alcoholicResult", a.getStrAlcoholic().toString() + " " + String.valueOf(a.getStrAlcoholic().isEmpty()));
            Log.i("alcoholicResult", String.valueOf(a.getStrAlcoholic().isEmpty()));
            if (!a.getStrAlcoholic().isEmpty()) {
                progressBar.setProgress(i);
                progressBar.setSecondaryProgress(i);
                i++;
                alcoholicMenu.add(a.getStrAlcoholic()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(menuItem -> {
                    Answers.getInstance().logCustom(new CustomEvent("Alcoholic Selected")
                            .putCustomAttribute("Alcoholic", a.getStrAlcoholic()));
                    iDrinksPresenter = new DisplayAlcoholicDrinks(interactor_);
                    iDrinksPresenter.attachView(iDrinksView);
                    iDrinksPresenter.performListDisplay(a.getStrAlcoholic());
                    return false;
                });
            }
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * init Category Menu
     *
     * @param categoryResults
     */
    private void onSuccessGetCategoryList(CategoryResults categoryResults) {

        progressBar.setVisibility(View.VISIBLE);
        SubMenu categoriesMenu = menu.addSubMenu("Categories");
        categoriesMenu.setHeaderTitle("Categories");
        progressBar.setMax(categoryResults.getCategories().size());
        int i = 1;

        for (Category c : categoryResults.getCategories()) {
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i);
            i++;
            categoriesMenu.add(c.getStrCategory()).setIcon(R.drawable.ic_local_drink_48px).setOnMenuItemClickListener(menuItem -> {
                iDrinksPresenter = new DisplayCategoryDrinks(interactor_);
                iDrinksPresenter.attachView(iDrinksView);
                iDrinksPresenter.performListDisplay(c.getStrCategory());
                Answers.getInstance().logCustom(new CustomEvent("Category Selected")
                        .putCustomAttribute("Category", c.getStrCategory()));
                return false;
            });
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * on error when database not come thought
     *
     * @param throwable
     */
    private void OnError(Throwable throwable) {
        Log.i("OnError", throwable.getMessage());
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * prevent exit application when clicking back
     */
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }

    /**
     * is required to be override
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * is required to be override
     *
     * @param item
     * @return
     */
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

    /**
     * is required to be override
     *
     * @param item
     * @return
     */
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
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * getting drinksResult and display results
     *
     * @param drinksResult list of drinks
     */
    @Override
    public void onFetchDataSuccess(DrinksResult drinksResult) {
        Log.i("onFetchDataSuccess", "onFetchDataSuccess");
        Bundle args = new Bundle();
        args.putParcelable("drinksResult", drinksResult);
        DrinksFragment drinksFragment = new DrinksFragment();
        drinksFragment.onAttach(MainActivity.this);
        drinksFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);


        transaction.replace(R.id.content_main, drinksFragment)
                .disallowAddToBackStack()
                .commit();
    }

    /**
     * getting Drink and display results
     *
     * @param drinkResult list of drinks
     */
    @Override
    public void onFetchDataSuccess(DrinkResult drinkResult) {

        progressBar.setVisibility(View.VISIBLE);

        com.example.shahp.finalproject.Models.DrinkResult.Drink drink = drinkResult.getDrinks().get(0);

        Answers.getInstance().logCustom(new CustomEvent("Displaying Drink")
                .putCustomAttribute("Drink", drink.getStrDrink()));

        Bundle args = new Bundle();
        args.putParcelable("drink", drink);
        DisplayDrinkFragment displayDrinkFragment = new DisplayDrinkFragment();
        displayDrinkFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        transaction.replace(R.id.content_main, displayDrinkFragment)
                .disallowAddToBackStack()
                .commit();

        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * displaying offline drinks to user
     */
    @Override
    public void onFetchDataSuccess() {
//        Answers.getInstance().logCustom(new CustomEvent("Displaying Drink")
//                .putCustomAttribute("Drink", drink.getStrDrink()));
        Bundle args = new Bundle();
        DrinksOfflineFragment drinksOfflineFragment = new DrinksOfflineFragment();
        drinksOfflineFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        transaction.replace(R.id.content_main, drinksOfflineFragment)
                .disallowAddToBackStack()
                .commit();
    }

    /**
     * shows errors
     *
     * @param throwable error
     */
    @Override
    public void onFetchDataFailure(Throwable throwable) {
        Log.i("onFetchDataFailure", "onFetchDataFailure");
        Log.i("onFetchDataFailure", throwable.getMessage());

    }

    /**
     * just got these methods due to interface.
     */
    @Override
    public void onFetchDataCompleted() {
        Log.i("ClassTrack", "onFetchDataCompleted");
    }

    /**
     * just got these methods due to interface.
     */
    @Override
    public void onFetchDataInProgress() {
        Log.i("ClassTrack", "onFetchDataInProgress");
    }

    /**
     * displaying a drink
     */
    public static void displayDrink(String idDrink) {
        iDrinkPresenter = new DisplayDrink(interactor_);
        iDrinkPresenter.attachView(iDrinkView);
        iDrinkPresenter.performDrinkDisplay(idDrink);
    }

    /**
     * displaying drink offline
     *
     * @param idDrink Drink's id
     */
    public static void displayDrinkOffline(String idDrink) {
        DisplayDrinkFragment displayDrinkFragment = new DisplayDrinkFragment();
        Bundle args = new Bundle();
        args.putString("drinkID", idDrink);
        displayDrinkFragment.setArguments(args);

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        transaction.replace(R.id.content_main, displayDrinkFragment)
                .disallowAddToBackStack()
                .commit();
    }

    /**
     * displaying a drink By Ingredient
     */
    public static void displayDrinkByIngredient(String ingredient) {
        Log.i("DISPLAYDRINKS", ingredient);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByIngredient")
                .putCustomAttribute("Ingredient", ingredient));
        iDrinksPresenter = new DisplayIngredientDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(ingredient);
    }

    /**
     * displaying a drink By Alcoholic
     */
    public static void displayDrinkByAlcoholic(String alcoholic) {
        Log.i("DISPLAYDRINKS", alcoholic);
        iDrinksPresenter = new DisplayAlcoholicDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(alcoholic);
    }

    /**
     * displaying a drinks offline
     */
    public static void displayDrinksOffline() {
        iDrinksPresenterOffline = new DrinksOffline();
        iDrinksPresenterOffline.attachView(iDrinksViewOffline);
        iDrinksPresenterOffline.performListDisplay();
    }

    /**
     * displaying a drink By Category
     */
    public static void displayDrinkByCategory(String category) {
        Log.i("DISPLAYDRINKS", category);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByCategory")
                .putCustomAttribute("Category", category));
        iDrinksPresenter = new DisplayCategoryDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(category);
    }

    /**
     * displaying a drink By Glass
     */
    public static void displayDrinkByGlass(String glass) {
        Log.i("DISPLAYDRINKS", glass);
        Answers.getInstance().logCustom(new CustomEvent("displayDrinkByGlass")
                .putCustomAttribute("Glass", glass));
        iDrinksPresenter = new DisplayGlassDrinks(interactor_);
        iDrinksPresenter.attachView(iDrinksView);
        iDrinksPresenter.performListDisplay(glass);
    }

}
