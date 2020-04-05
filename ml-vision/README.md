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
	implementation 'com.github.xanscale.LocalhostToolkit:ml-vision:-SNAPSHOT'
}
```
This library require to Add Firebase to Your Android Project:
https://firebase.google.com/docs/android/setup

This sample is for VisionBarcodeDetectorView.
VisionTextRecognizerView is same

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:keepScreenOn="true">

    <it.localhostsoftware.ml.vision.barcode.VisionBarcodeDetectorView
        android:id="@+id/camera"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</FrameLayout>
```
```java
public class class BarcodeActivity extends AppCompatActivity implements OnSuccessListener<List<FirebaseVisionBarcode>> {
	@Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)
        camera.setOnSuccessListener(this)

        // set delay for each scan
        camera.setDelayMillis(3000)

        // RequiresPermission Manifest.permission.CAMERA
        camera.bindToLifecycle(this)
    }

    @Override public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
        // Your data here
    }
}
```