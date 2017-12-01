package in.vishrayne.example.architecture.mvptest;

/**
 * Created by vishnu on 18/8/17.
 */

public class MvpModel {
  private String data;

  public MvpModel() {
    this.data = "Greetings! TimeStamp==>" + System.currentTimeMillis();
  }

  public String getData() {
    return data;
  }
}
