package com.processmining.logdeploy.autodeploy.util;

import com.processmining.logdeploy.autodeploy.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
