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
import androidx.lifecycle.LiveData;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Map;

public class RequestPermissionLauncher extends LiveData<RequestPermissionLauncher.PermissionResult> implements ActivityResultCallback<Map<String, Boolean>> {
    private final ActivityResultLauncher<String[]> launcher;
    private FragmentActivity activity;
    private Fragment fragment;

    public RequestPermissionLauncher(Fragment fragment) {
        this.fragment = fragment;
        launcher = fragment.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this);
    }

    public RequestPermissionLauncher(FragmentActivity activity) {
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
        boolean success = true;
        for (boolean result : results.values())
            success = success && result;
        if (success)
            setValue(PermissionResult.GRANTED);
        else if (shouldShowRequestPermissionRationale(results.keySet().toArray(new String[0])))
            setValue(PermissionResult.DENIED);
        else
            setValue(PermissionResult.PERMANENTLY_DENIED);

    }

    private boolean checkSelfPermission(String... permissions) {
        for (String perm : permissions)
            if (ContextCompat.checkSelfPermission(getActivity(), perm) != PackageManager.PERMISSION_GRANTED)
                return false;
        return true;
    }

    private boolean shouldShowRequestPermissionRationale(String... permissions) {
        for (String perm : permissions)
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), perm))
                return true;
        return false;
    }

    public void launch(String title, String rational, final String... permissions) {
        if (checkSelfPermission(permissions))
            setValue(PermissionResult.GRANTED);
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
                    setValue(PermissionResult.DENIED);
                }
            });
            builder.show();
        } else
            launcher.launch(permissions);
    }

    public enum PermissionResult {GRANTED, DENIED, PERMANENTLY_DENIED}
}
