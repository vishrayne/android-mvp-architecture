package in.vishrayne.architecture.error;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vishnu on 14/8/17.
 */

public class MvpError implements Parcelable {
  private static final int TYPE_STANDARD = 1;

  private String title;
  private String message;
  private int errorCode;
  private boolean isFatal;

  public static MvpError CreateError(int errorCode, String message) {
    return new MvpError().withMessage(message).withErrorCode(errorCode).setAsFatal(false);
  }

  public static MvpError CreateFatalError(int errorCode, String message) {
    return new MvpError().withMessage(message).withErrorCode(errorCode).setAsFatal(true);
  }

  public static MvpError Wrap(Throwable e) {
    return new MvpError().withMessage(e.getMessage()).withErrorCode(TYPE_STANDARD).setAsFatal(true);
  }

  public MvpError withTitle(String title) {
    this.title = title;
    return this;
  }

  private MvpError() {
    // no-op
  }

  private MvpError(Parcel in) {
    title = in.readString();
    message = in.readString();
    errorCode = in.readInt();
    isFatal = in.readByte() != 0x00;
  }

  public String getMessage() {
    return message;
  }

  public String getTitle() {
    if (title == null || title.isEmpty()) {
      return "Action failed!";
    }

    return title;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public boolean isFatal() {
    return isFatal;
  }

  public MvpError withMessage(String message) {
    this.message = message;
    return this;
  }

  public MvpError withErrorCode(int errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  public MvpError setAsFatal(boolean isFatal) {
    this.isFatal = isFatal;
    return this;
  }

  @Override public String toString() {
    return String.format("%s | %s", errorCode, message);
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(title);
    parcel.writeString(message);
    parcel.writeInt(errorCode);
    parcel.writeByte((byte) (isFatal ? 0x01 : 0x00));
  }

  @SuppressWarnings("unused") public static final Creator<MvpError> CREATOR =
      new Creator<MvpError>() {
        @Override public MvpError createFromParcel(Parcel in) {
          return new MvpError(in);
        }

        @Override public MvpError[] newArray(int size) {
          return new MvpError[size];
        }
      };
}

