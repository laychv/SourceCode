## 骗过AMS, 反射 + 动态代理
流程:

app进程(Binder客户端)通过Binder机制,根据IActivityManager契约,与系统进程(Binder服务端)AMS通信

[大致流程](./img/HookAMS大致流程.png)

## Android SDK Framework 源码区别?
- IActivityManager
- ActivityManager
- ActivityThread

>> android [21-25]
>> H LAUNCH_ACTIVITY = 100;
>> case LAUNCH_ACTIVITY:
>>  ActivityClientRecord =
>> android [26-28]
>> H EXECUTE_TRANSACTION = 159;

android-21-25
- IActivityManager通过Java类,完成Binder通信

android-26-28
- IActivityManager通过AIDL生成,完成Binder通信

android-29-30


**调用过程:**

startActivity() -> startActivityForResult() -> Instrumentation.ActivityResult.execStartActivity() ->

ActivityManager.getService().startActivity()/ActivityTaskManager.getService().startActivity()

ActivityThread handleMessage() -> ClientTransaction private List<ClientTransactionItem> mActivityCallbacks; -> LaunchActivityItem (ClientTransactionItem) Intent


Activity.java
```Java

    @Override
    public void startActivity(Intent intent) {
        this.startActivity(intent, null);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        // ...
        if (options != null) {
            startActivityForResult(intent, -1, options);
        } else {
            startActivityForResult(intent, -1);
        }
    }

    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode,
            @Nullable Bundle options) {
        if (mParent == null) {
            options = transferSpringboardActivityOptions(options);
            Instrumentation.ActivityResult ar = // 关键方法
                mInstrumentation.execStartActivity(
                    this, mMainThread.getApplicationThread(), mToken, this,
                    intent, requestCode, options);
            // ...
        } else {
            // ...
        }
    }

```

Instrumentation.java
```Java
    public ActivityResult execStartActivity(
        Context who, IBinder contextThread, IBinder token, String target,
        Intent intent, int requestCode, Bundle options) {
        IApplicationThread whoThread = (IApplicationThread) contextThread;
        // ...
        // [26-28]
         try {
             intent.migrateExtraStreamToClipData();
             intent.prepareToLeaveProcess(who);
             int result = ActivityManager.getService().startActivity(whoThread,
                     who.getBasePackageName(), intent,
                     intent.resolveTypeIfNeeded(who.getContentResolver()),
                     token, target != null ? target.mEmbeddedID : null,
                     requestCode, 0, null, options);
                 checkStartActivityResult(result, intent);
             } catch (RemoteException e) {
                 throw new RuntimeException("Failure from system", e);
             }
        // [29-30]
        try {
            intent.migrateExtraStreamToClipData(who);
            intent.prepareToLeaveProcess(who);
            int result = ActivityTaskManager.getService().startActivity(whoThread,
                    who.getBasePackageName(), who.getAttributionTag(), intent,
                    intent.resolveTypeIfNeeded(who.getContentResolver()), token, target,
                    requestCode, 0, null, options);
            checkStartActivityResult(result, intent);
        } catch (RemoteException e) {
            throw new RuntimeException("Failure from system", e);
        }
        return null;
    }
```

// android [26-28]
ActivityManager.java
```Java

    public static IActivityManager getService() {
        return IActivityManagerSingleton.get();
    }

    private static final Singleton<IActivityManager> IActivityManagerSingleton =
            new Singleton<IActivityManager>() {
                @Override
                protected IActivityManager create() {
                    final IBinder b = ServiceManager.getService(Context.ACTIVITY_SERVICE);
                    final IActivityManager am = IActivityManager.Stub.asInterface(b);
                    return am;
                }
            };

```

// android [21-25]
IActivityManager.java
```Java

 public int startActivity(IApplicationThread caller, String callingPackage, Intent intent,
            String resolvedType, IBinder resultTo, String resultWho, int requestCode, int flags,
            ProfilerInfo profilerInfo, Bundle options) throws RemoteException;

```

// android [26-28]
IActivityManager.aidl
```aidl

    int startActivity(in IApplicationThread caller, in String callingPackage, in Intent intent,
            in String resolvedType, in IBinder resultTo, in String resultWho, int requestCode,
            int flags, in ProfilerInfo profilerInfo, in Bundle options);

```

// android [29-30]
ActivityManager.java
```Java

    public static IActivityManager getService() {
        return IActivityManagerSingleton.get();
    }

    private static IActivityTaskManager getTaskService() {
        return ActivityTaskManager.getService();
    }

    @UnsupportedAppUsage
    private static final Singleton<IActivityManager> IActivityManagerSingleton =
            new Singleton<IActivityManager>() {
                @Override
                protected IActivityManager create() {
                    final IBinder b = ServiceManager.getService(Context.ACTIVITY_SERVICE);
                    final IActivityManager am = IActivityManager.Stub.asInterface(b);
                    return am;
                }
            };
```

// android [21-25]
ActivityThread.java
```Java
    private class H extends Handler {
        public static final int LAUNCH_ACTIVITY         = 100;
        // ...
    }

    public void handleMessage(Message msg) {
        // ...
        switch (msg.what) {
            case LAUNCH_ACTIVITY: {
                Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
                final ActivityClientRecord r = (ActivityClientRecord) msg.obj;

                r.packageInfo = getPackageInfoNoCheck(
                        r.activityInfo.applicationInfo, r.compatInfo);
                handleLaunchActivity(r, null, "LAUNCH_ACTIVITY");
                Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
            } break;
            // ...
        }
    }

```

// android [26-28]
ActivityThread.java
```Java
    class H extends Handler {
        // ...
        public static final int EXECUTE_TRANSACTION = 159;
        // ...
    }

public void handleMessage(Message msg) {
    // ...
    switch (msg.what) {
        case EXECUTE_TRANSACTION:
                    final ClientTransaction transaction = (ClientTransaction) msg.obj;
                    // ...
                    break;
     }
     // ...
```