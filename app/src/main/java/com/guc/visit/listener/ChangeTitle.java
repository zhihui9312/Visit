package com.guc.visit.listener;


public interface ChangeTitle {
    void controlBar(int resId, int backId, boolean isBack, boolean isRight);

    void controlBar(String resId, int backId, boolean isLeft, boolean isRight);

    void controlBar(String resId, String backId, boolean isLeft, boolean isRight);

    void controlBar(int resId, String backId, boolean isLeft, boolean isRight);
}
