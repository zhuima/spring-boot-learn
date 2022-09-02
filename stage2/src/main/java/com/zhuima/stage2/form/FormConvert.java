package com.zhuima.stage2.form;

public interface FormConvert<S, T> {
    T convert(S source);
}
