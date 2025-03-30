package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Otp_Extractor {

    public static String extractOtpFromEmail(String emailContent) {

        if (emailContent.contains("Your verification code") && emailContent.contains("AFF-HONIVY is")) {
            System.out.println("✅ Raw Message found: [" + emailContent + "]");

            Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b"); 
            Matcher matcher = otpPattern.matcher(emailContent);
            if (matcher.find()) {
                String otp = matcher.group();  
                System.out.println("🔢 OTP extracted: [" + otp + "]");
                return otp;
            } else {
                System.out.println("⏳ Không tìm thấy OTP.");
                return null;
            }
        } else {
            System.out.println("⏳ Không tìm thấy OTP.");
            return null;
        }
    }
}
