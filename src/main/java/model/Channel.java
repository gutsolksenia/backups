package model;

import flow.Edge;

import java.io.Serializable;

/**
 * Created by ksenia on 23.04.18.
 */
public class Channel extends Edge implements Serializable {
    public Channel(SubNetwork from, SubNetwork to, int capacity) {
       super(from, to, capacity);
    }
}