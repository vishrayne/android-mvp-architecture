package in.vishrayne.example.architecture.mvptest;

import in.vishrayne.architecture.Mvp;

/**
 * Created by vishnu on 18/8/17.
 */

public interface MvpDemoView extends Mvp.View {
  void greetUser(String data);
}
