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

```java
BiometricEncryptedSharedPreferences.create(
        this,
        "secret_shared_prefs",
        1,
        new BiometricPrompt.PromptInfo.Builder().setTitle(getString(R.string.app_name)).setDeviceCredentialAllowed(true).build()
).observe(this, it -> {
    it.edit().putString("secretValue", "IT works!").apply();
    System.out.println(it.getString("secretValue", "It didn't work"));
});
```