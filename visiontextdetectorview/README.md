This library require to Add Firebase to Your Android Project:
https://firebase.google.com/docs/android/setup

```xml
<?xml version="1.0" encoding="utf-8"?>
<it.localhostsoftware.visiontextdetectorview.VisionTextDetectorView 
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:id="@+id/camera"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:adjustViewBounds="true"
	app:facing="back"/>
```
```java
public class CameraFragment extends Fragment implements VisionTextDetectorView.Callback {
	private VisionTextDetectorView view;

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.camera, container, false);
		view = v.findViewById(R.id.camera);
		view.setPattern(ErrorRegexListener.PATTERN_SSN_IT);
		view.setCallback(this);
		view.setDelayMillis(3000);
		return v;
	}

	@Override public void onResume() {
		super.onResume();
		view.start();
	}

	@Override public void onPause() {
		view.stop();
		super.onPause();
	}

	@Override public void onTextDetected(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}

```