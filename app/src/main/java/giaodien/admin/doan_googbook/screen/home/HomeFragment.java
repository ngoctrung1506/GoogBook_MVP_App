package giaodien.admin.doan_googbook.screen.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The Home Fragment
 */
public class HomeFragment extends ViewFragment<HomeContract.Presenter> implements HomeContract.View {

  @BindView(R.id.main_viewpager)
  ViewPager mViewPager;

  @BindView(R.id.main_tab)
  TabLayout mTab;

  private HomePagerAdapter mHomePagerAdapter;
  private List<Fragment> fragments;

  public static HomeFragment getInstance() {
    return new HomeFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.app_bar_main;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    addTab();
    setUpWithViewPager();

  }

  @Override
  public void setUpView() {
//      addTab();
  }

  public void addTab() {
    mTab.addTab(mTab.newTab().setText("Tin mới nhất"));
    mTab.addTab(mTab.newTab().setText("Thông báo"));
    mTab.addTab(mTab.newTab().setText("Tài liệu online"));
    mTab.addTab(mTab.newTab().setText("Nhiều hơn"));
    mTab.setTabGravity(TabLayout.GRAVITY_CENTER);
    fragments = new ArrayList<>();
    fragments.add(mPresenter.changeFragment(0));
    fragments.add(mPresenter.changeFragment(1));
    fragments.add(mPresenter.changeFragment(2));
    fragments.add(mPresenter.changeFragment(3));

  }

  public void setUpWithViewPager() {
    mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager(),fragments);
    // Set up the ViewPager with the sections adapter.
    mViewPager.setAdapter(mHomePagerAdapter);
    mTab.setupWithViewPager(mViewPager);
  }

  public class HomePagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
      super(fm);
      this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
//      return PlaceholderFragment.newInstance(position + 1);
      return fragments.get(position);
    }

    @Override
    public int getCount() {
      return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "Tin mới nhất";
        case 1:
          return "Thông báo";
        case 2:
          return "Tài liệu online";
        case 3:
          return "Nhiều hơn";
      }
      return null;
    }
  }

}
