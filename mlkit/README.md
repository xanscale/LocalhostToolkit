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

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.camera.view.PreviewView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/previewView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
To use BarcodeScanner
```
implementation("com.google.mlkit:barcode-scanning:{latestVersion}")
BarcodeScanning.getClient().bindToLifecycle(...)
```
To use ImageLabeler
```
implementation("com.google.mlkit:image-labeling-custom:{latestVersion}")
ImageLabeling.getClient(...).bindToLifecycle(...)
```
To use TextRecognizer
```
implementation("com.google.mlkit:text-recognition:{latestVersion}")
TextRecognition.getClient().bindToLifecycle(...)
```
