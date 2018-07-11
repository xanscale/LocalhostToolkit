```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="match_parent"
	android:layout_height="match_parent">

	<it.vjtechnology.whereapp.fragment.VisionTextDetectorView
		android:id="@+id/camera"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:adjustViewBounds="true"
		app:facing="back"/>
</FrameLayout>
```

```java
public class CameraFragment extends Fragment implements VisionTextDetectorView.Callback {
	@BindView(R.id.camera) VisionTextDetectorView mCameraView;

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.camera, container, false);
		ButterKnife.bind(this, v);
		mCameraView.setPattern(ErrorRegexListener.PATTERN_SSN_IT);
		mCameraView.setCallback(this);
		mCameraView.setDelayMillis(3000);
		return v;
	}

	@Override public void onResume() {
		super.onResume();
		mCameraView.start();
	}

	@Override public void onPause() {
		mCameraView.stop();
		super.onPause();
	}

	@Override public void onTextDetected(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
```