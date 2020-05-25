package it.localhostsoftware.security.biometric.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class BiometricEncryptedSharedPreferences {
    private static final int KEY_SIZE = 256;
    private static final String MASTER_KEY_ALIAS = "_androidx_security_master_key_biometric";

    /**
     * @param fragment                                  A reference to the client's fragment
     * @param fileName                                  The name of the file to open; can not contain path separators
     * @param userAuthenticationValidityDurationSeconds duration in seconds, must be greater than 0
     * @param promptInfo                                The information that will be displayed on the prompt. Create this object using {@link BiometricPrompt.PromptInfo.Builder}
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    public static LiveData<SharedPreferences> create(final Fragment fragment, final String fileName, final int userAuthenticationValidityDurationSeconds, BiometricPrompt.PromptInfo promptInfo) {
        final MutableLiveData<SharedPreferences> out = new MutableLiveData<>();
        new BiometricPrompt(fragment.requireActivity(), ContextCompat.getMainExecutor(fragment.requireContext()),
                new AuthenticationCallback(fragment.requireContext(), fileName, userAuthenticationValidityDurationSeconds, out)
        ).authenticate(promptInfo);
        return out;
    }

    /**
     * @param activity                                  A reference to the client's activity
     * @param fileName                                  The name of the file to open; can not contain path separators
     * @param userAuthenticationValidityDurationSeconds duration in seconds, must be greater than 0
     * @param promptInfo                                The information that will be displayed on the prompt. Create this object using {@link BiometricPrompt.PromptInfo.Builder}
     * @return LiveData of EncryptedSharedPreferences that requires user biometric authentication
     */
    public static LiveData<SharedPreferences> create(final FragmentActivity activity, final String fileName, final int userAuthenticationValidityDurationSeconds, BiometricPrompt.PromptInfo promptInfo) {
        final MutableLiveData<SharedPreferences> out = new MutableLiveData<>();
        new BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
                new AuthenticationCallback(activity, fileName, userAuthenticationValidityDurationSeconds, out)
        ).authenticate(promptInfo);
        return out;
    }

    private static SharedPreferences create(Context c, String fileName, int userAuthenticationValidityDurationSeconds) {
        try {
            return EncryptedSharedPreferences.create(
                    fileName,
                    MasterKeys.getOrCreate(new KeyGenParameterSpec.Builder(
                            MASTER_KEY_ALIAS,
                            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                            .setKeySize(KEY_SIZE)
                            .setUserAuthenticationRequired(true)
                            .setUserAuthenticationValidityDurationSeconds(userAuthenticationValidityDurationSeconds).build()),
                    c,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class AuthenticationCallback extends BiometricPrompt.AuthenticationCallback {
        private final Context context;
        private final String fileName;
        private final int userAuthenticationValidityDurationSeconds;
        private final MutableLiveData<SharedPreferences> out;

        AuthenticationCallback(Context context, String fileName, int userAuthenticationValidityDurationSeconds, MutableLiveData<SharedPreferences> out) {
            this.context = context;
            this.fileName = fileName;
            this.userAuthenticationValidityDurationSeconds = userAuthenticationValidityDurationSeconds;
            this.out = out;
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            out.postValue(create(context, fileName, userAuthenticationValidityDurationSeconds));
        }

        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            out.postValue(null);
        }
    }
}
