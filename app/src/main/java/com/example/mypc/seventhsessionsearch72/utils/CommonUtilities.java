package com.example.mypc.seventhsessionsearch72.utils;

import android.content.Context;

import com.example.mypc.seventhsessionsearch72.DBHelper;

/**
 * Created by my pc on 4/4/2017.
 */

public class CommonUtilities {

    /**
     * Check if singleton object of DB is null and not open; in the case
     * reinitialize and open DB.
     *
     * @param mContext
     */
    public static DBHelper getDBObject(Context mContext) {
        DBHelper dbhelper = DBHelper.getInstance(mContext);
        return dbhelper;
    }
}

