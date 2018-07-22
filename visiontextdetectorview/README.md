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
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:keepScreenOn="true">

	<it.localhostsoftware.visiontextdetectorview.VisionTextDetectorView
		android:id="@+id/camera"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:adjustViewBounds="true"
		app:cameraFacing="back"/>

	<ImageView
		android:id="@+id/bitmap"
		android:layout_width="192dp"
		android:layout_height="192dp"
		android:background="@color/colorBlue"/>
</FrameLayout>
```
```java
public class CameraFragment extends Fragment implements VisionTextDetectorView.Callback {
	VisionTextDetectorView view;
	ImageView bitmap;
	
	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		// findViewById ...
		view.setPatterns(ErrorRegexListener.PATTERN_SSN_IT, ErrorRegexListener.PATTERN_EMAIL);
		view.setCallback(this);
		view.setDelayMillis(3000);
		// if you want to crop or rotate
		view.setRect(new VisionTextDetectorView.Rect(0, 0, 1, 1, 0));
	}

	@Override public void onResume() {
		super.onResume();
		view.start();
	}
	
	@Override public void onPause() {
		view.stop();
		super.onPause();
	}
	
	@Override public void onDestroy() {
		super.onDestroy();
		view.destroy();
	}
	
		@Override public void onTextDetected(VisionTextDetectorView.VisionTextResult visionTextResult) {
    		for (String value : visionTextResult.getMatched())
    			Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
    		bitmap.setImageBitmap(visionTextResult.getBitmap());
    	}
}

```