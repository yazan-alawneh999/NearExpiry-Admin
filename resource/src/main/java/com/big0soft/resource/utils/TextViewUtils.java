package com.big0soft.resource.utils;

import static com.big0soft.resource.utils.StrUtils.isEmpty;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;

import com.big0soft.resource.helper.DateHelper;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public final class TextViewUtils {
    @BindingAdapter(value = {"android:parseNumber", "android:appendNumber"}, requireAll = false)
    public static void parseNumberToString(TextView textView, Number number, boolean append) {
        if (number == null) return;
        String text = append ? textView.getText().toString() + number : String.valueOf(number);
        textView.setText(text);
    }

    @BindingAdapter(value = {"android:_number","android:_appendToNumber"}, requireAll = false)
    public static void parseNumberToString(TextView textView, Number number,String append) {
        if (number == null) return;
        String text = null;
        try {
            text = String.valueOf(number);
            if (!isEmpty(append)){
                text = text + append;
            }
        } catch (Exception ignore) {}
        textView.setText(text);
    }


    @BindingAdapter("android:_restText")
    public static void setText(TextView textView, int text) {
        if (text == 0) return;
        textView.setText(text);
    }

    @BindingAdapter(value = "android:goneIfEmpty")
    public static void setGoneIfEmpty(TextView textView, boolean b) {
        if (!b) return;
        if (textView.length() == 0) {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {
            "android:appendText"
    })
    public static void appendText(TextView textView, String txt) {
        if (isEmpty(txt))return;
        textView.append(txt);
    }

    @BindingAdapter(value = "android:goneIfTrue")
    public static void setGoneIfTrue(TextView textView, boolean b) {
        if (b) {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {"android:parseDate"}, requireAll = false)
    public static void parseNumberToString(TextView textView, Date date) {
        if (date == null) return;
        android.icu.text.SimpleDateFormat simpleDateFormat = new android.icu.text.SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String format = simpleDateFormat.format(date);
        textView.setText(format);
    }

    @BindingAdapter("android:firstToUpperCase")
    public static void firstToUpperCase(TextView view, boolean falseDefault) {
//        if (!falseDefault || InputValidation.predicate.test(view.getText().toString())) return;
        view.setText(convertFirstCharacterToUpperCase(view.getText().toString()));
    }

    @BindingAdapter("android:toString")
    public static void numberToString(TextView textView, Number number) {
        textView.setText(String.valueOf(number));
    }

//    @BindingAdapter("android:quantityToString")
//    public static void quantityToString(TextView textView, double qty) {
//        textView.setText(MessageFormat.format("{0}{1}"
//                , textView.getResources().getText(R.string.quantity_).toString(), (int) qty));
//    }


    public static final String DEFAULT_DATE_PATTERN = "yyyy/mm/dd hh:MM:ss";

    @BindingAdapter("android:dateToString")
    public static void dateToString(TextView textView, LocalDateTime localDateTime) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN, Locale.US);
            localDateTime.format(formatter);
        } else {
            try {
                SimpleDateFormat simpleDateFormat = DateHelper.simpleDateFormat(DEFAULT_DATE_PATTERN);
                String localDateAndTime = localDateTime.toString();
                Date parse = simpleDateFormat.parse(localDateAndTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BindingAdapter(value = {"android:editorAction"})
    public static void setOnEditorAction(EditText editText, TextView.OnEditorActionListener onEditorActionListener) {
        if (onEditorActionListener == null) return;
        editText.setOnEditorActionListener(onEditorActionListener);
    }

//    @BindingAdapter("android:firstHintCharacterToUpperCase")
//    public static void firstHintCharacterToUpperCase(TextView view, boolean falseDefault) {
//        if (!falseDefault || InputValidation.predicate.test(view.getText().toString())) return;
//        view.setHint(convertFirstCharacterToUpperCase(view.getHint().toString()));
//    }
//    @BindingAdapter("android:firstHintCharacterToUpperCase")
//    public static void firstHintCharacterToUpperCase(TextInputLayout view, boolean falseDefault){
//       firstHintCharacterToUpperCase(view.getEditText(),falseDefault);
//    }

    public static String convertFirstCharacterToUpperCase(String txt) {
        String[] strArray = txt.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap).append(" ");
        }
        return builder.toString();
    }

    @BindingAdapter("android:textToPrice")
    public static void toPrice(TextView textView, Number number) {
        textView.setText(MessageFormat.format("$ {0}", number));
    }

    @BindingAdapter(value = {"android:beforeTextChanged2", "android:textChanged2",
            "android:afterTextChanged2", "android:textAttrChanged2"}, requireAll = false)
    public static void setTextWatcher2(TextView view, final BeforeTextChanged before,
                                       final OnTextChanged on, final AfterTextChanged after,
                                       final InverseBindingListener textAttrChanged) {
        final TextWatcher newValue;
        if (before == null && after == null && on == null && textAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (before != null) {
                        before.beforeTextChanged(s, view, start, count, after);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (on != null) {
                        on.onTextChanged(s, view, start, before, count);
                    }
                    if (textAttrChanged != null) {
                        textAttrChanged.onChange();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (after != null) {
                        after.afterTextChanged(s, view);
                    }
                }
            };
        }
        final TextWatcher oldValue = ListenerUtil.trackListener(view, newValue, 1);
        if (oldValue != null) {
            view.removeTextChangedListener(oldValue);
        }
        if (newValue != null) {
            view.addTextChangedListener(newValue);
        }
    }

    public interface AfterTextChanged {
        void afterTextChanged(Editable s, View view);
    }

    public interface BeforeTextChanged {
        void beforeTextChanged(CharSequence s, View view, int start, int count, int after);
    }

    public interface OnTextChanged {
        void onTextChanged(CharSequence s, View view, int start, int before, int count);
    }

}
