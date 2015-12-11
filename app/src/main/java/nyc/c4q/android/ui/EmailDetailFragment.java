package nyc.c4q.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nyc.c4q.android.R;
import nyc.c4q.android.model.Email;

public class EmailDetailFragment extends Fragment {
  private Email email;
  private static final DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);


  private ImageView email_from_img;
  private TextView email_from;
  private TextView email_subject;
  private TextView email_sent;
  private TextView email_body;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    email = (Email) getArguments().getSerializable("email");
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_email_detail, container, false);

    // TODO - get references to views
      email_from_img = (ImageView) view.findViewById(R.id.email_from_img);
      email_from = (TextView) view.findViewById(R.id.email_from);
      email_subject = (TextView) view.findViewById(R.id.email_subject);
      email_sent = (TextView) view.findViewById(R.id.email_sent);
      email_body = (TextView) view.findViewById(R.id.email_body);


    // TODO - replace nulls
    Picasso.with(getActivity()).load((String) email.getFromUrl()).into((ImageView) email_from_img);

    // TODO - bind email data to views
      email_from.setText(email.getFrom());
      email_subject.setText(email.getSubject());
      email_sent.setText(formatter.format(email.getSent()));
      email_body.setText(email.getBody());

    return view;
  }

  public static EmailDetailFragment newInstance(Email email) {
    // TODO - implement this factory method
    // create fragment, set up its only argument (the email) and return it
      EmailDetailFragment emailDetailFragment = new EmailDetailFragment();
      Bundle args = new Bundle();
      args.putSerializable("email", email);
      emailDetailFragment.setArguments(args);

    // hint
    //args.putSerializable("email", email);

      return emailDetailFragment;

  }
}
