package localhost.toolkit.app.appcompat;

import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;

public class StartActivityForResultLauncher extends LiveData<ActivityResult> implements ActivityResultCallback<ActivityResult> {
    private final ActivityResultLauncher<Intent> launcher;

    public StartActivityForResultLauncher(Fragment fragment) {
        launcher = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);
    }

    public StartActivityForResultLauncher(FragmentActivity activity) {
        launcher = activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        setValue(result);
    }

    public void launch(Intent intent) {
        launcher.launch(intent);
    }
}
