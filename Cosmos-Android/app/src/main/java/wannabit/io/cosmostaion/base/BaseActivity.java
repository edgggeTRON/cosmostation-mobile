package wannabit.io.cosmostaion.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WalletListActivity;
import wannabit.io.cosmostaion.dialog.Dialog_Wait;

public class BaseActivity extends AppCompatActivity {

    protected BaseApplication               mApplication;
    protected BaseData                      mData;
    protected Dialog_Wait                   mDialogWait;
    public    boolean                       mBusy;
    public    int                           mBusyCnt;


    public BaseApplication getBaseApplication() {
        if (mApplication == null)
            mApplication = (BaseApplication) getApplication();
        return mApplication;
    }

    public BaseData getBaseDao() {
        if (mData == null)
            mData = getBaseApplication().getBaseDao();
        return mData;
    }

    public void onShowWaitDialog() {
        mDialogWait = new Dialog_Wait();
        mDialogWait.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogWait, "wait").commitNowAllowingStateLoss();
    }

    public void onHideWaitDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("wait");
        if (prev != null) {
            ft.remove(prev).commitNowAllowingStateLoss();
        }
    }

    public void onHideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v == null) {
            v = new View(getBaseContext());
        }
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void onStartMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//    public void onStartListActivity() {
//        Intent intent = new Intent(this, WalletListActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    public void onChoiceNet(BaseChain chain) {
    }

    public void onShare() {

    }
}
