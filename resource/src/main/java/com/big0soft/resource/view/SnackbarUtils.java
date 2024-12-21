package com.big0soft.resource.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.big0soft.resource.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

public class SnackbarUtils {
    public static final int LONG = BaseTransientBottomBar.LENGTH_LONG;
    public static final int SHORT = BaseTransientBottomBar.LENGTH_SHORT;

    public static Snackbar speedSnackbar(View root, Object message, int duration) {
        if (Objects.isNull(message)) return speedSnackbar(root, R.string.error, SHORT);
        Snackbar snackbar = null;
        if (message instanceof Integer) {
            snackbar = Snackbar.make(root, ((Integer) message), duration);
        } else if (message instanceof String) {
            snackbar = Snackbar.make(root, ((String) message), duration);
        }
        return snackbar;
    }

    public static Snackbar speedSnackbar(View root, Object message, int duration, Object messageDismiss, OnClickSnackbar onClickSnackbar) {
        Snackbar snackbar = speedSnackbar(root, message, duration);
        if (messageDismiss instanceof Integer && onClickSnackbar != null) {
            snackbar.setAction(((Integer) messageDismiss), v -> {
                onClickSnackbar.onClick(snackbar);
            });
        } else if (messageDismiss instanceof String && onClickSnackbar != null) {
            snackbar.setAction(((String) messageDismiss), v -> {
                onClickSnackbar.onClick(snackbar);
            });
        }
        return snackbar;
    }


    /**
     * @param colorList index 0 is action text color || index 1 is message color
     */
    @NonNull
    public static Snackbar speedSnackbar(View root, Object message, int duration, Object messageDismiss, List<Integer> colorList, OnClickSnackbar onClickSnackbar) {
        Snackbar snackbar = speedSnackbar(root, message, duration, messageDismiss, onClickSnackbar);
        snackbar.setActionTextColor(colorList.get(0));
        View viewErrorUI = snackbar.getView();
        TextView textView = viewErrorUI.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(colorList.get(1));
        return snackbar;
    }


    public interface SnackbarUi {
        Snackbar snackbar(Object message);
    }

    public interface OnClickSnackbar {
        void onClick(Snackbar snackbar);
    }

    public static class ClickSnackbarImpl implements OnClickSnackbar {
        @Override
        public void onClick(Snackbar snackbar) {
            snackbar.dismiss();
        }
    }
}
