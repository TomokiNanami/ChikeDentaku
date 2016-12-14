package jp.promin.chike.android.dentakukku.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import jp.promin.chike.android.dentakukku.R;
import jp.promin.chike.android.dentakukku.databinding.ActivityMainBinding;
import jp.promin.chike.android.dentakukku.ui.view.FunctionButton;
import jp.promin.chike.android.dentakukku.ui.view.NumberButton;

public final class MainActivity extends AppCompatActivity
        implements MainActivityViewModel.Listener {
    ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainActivityViewModel();
        binding.setViewModel(viewModel);
        binding.setListener(this);
    }

    @Override
    public void onClickKey(View view) {

//        ViewCompat.animate(view)
//                .rotation(0f)
//                .rotationBy(360f)
//                .setDuration(1000)
//                .setInterpolator(new FastOutSlowInInterpolator())
//                .start();

        if (view instanceof NumberButton) {
            boolean result = viewModel.inputValue(((NumberButton) view).getNumber());
            if (!result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("えらー")
                        .setMessage("だめです")
                        .setNeutralButton("OK", null);
                builder.create().show();
            }
        } else if (view instanceof FunctionButton) {
            viewModel.doFunction(((FunctionButton) view).getButtonType());
        }
    }
}
