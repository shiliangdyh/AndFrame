package com.mirstone.selectfilelib.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * @package: com.mirstone.selectfilelib.filter
 * @fileName: SLFileFilter
 * @data: 2018/7/28 15:03
 * @author: ShiLiang
 * @describe:
 */
public class SLFileFilter implements FileFilter {
    private final boolean showHideFile;
    private String[] mTypes;

    public SLFileFilter(String[] types, boolean showHideFile) {
        this.mTypes = types;
        this.showHideFile = showHideFile;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return showHideFile || !file.getName().startsWith(".");
        }
        if (mTypes != null && mTypes.length > 0) {
            for (String mType : mTypes) {
                if (file.getName().endsWith(mType.toLowerCase()) || file.getName().endsWith(mType.toUpperCase())) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
