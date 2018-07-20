Add it in your root build.gradle:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
Add it in your module build.gradle:
```
dependencies {
	implementation 'com.github.xanscale.LocalhostToolkit:visiontextdetectorview:-SNAPSHOT'
}
```
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
		view.setPatterns(ErrorRegexListener.PATTERN_SSN_IT, ErrorRegexListener.PATTERN_EMAIL);
		view.setCallback(this);
		view.setDelayMillis(3000);
		// if you want to crop area. Be careful to create the rectangle completely inside the bitmap
		view.setRect(new Rect(x, y, width, height));
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

	@Override public void onTextDetected(VisionTextResult visionTextResult) {
		for(String value: visionTextResult.getMatched())
			Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
		// if you want to print bitmap
		visionTextResult.getBitmap();
	}
}

```