package in.vishrayne.example.architecture.mvptest;

import in.vishrayne.architecture.Mvp;
import java.util.List;

/**
 * Created by vishnu on 18/8/17.
 */

public class MvpDemoModel implements Mvp.Model<MvpModel> {
  private MvpModel model;

  @Override public void cache(MvpModel data) {

  }

  @Override public MvpModel cachedItem() {
    if (model == null) model = new MvpModel();
    
    return model;
  }

  @Override public void cacheItems(List<? extends MvpModel> data) {

  }

  @Override public List<? extends MvpModel> cachedItems() {
    return null;
  }

  @Override public void clearCache() {

  }
}
