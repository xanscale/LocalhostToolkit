This library use livedata to merge androidx.security with androidx.biometric

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
	implementation 'com.github.xanscale.LocalhostToolkit:security:-SNAPSHOT'
}
```
You can just use this inside fragment or activity
```java
BiometricEncryptedSharedPreferences.canAuthenticate(context)

BiometricEncryptedSharedPreferences.create(context, "secret_shared_prefs", 1,
        new BiometricPrompt.PromptInfo.Builder().setTitle(getString(R.string.app_name))
).observe(this, it -> {
    it.edit().putString("secretValue", "IT works!").apply();
    System.out.println(it.getString("secretValue", "It didn't work"));
});
```