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
	implementation 'com.github.xanscale.LocalhostToolkit:app:-SNAPSHOT'
	implementation 'com.github.xanscale.LocalhostToolkit:visiontextdetectorview:-SNAPSHOT'
}
```