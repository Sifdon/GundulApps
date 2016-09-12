package com.gundulsoftware.gundulapps.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.activity.RecyclerItemClickListener;
import com.gundulsoftware.gundulapps.activity.SoalActivity;
import com.gundulsoftware.gundulapps.adapter.ItemKuisAdapter;
import com.gundulsoftware.gundulapps.helper.ItemKuis;

/**
 * Created by Ardika Bagus on 19-May-16.
 */
public class KuisSekarangFragment extends android.support.v4.app.Fragment {

    public KuisSekarangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kuis_sekarang, container, false);

        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.recycle_kuis_sekarang);
        ItemKuisAdapter itemAdapter = new ItemKuisAdapter(getActivity(), ItemKuis.getData("tes"));
        recycler.setAdapter(itemAdapter);

        LinearLayoutManager linearLM = new LinearLayoutManager(getActivity());
        linearLM.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLM);


        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                    alertDialog(position);

            }
        }));


        /*
        Spinner spinnerTingkatPendidikan = (Spinner) rootView.findViewById(R.id.spinner_tingkat_pendidikan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_tingkat_pendidikan,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTingkatPendidikan.setAdapter(adapter);

        String[] itemMataPelajaran = {
                "Matematika",
                "Bahasa Indonesia",
                "Bahasa Inggris",
                "Kimia",
                "Fisika",
                "Biologi"
        };

        List<String> listMataPelajaran = new ArrayList<String>(Arrays.asList(itemMataPelajaran));

        listMataPelajaranAdapter =  new ArrayAdapter<String>(getActivity(), R.layout.item_kuis_sekarang, R.id.list_item_card, listMataPelajaran);

        ListView listView = (ListView) rootView.findViewById(R.id.list_item_kuis_sekarang);
        listView.setAdapter(listMataPelajaranAdapter);
*/
        return rootView;

    }
    public void alertDialog(final int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Yakin ingin mengerjakan ?");

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intentMp = new Intent(getActivity(), SoalActivity.class);
                intentMp.putExtra("posisi",position);
                startActivity(intentMp);
            }
        });

        alertDialogBuilder.setNegativeButton("Batal",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
