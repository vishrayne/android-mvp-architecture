package in.vishrayne.example.architecture.mvptest;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import in.vishrayne.architecture.error.MvpError;
import in.vishrayne.example.architecture.R;

public class MvpDemoActivity extends AppCompatActivity implements MvpDemoView {
  MvpDemoPresenter presenter;
  TextView greetingsView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mvp_demo);

    if (presenter == null) {
      // use a proper DI tool to inject these.
      presenter = new MvpDemoPresenter(new MvpDemoModel());
      presenter.setView(this);
    }

    greetingsView = findViewById(R.id.textView2);
    presenter.sayHello();

    findViewById(R.id.show_error_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        presenter.makeError();
      }
    });

    findViewById(R.id.show_fatal_error_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        presenter.makeFatalError();
      }
    });
  }

  @Override public void greetUser(String data) {
    greetingsView.setText(data);
  }

  @Override public void showLoading(String message) {

  }

  @Override public void hideLoading() {

  }

  @Override public void showError(MvpError mvpError) {
    String message =
        String.format("Error ==> title: %s -- message: %s -- isfatal: %b", mvpError.getTitle(),
            mvpError.getMessage(), mvpError.isFatal());
    Log.d("MvpDemo", message);

    Snackbar.make(greetingsView, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
  }

  @Override public void showContent() {

  }

  @Override public void showEmpty() {

  }

  private void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }
}
