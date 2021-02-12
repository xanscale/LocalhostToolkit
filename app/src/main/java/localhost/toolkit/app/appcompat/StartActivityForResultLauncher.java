package localhost.toolkit.app.appcompat;

import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

public class StartActivityForResultLauncher implements ActivityResultCallback<ActivityResult> {
    private final ActivityResultLauncher<Intent> launcher;
    private MutableLiveData<ActivityResult> liveData;

    public StartActivityForResultLauncher(Fragment fragment) {
        launcher = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);
    }

    public StartActivityForResultLauncher(FragmentActivity activity) {
        launcher = activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        liveData.setValue(result);
    }

    public MutableLiveData<ActivityResult> launch(Intent intent) {
        liveData = new MutableLiveData<>();
        launcher.launch(intent);
        return liveData;
    }
}
