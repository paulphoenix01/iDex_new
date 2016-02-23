package phoenix.idex;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import phoenix.idex.AdapterClassModel.ItemSlideMenu;
import phoenix.idex.Adapters.SlidingMenuAdapter;
import phoenix.idex.Fragments.Fragment3;
import phoenix.idex.Fragments.LoginActivityFragment;
import phoenix.idex.Fragments.PostListFragment;
import phoenix.idex.Fragments.TabFragment;

public class MainActivity extends AppCompatActivity {

    private List<ItemSlideMenu> itemList;
    private List<Fragment> fragmentList;
    private SlidingMenuAdapter adapter;
    private ListView listView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int currentPos;
    private RelativeLayout layout;
    private FragmentManager fragmentManager;
    public static boolean isMainShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();


        // Display icon to open/close sliding list
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewMain);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        layout = (RelativeLayout) findViewById(R.id.rLayoutMain);

        itemList = new ArrayList<>();

        itemList.add(new ItemSlideMenu("Roll"));
        itemList.add(new ItemSlideMenu("Log In"));
        itemList.add(new ItemSlideMenu("Dash"));
        itemList.add(new ItemSlideMenu("Profile"));
        itemList.add(new ItemSlideMenu("About"));

        adapter = new SlidingMenuAdapter(this, itemList);
        listView.setAdapter(adapter);

        fragmentList = new ArrayList<>();

        fragmentList.add(new PostListFragment());
        fragmentList.add(new LoginActivityFragment());
        fragmentList.add(new Fragment3());
        fragmentList.add(new TabFragment());
        fragmentList.add(new PostListFragment());


        /* Start up state
         * Title Idex, with closed navigation drawer and default fragment 1
         */
        isMainShown = true;
        setTitle("Idex");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rLayoutMain, fragmentList.get(0)).commit();
        listView.setItemChecked(0, true);
        drawerLayout.closeDrawer(listView);


        // Handle click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPos = position;
                if (position == 0 ) {
                    isMainShown = true;
                    setTitle("Idex");
                } else {
                    isMainShown = false;
                    setTitle(itemList.get(position).getTitle());
                }

                drawerLayout.closeDrawer(listView);
                // Set item to selected
                listView.setItemChecked(position, true);

                // Delay to avoid lag between navigation drawer items
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.rLayoutMain, fragmentList.get(currentPos)).commit();                    }
                }, 270);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawerOpened, R.string.drawerClosed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync it based on if navigation drawer is selected or not.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (isMainShown) {
            finish();
        } else  {
            fragmentManager.beginTransaction().replace(R.id.rLayoutMain, fragmentList.get(0)).commit();
        }
    }
}
