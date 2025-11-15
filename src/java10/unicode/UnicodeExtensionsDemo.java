package java10.unicode;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Java 10 Additional Unicode Language-Tag Extensions
 * 
 * Demonstrates support for additional Unicode language-tag extensions:
 * - Hour cycle (hc): h11, h12, h23, h24
 * - Number system (nu): latn, arab, etc.
 * - Calendar type (ca)
 * - Collation type (co)
 */
public class UnicodeExtensionsDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Unicode Language-Tag Extensions ===");
        
        // 1. Hour Cycle Extensions
        System.out.println("\n--- Hour Cycle Extensions (hc) ---");
        
        // 12-hour format (h12)
        Locale locale12h = Locale.forLanguageTag("en-US-u-hc-h12");
        DateFormat df12h = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale12h);
        System.out.println("12-hour format: " + df12h.format(new Date()));
        
        // 24-hour format (h23)
        Locale locale24h = Locale.forLanguageTag("en-US-u-hc-h23");
        DateFormat df24h = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale24h);
        System.out.println("24-hour format: " + df24h.format(new Date()));
        
        // Alternative 12-hour (h11) - 0-11 range
        Locale locale11h = Locale.forLanguageTag("en-US-u-hc-h11");
        DateFormat df11h = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale11h);
        System.out.println("11-hour format (0-11): " + df11h.format(new Date()));
        
        // Alternative 24-hour (h24) - 0-24 range
        Locale locale24hAlt = Locale.forLanguageTag("en-US-u-hc-h24");
        DateFormat df24hAlt = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale24hAlt);
        System.out.println("24-hour format (0-24): " + df24hAlt.format(new Date()));
        
        // 2. Number System Extensions
        System.out.println("\n--- Number System Extensions (nu) ---");
        
        // Latin numerals (default)
        Locale latinNums = Locale.forLanguageTag("en-US-u-nu-latn");
        NumberFormat nfLatin = NumberFormat.getNumberInstance(latinNums);
        System.out.println("Latin numerals: " + nfLatin.format(12345));
        
        // Arabic-Indic numerals
        Locale arabicNums = Locale.forLanguageTag("ar-SA-u-nu-arab");
        NumberFormat nfArabic = NumberFormat.getNumberInstance(arabicNums);
        System.out.println("Arabic-Indic numerals: " + nfArabic.format(12345));
        
        // 3. Calendar Extensions
        System.out.println("\n--- Calendar Extensions (ca) ---");
        
        // Gregorian calendar (default)
        Locale gregorian = Locale.forLanguageTag("en-US-u-ca-gregory");
        DateFormat dfGreg = DateFormat.getDateInstance(DateFormat.FULL, gregorian);
        System.out.println("Gregorian calendar: " + dfGreg.format(new Date()));
        
        // Japanese calendar
        Locale japanese = Locale.forLanguageTag("ja-JP-u-ca-japanese");
        DateFormat dfJpn = DateFormat.getDateInstance(DateFormat.FULL, japanese);
        System.out.println("Japanese calendar: " + dfJpn.format(new Date()));
        
        // 4. Combined Extensions
        System.out.println("\n--- Combined Extensions ---");
        
        // Multiple extensions in one locale
        Locale combined = Locale.forLanguageTag("en-US-u-hc-h12-nu-latn-ca-gregory");
        DateFormat dfCombined = DateFormat.getDateTimeInstance(
            DateFormat.FULL, DateFormat.FULL, combined);
        System.out.println("Combined (12h, Latin, Gregorian): " + dfCombined.format(new Date()));
        
        // 5. Practical Examples
        System.out.println("\n=== Practical Examples ===");
        
        // Format time for different regions
        System.out.println("\n--- Regional Time Formats ---");
        formatTimeForRegion("en-US-u-hc-h12", "US (12-hour)");
        formatTimeForRegion("en-GB-u-hc-h24", "UK (24-hour)");
        formatTimeForRegion("de-DE-u-hc-h24", "Germany (24-hour)");
        
        // Format numbers with different numeral systems
        System.out.println("\n--- Number Formatting ---");
        formatNumberWithSystem(12345, "en-US-u-nu-latn", "Latin");
        formatNumberWithSystem(12345, "ar-SA-u-nu-arab", "Arabic-Indic");
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Better internationalization support");
        System.out.println("2. More flexible locale handling");
        System.out.println("3. Standardized Unicode extensions");
        System.out.println("4. Consistent with Unicode CLDR standards");
        System.out.println("5. Better support for regional preferences");
    }
    
    private static void formatTimeForRegion(String localeTag, String regionName) {
        Locale locale = Locale.forLanguageTag(localeTag);
        DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        System.out.println(regionName + ": " + df.format(new Date()));
    }
    
    private static void formatNumberWithSystem(int number, String localeTag, String systemName) {
        Locale locale = Locale.forLanguageTag(localeTag);
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        System.out.println(systemName + " numerals: " + nf.format(number));
    }
}

