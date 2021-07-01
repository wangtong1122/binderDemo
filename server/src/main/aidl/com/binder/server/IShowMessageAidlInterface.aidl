// IShowMessageAidlInterface.aidl
package com.binder.server;

// Declare any non-default types here with import statements

interface IShowMessageAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void showMessage(String msg);
}