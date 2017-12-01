package in.vishrayne.example.architecture.mvptest;

import in.vishrayne.architecture.Mvp;
import in.vishrayne.architecture.error.MvpError;

/**
 * Created by vishnu on 18/8/17.
 */

public class MvpDemoPresenter extends Mvp.Presenter<MvpDemoView> {
  MvpDemoModel mvpDemoModel;

  public MvpDemoPresenter(MvpDemoModel mvpDemoModel) {
    this.mvpDemoModel = mvpDemoModel;
  }

  @Override public void destroy() {

  }

  public void sayHello() {
    MvpModel model = mvpDemoModel.cachedItem();
    getView().greetUser(model.getData());
  }

  public void makeError() {
    MvpError mvpError = MvpError.CreateError(100,
        "Cancelable error occurred because of so and so reason, which was submitted for auto-rectification once and failed!. Press back or click outside to dismiss")
        .withTitle("Error");

    getView().showError(mvpError);
  }

  public void makeFatalError() {
    MvpError mvpError = MvpError.CreateFatalError(101,
        "A fatal error occurred because of so and so reason, which was submitted for auto-rectification once and failed!. Press OK to dismiss --Inconvenience is regretted")
        .withTitle("Fatal Error");

    getView().showError(mvpError);
  }
}
