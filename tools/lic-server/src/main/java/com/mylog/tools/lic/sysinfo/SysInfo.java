package com.mylog.tools.lic.sysinfo;

/**
 * 系统信息类
 * @author Dylan
 */
public class SysInfo {
    public boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
    }
}
