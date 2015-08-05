package lee.devis.joker;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("TTT", "Fragment onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i("TTT", "Fragment onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("TTT", "Fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i("TTT", "Fragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("TTT", "Fragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("TTT", "Fragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("TTT", "Fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("TTT", "Fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("TTT", "Fragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("TTT", "Fragment onDetach");
        super.onDetach();
    }
}
