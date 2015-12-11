package nyc.c4q.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;
import nyc.c4q.android.R;
import nyc.c4q.android.model.Email;

public class EmailListActivity extends FragmentActivity implements EmailListFragment.OnEmailSelectedListener {

  private boolean isTwoPane = false;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_email_list);
    determinePaneLayout();
  }

  private void determinePaneLayout() {
    FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.email_detail_container);
    if (fragmentItemDetail != null) {
      isTwoPane = true;
      FragmentManager fragmentManager = getSupportFragmentManager();
      EmailListFragment fragmentItemsList =
          (EmailListFragment) fragmentManager.findFragmentById(R.id.fragment_email_list);
      fragmentItemsList.setActivateOnItemClick(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.items, menu);
    return true;
  }

  @Override
  public void onEmailSelected(Email email) {
    if (isTwoPane) {
      // tablet - single activity with list and detail

      // TODO - use EmailDetailFragment's factory method to create the fragment
      // then add the fragment to the SupportFragmentManager under R.id.email_detail_container
        EmailDetailFragment fragment = EmailDetailFragment.newInstance(email);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.email_detail_container, fragment);
        ft.commit();


    }
    else {
      // mobile - one activity at a time

      // TODO - launch EmailDetailActivity passing "email" extra

        Intent emailIntent = new Intent(getApplicationContext(), EmailDetailActivity.class);
        emailIntent.putExtra("email", email);
        startActivity(emailIntent);
    }
  }
}
