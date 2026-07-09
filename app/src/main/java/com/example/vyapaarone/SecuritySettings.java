package com.example.vyapaarone;

public class SecuritySettings {

    private boolean appLock;
    private boolean fingerprint;
    private boolean autoLogout;
    private String pin;

    public SecuritySettings() {
    }

    public SecuritySettings(boolean appLock,
                            boolean fingerprint,
                            boolean autoLogout,
                            String pin) {
        this.appLock = appLock;
        this.fingerprint = fingerprint;
        this.autoLogout = autoLogout;
        this.pin = pin;
    }

    public boolean isAppLock() {
        return appLock;
    }

    public void setAppLock(boolean appLock) {
        this.appLock = appLock;
    }

    public boolean isFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(boolean fingerprint) {
        this.fingerprint = fingerprint;
    }

    public boolean isAutoLogout() {
        return autoLogout;
    }

    public void setAutoLogout(boolean autoLogout) {
        this.autoLogout = autoLogout;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}