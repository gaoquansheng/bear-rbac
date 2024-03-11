package com.bear.context;

import com.bear.dto.res.UserDetail;

public class UserContext {

    private static final ThreadLocal<UserDetail> threadLocal = ThreadLocal.withInitial(UserDetail::new);

    public static void set(UserDetail userDetail) {
        threadLocal.set(userDetail);
    }
    public static UserDetail get(){
        return threadLocal.get();
    }
    public static void remove() {
        threadLocal.remove();
    }
}
