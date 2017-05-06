package io.github.likewhat.springbase.web.util;


import org.springframework.util.Assert;


public final class UrlUtil {
    private UrlUtil() {
    }

    public static String redirect(final String url) {
        Assert.hasText(url, "Url must not be empty!");
        return "redirect:" + url;
    }
}
