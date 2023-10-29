package com.example.attendancesystem.QRfiles;

import java.time.LocalDateTime;

public class QRcode {
    private String qrCodeText;
    private LocalDateTime expirationTime;

    public QRcode() {
        // Default constructor required for Firebase
    }

    public QRcode(String qrCodeText, LocalDateTime expirationTime) {
        this.qrCodeText = qrCodeText;
        this.expirationTime = expirationTime;
    }

    public String getQRCodeText() {
        return qrCodeText;
    }

    public void setQRCodeText(String qrCodeText) {
        this.qrCodeText = qrCodeText;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
