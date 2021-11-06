package com.dassoop.jdkswitchapp.services;

import java.io.File;

public class FilesService
{
    public String[] getFileNames(File[] files)
    {
        int length = files.length;
        String[] fileStrings = new String[length];

        for(int i = 0; i < length; i++)
        {
            fileStrings[i] = files[i].getName();
        }

        return fileStrings;
    }
}
