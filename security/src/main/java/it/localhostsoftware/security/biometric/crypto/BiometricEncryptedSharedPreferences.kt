package it.localhostsoftware.security.biometric.crypto

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object BiometricEncryptedSharedPreferences {
    private const val KEY_SIZE = 256
    private const val MASTER_KEY_ALIAS = "_androidx_security_master_key_biometric"
    private val AUTHENTICATORS =
        BiometricManager.Authenticators.DEVICE_CREDENTIAL or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) BiometricManager.Authenticators.BIOMETRIC_STRONG else BiometricManager.Authenticators.BIOMETRIC_WEAK
    private val BIO_AES256_GCM_SPEC by lazy {
        KeyGenParameterSpec.Builder(MASTER_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(KEY_SIZE)
            setUserAuthenticationRequired(true)
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) setUserAuthenticationParameters(1, KeyProperties.AUTH_BIOMETRIC_STRONG or KeyProperties.AUTH_DEVICE_CREDENTIAL)
            else setUserAuthenticationValidityDurationSeconds(1)
        }.build()
    }

    fun canAuthenticate(c: Context) =
        BiometricManager.from(c).canAuthenticate(AUTHENTICATORS) == BiometricManager.BIOMETRIC_SUCCESS

    /**
     * @param fragment          A reference to the client's fragment
     * @param fileName          The name of the file to open; can not contain path separators
     * @param promptInfoBuilder The information that will be displayed on the prompt. Create this object using [BiometricPrompt.PromptInfo.Builder]
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    fun create(fragment: Fragment, fileName: String, promptInfoBuilder: PromptInfo.Builder): LiveData<SharedPreferences?> =
        MutableLiveData<SharedPreferences?>().also {
            BiometricPrompt(fragment, AuthenticationCallback(fragment.requireContext(), fileName, it))
                .authenticate(promptInfoBuilder.setAllowedAuthenticators(AUTHENTICATORS).build())
        }

    /**
     * @param activity          A reference to the client's activity
     * @param fileName          The name of the file to open; can not contain path separators
     * @param promptInfoBuilder The information that will be displayed on the prompt. Create this object using [BiometricPrompt.PromptInfo.Builder]
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    fun create(activity: FragmentActivity, fileName: String, promptInfoBuilder: PromptInfo.Builder): LiveData<SharedPreferences?> =
        MutableLiveData<SharedPreferences?>().also {
            BiometricPrompt(activity, AuthenticationCallback(activity, fileName, it))
                .authenticate(promptInfoBuilder.setAllowedAuthenticators(AUTHENTICATORS).build())
        }

    private class AuthenticationCallback(
        private val context: Context,
        private val fileName: String,
        private val out: MutableLiveData<SharedPreferences?>
    ) : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            out.postValue(EncryptedSharedPreferences.create(fileName, MasterKeys.getOrCreate(BIO_AES256_GCM_SPEC), context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM))
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            out.postValue(null)
        }
    }
}