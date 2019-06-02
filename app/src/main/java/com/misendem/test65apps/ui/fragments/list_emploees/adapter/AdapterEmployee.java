package com.misendem.test65apps.ui.fragments.list_emploees.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.EmployeeEntity;
import com.misendem.test65apps.ui.fragments.list_emploees.view.ViewHolderEmployee;

import java.util.ArrayList;

public class AdapterEmployee extends RecyclerView.Adapter<ViewHolderEmployee> {

    private ArrayList<EmployeeEntity> listSpeciality = new ArrayList<>();

    ListenerEmployeesList listenerEmployeesList;

    public AdapterEmployee(ListenerEmployeesList listenerEmployeesList) {
        this.listenerEmployeesList = listenerEmployeesList;
    }

    @NonNull
    @Override
    public ViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderEmployee(
                LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.view_item_employee,
                        viewGroup,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmployee viewHolderEmployee, int i) {
        viewHolderEmployee.onBind(listSpeciality.get(i));
        viewHolderEmployee.itemView.setOnClickListener(
                v -> listenerEmployeesList.onClickEmployee(listSpeciality.get(i))
        );
    }

    @Override
    public int getItemCount() {
        return listSpeciality.size();
    }

    public void addEmployee(EmployeeEntity employee) {
        listSpeciality.add(employee);
        notifyItemChanged(listSpeciality.size() - 1);
    }

    public interface ListenerEmployeesList {
        void onClickEmployee(EmployeeEntity employee);
    }
}
