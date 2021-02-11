package localhost.toolkit.app.appcompat;

import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Map;

public class RequestPermissionsManager implements ActivityResultCallback<Map<String, Boolean>> {
    private final ActivityResultLauncher<String[]> launcher;
    private MutableLiveData<Boolean> liveData;
    private FragmentActivity activity;
    private Fragment fragment;

    public RequestPermissionsManager(Fragment fragment) {
        this.fragment = fragment;
        launcher = fragment.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this);
    }

    public RequestPermissionsManager(FragmentActivity activity) {
        this.activity = activity;
        launcher = activity.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this);
    }

    private FragmentActivity getActivity() {
        if (activity != null)
            return activity;
        else
            return fragment.requireActivity();
    }

    @Override
    public void onActivityResult(Map<String, Boolean> results) {
        if (liveData != null) {
            boolean success = true;
            for (boolean result : results.values())
                success = success && result;
            liveData.setValue(success);
        }
    }

    private boolean checkSelfPermission(String... permissions) {
        for (String perm : permissions)
            if (ContextCompat.checkSelfPermission(getActivity(), perm) == PackageManager.PERMISSION_DENIED)
                return false;
        return true;
    }

    private boolean shouldShowRequestPermissionRationale(String... permissions) {
        for (String perm : permissions)
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), perm))
                return true;
        return false;
    }

    public MutableLiveData<Boolean> checkSelfPermission(String title, String rational, final String... permissions) {
        liveData = new MutableLiveData<>();
        if (checkSelfPermission(permissions))
            liveData.setValue(true);
        else if (shouldShowRequestPermissionRationale(permissions)) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            if (title != null)
                builder.setTitle(title);
            if (rational != null)
                builder.setMessage(rational);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    launcher.launch(permissions);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    liveData.setValue(false);
                }
            });
            builder.show();
        } else
            launcher.launch(permissions);
        return liveData;
    }
}
