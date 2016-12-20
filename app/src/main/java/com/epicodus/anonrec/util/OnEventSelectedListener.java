package com.epicodus.anonrec.util;

import com.epicodus.anonrec.models.Event;

import java.util.ArrayList;

/**
 * Created by Guest on 12/20/16.
 */
public interface OnEventSelectedListener {
    public void OnEventSelected(Integer position, ArrayList<Event> events);
}
