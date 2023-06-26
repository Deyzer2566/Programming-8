package gui;

import javax.swing.*;

public class LabelWithData<E> {
    JLabel label;
    E obj;
    public LabelWithData(JLabel label, E obj){
        this.obj=obj;
        this.label=label;
    }

    public JLabel getLabel(){
        return label;
    }

    public void setLabel(JLabel label){
        this.label=label;
    }

    public E getObj(){
        return obj;
    }

    public void setObj(E obj){
        this.obj = obj;
    }
}
