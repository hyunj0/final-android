package nyc.c4q.android;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;

import nyc.c4q.android.model.Email;
import nyc.c4q.android.rest.FakeEmailService;
import nyc.c4q.android.ui.EmailDetailActivity;

public class EmailApplication extends Application {
  public static final int EMAIL_POLL_IN_SEC = 5;

  public static final int MILLIS_PER_SEC = 1000;
  public static final int DELAY_MILLIS = EMAIL_POLL_IN_SEC * MILLIS_PER_SEC;

  private static final FakeEmailService emailService = new FakeEmailService();

  private HandlerThread handlerThread;
  private NotificationManager notificationManager;
  private Runnable emailCheck;

  @Override public void onCreate() {
    super.onCreate();

    // TODO - finish this
    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    handlerThread = new HandlerThread("email-timer");
    handlerThread.start();
    Looper looper = handlerThread.getLooper();
    final Handler handler = new Handler(looper);

    emailCheck = new Runnable() {
      @Override public void run() {
        if (emailService.hasNewMail()) {

          // TODO
          // 1) get the most recent email and..
          // a) send a notification to the user notifying of the new email
          // b) use R.string.you_got_email as title
          // c) use R.string.notification_email_from (accounting for who sent the email)
          // d) when user clicks on notification, go to EmailDetailActivity

            Intent resultIntent = new Intent(getApplicationContext(), EmailDetailActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Email email = emailService.getEmails().get(0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.c4q);
            builder.setContentTitle("You got mail!");
            builder.setContentText("From " + email.getFrom());
            builder.setContentIntent(resultPendingIntent);
            Notification notification = builder.build();
            notificationManager.notify(1, notification);

        }


        handler.postDelayed(emailCheck, DELAY_MILLIS);
      }
    };

    handler.postDelayed(emailCheck, DELAY_MILLIS);
  }
}
