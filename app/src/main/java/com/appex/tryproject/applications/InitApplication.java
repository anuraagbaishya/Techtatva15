package com.appex.tryproject.applications;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import io.fabric.sdk.android.Fabric;



@ReportsCrashes(
        mailTo = "anuraagbaishya@hotmail.com",
        mode = ReportingInteractionMode.SILENT)
public class InitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        ACRA.init(this);
        LeakCanary.install(this);
    }
}