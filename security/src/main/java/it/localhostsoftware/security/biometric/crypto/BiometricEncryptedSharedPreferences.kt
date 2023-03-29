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
import java.io.IOException
import java.security.GeneralSecurityException

object BiometricEncryptedSharedPreferences {
    private const val KEY_SIZE = 256
    private const val MASTER_KEY_ALIAS = "_androidx_security_master_key_biometric"
    private val AUTHENTICATORS =
        BiometricManager.Authenticators.DEVICE_CREDENTIAL or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) BiometricManager.Authenticators.BIOMETRIC_STRONG else BiometricManager.Authenticators.BIOMETRIC_WEAK

    fun canAuthenticate(c: Context) =
        BiometricManager.from(c).canAuthenticate(AUTHENTICATORS) == BiometricManager.BIOMETRIC_SUCCESS

    /**
     * @param fragment          A reference to the client's fragment
     * @param fileName          The name of the file to open; can not contain path separators
     * @param timeout           duration in seconds, must be greater than 0
     * @param promptInfoBuilder The information that will be displayed on the prompt. Create this object using [BiometricPrompt.PromptInfo.Builder]
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    fun create(fragment: Fragment, fileName: String, timeout: Int, promptInfoBuilder: PromptInfo.Builder): LiveData<SharedPreferences?> =
        MutableLiveData<SharedPreferences?>().also {
            BiometricPrompt(fragment, AuthenticationCallback(fragment.requireContext(), fileName, timeout, it))
                .authenticate(promptInfoBuilder.setAllowedAuthenticators(AUTHENTICATORS).build())
        }

    /**
     * @param activity          A reference to the client's activity
     * @param fileName          The name of the file to open; can not contain path separators
     * @param timeout           duration in seconds, must be greater than 0
     * @param promptInfoBuilder The information that will be displayed on the prompt. Create this object using [BiometricPrompt.PromptInfo.Builder]
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    fun create(activity: FragmentActivity, fileName: String, timeout: Int, promptInfoBuilder: PromptInfo.Builder): LiveData<SharedPreferences?> =
        MutableLiveData<SharedPreferences?>().also {
            BiometricPrompt(activity, AuthenticationCallback(activity, fileName, timeout, it))
                .authenticate(promptInfoBuilder.setAllowedAuthenticators(AUTHENTICATORS).build())
        }

    private fun create(c: Context, fileName: String, timeout: Int) = try {
        EncryptedSharedPreferences.create(fileName, MasterKeys.getOrCreate(KeyGenParameterSpec.Builder(MASTER_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(KEY_SIZE)
            setUserAuthenticationRequired(true)
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                setUserAuthenticationParameters(timeout, KeyProperties.AUTH_BIOMETRIC_STRONG or KeyProperties.AUTH_DEVICE_CREDENTIAL)
            else
                setUserAuthenticationValidityDurationSeconds(timeout)
        }.build()), c, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    } catch (e: GeneralSecurityException) {
        e.printStackTrace()
        null
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

    private class AuthenticationCallback(
        private val context: Context,
        private val fileName: String,
        private val timeout: Int,
        private val out: MutableLiveData<SharedPreferences?>
    ) : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            out.postValue(create(context, fileName, timeout))
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            out.postValue(null)
        }
    }
}