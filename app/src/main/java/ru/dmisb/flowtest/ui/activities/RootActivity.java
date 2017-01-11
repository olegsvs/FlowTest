package ru.dmisb.flowtest.ui.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import flow.Direction;
import flow.Flow;
import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.databinding.ActivityRootBinding;
import ru.dmisb.flowtest.flow.TreeKeyDispatcher;
import ru.dmisb.flowtest.flow.TreeKeyParceler;
import ru.dmisb.flowtest.screens.calc.CalcScreen;
import ru.dmisb.flowtest.screens.list.ListScreen;
import ru.dmisb.flowtest.ui.helpers.IActionBarView;
import ru.dmisb.flowtest.ui.helpers.IView;
import ru.dmisb.flowtest.utils.L;

public class RootActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IActionBarView {

    private ActivityRootBinding mBinding;
    @Nullable
    private ActionBarDrawerToggle mToggle;
    @Nullable
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_root);
        initToolbar();
        initDrawer();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .dispatcher(new TreeKeyDispatcher(this))
                .defaultKey(new CalcScreen())
                .keyParceler(new TreeKeyParceler())
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPause() {
        mBinding.navigationView.setNavigationItemSelectedListener(null);
        super.onPause();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        mActionBar = getSupportActionBar();
    }

    private void initDrawer() {
        mToggle = new ActionBarDrawerToggle(this,
                mBinding.drawerLayout,
                mBinding.toolbar,
                R.string.open_drawer,
                R.string.close_drawer);
        mToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.calc_list:
                Flow.get(this).replaceHistory(new ListScreen(), Direction.REPLACE);
                return true;
            case R.id.new_calc:
                Flow.get(this).replaceHistory(new CalcScreen(), Direction.REPLACE);
                return true;
            case R.id.settings:
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (! Flow.get(this).goBack()) {
            if (mBinding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    // region ==================== IActionBarView ====================

    @Override
    public void setBackArrowShow(boolean isShow) {
        if (mToggle != null && mActionBar != null) {
            if (isShow) {
                mToggle.setDrawerIndicatorEnabled(false); // скрываем индикатор toggle
                mActionBar.setDisplayHomeAsUpEnabled(true); // устанавливаем индикатор тулбара
                if (mToggle.getToolbarNavigationClickListener() == null) {
                    mToggle.setToolbarNavigationClickListener(v -> onBackPressed()); // вешаем обработчик
                }
            } else {
                mActionBar.setDisplayHomeAsUpEnabled(false); // скрываем индиктора тулбара
                mToggle.setDrawerIndicatorEnabled(true); // фктивируем индиктора toggle
                mToggle.setToolbarNavigationClickListener(null); // зануляем обработчик на toggle
            }
            // если есть возможность вернуться назад (активна стрелка назад),
            // то блокируем раскрытие NavigationDrawer
            mBinding.drawerLayout.setDrawerLockMode(
                    isShow ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED);
            mToggle.syncState(); // синхронизируем состояние toggle с NavigationDrawer
        }
    }

    @Override
    public void setBatTitle(CharSequence title) {
        L.d(title.toString());
        if (mActionBar != null) {
            if (title.length() == 0) {
                mActionBar.setDisplayShowTitleEnabled(false);
            } else {
                mActionBar.setDisplayShowTitleEnabled(true);
                mActionBar.setTitle(title);
            }
        }
    }

    public void updateToolBar() {
        IView view = (IView) mBinding.rootFrame.getChildAt(0);
        if (view != null) {
            setBatTitle(getResources().getString(view.getBarTitleResId()));
            setBackArrowShow(view.getBackArrowShow());
        }
    }

    // endregion
}