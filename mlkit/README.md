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
	implementation 'com.github.xanscale.LocalhostToolkit:mlkit:-SNAPSHOT'
}
```
This library require to Add Firebase to Your Android Project:
https://firebase.google.com/docs/android/setup

This sample is for VisionBarcodeDetectorView.
VisionTextRecognizerView is same

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.camera.view.PreviewView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/previewView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
```java
public class class BarcodeActivity extends AppCompatActivity {
	@Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)
        new BarcodeScanner().bindToLifecycle(this, this, previewView,
            new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build(),
            barcodes -> {
              System.out.println(barcodes);
            });
    }
}
```