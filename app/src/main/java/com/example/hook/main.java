package com.example.hook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class main implements IXposedHookLoadPackage {

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.example.testhook")) {

            XposedBridge.log(" has Hooked!");


            Class clazz = loadPackageParam.classLoader.loadClass("com.example.testhook.MainActivity");

            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    super.beforeHookedMethod(param);

                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    param.setResult("你已被劫持");

                }
            });

        }
    }
}