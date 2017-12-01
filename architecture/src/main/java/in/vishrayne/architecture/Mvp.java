package in.vishrayne.architecture;

import in.vishrayne.architecture.error.MvpError;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by vishnu on 14/8/17.
 */

public interface Mvp {
  interface Model<T> {
    void cache(T data);

    T cachedItem();

    void cacheItems(List<? extends T> data);

    List<? extends T> cachedItems();

    void clearCache();

    interface OnDataLoaded<D> {
      void onSuccess(D data);

      void onFail(MvpError mvpError);
    }
  }

  interface View {
    void showLoading(String message);

    void hideLoading();

    void showError(MvpError mvpError);

    void showContent();

    void showEmpty();
  }

  abstract class Presenter<V extends View> {
    private WeakReference<V> view = null;

    public final void setView(V view) {
      if (view == null) throw new NullPointerException("new view must not be null");

      if (this.view != null) dropView(this.view.get());

      this.view = new WeakReference<>(view);
    }

    public final void dropView(V view) {
      destroy();
      
      if (view == null) throw new NullPointerException("dropped view must not be null");
      this.view = null;
    }

    protected final V getView() {
      if (view == null) {
        throw new NullPointerException(
            "getView called when view is null. " + "Ensure setView(View view) is called first.");
      }
      return view.get();
    }

    public abstract void destroy();
  }
}

