package controller;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class MainController extends SelectorComposer<Window> {

    @Wire
    Window HalamanKeranjangZul;

    @Wire
    Checkbox checkAll, checkBox1, checkBox2, checkBox3;

    @Wire
    Button btnAdd1, btnAdd2, btnMin1, btnMin2;

    @Wire
    Intbox ib1, ib2;

    @Wire
    Textbox ibTotalAll, ibTotalPerBarang;

    @Wire
    Label lb1;

    @Listen("onFocus=#ib1")
    public void onChangeIb1(){
        hitungTotal(47000, 60000);
    }

    @Listen("onBlur=#ib1")
    public void onBlurIb1(){
        hitungTotal(47000, 60000);
    }
    @Listen("onBlur=#ib2")
    public void onBlurIb2(){
        hitungTotal(47000, 60000);
    }
    @Listen("onFocus=#ib2")
    public void onChangeIb2(){
        hitungTotal(47000, 60000);
    }

    @Listen("onCheck = #checkAll")
    public void checkBoxAll(){
        Checkbox[] cbAll ={checkBox1, checkBox2, checkBox3};
        setCheck(checkAll, cbAll);
        checkBoxShop();
    }

    @Listen("onCheck = #checkBox1")
    public void checkBoxShop(){
        Checkbox[] cbAll ={checkBox2, checkBox3};
        setCheck(checkBox1, cbAll);
        if(checkBox2.isChecked()){
            ib1.setValue(1);
        }else{
            ib1.setValue(0);
        }

        if(checkBox3.isChecked()){
            ib2.setValue(1);
        }else{
            ib2.setValue(0);
        }
    }

    public void setCheck(Checkbox check, Checkbox[] cb){
        if(check.isChecked()){
            for(Checkbox cbA : cb){
                cbA.setChecked(true);
            }
        }else{
            for(Checkbox cbA: cb){
                cbA.setChecked(false);
            }
        }
    }

    @Listen("onCheck = #checkBox2")
    public void onCheckButton2(){
        if(checkBox2.isChecked()){
            ib1.setValue(1);
        }else{
            ib1.setValue(0);
        }
    }


    @Listen("onCheck = #checkBox3")
    public void onCheckButton3(){
        if(checkBox3.isChecked()){
            ib2.setValue(1);
        }else{
            ib2.setValue(0);
        }
    }

    @Listen("onClick = #btnAdd1")
    public void clickButtonAdd(){
        addButton(btnAdd1, ib1);
        hitungTotal(47000, 60000);
    }
    @Listen("onClick = #btnAdd2")
    public void clickButtonAdd2(){
        addButton(btnAdd2, ib2);
        hitungTotal(47000, 60000);
    }

    public void addButton(Button btn, Intbox ibx){
        int getVal = 0;
        int postVal = 0;
        if(ibx.getValue() == null || ibx.getValue().equals(null)){
            getVal = 0;
        }else {
            getVal = ibx.getValue();
            postVal = getVal + 1;
            ibx.setValue(postVal);
        }
    }

    @Listen("onClick = #btnMin1")
    public void clickButtonMin(){
        minButton(btnMin1, ib1);
        hitungTotal(47000, 60000);
    }
    @Listen("onClick = #btnMin2")
    public void clickButtonMin2(){
        minButton(btnMin2, ib2);
        hitungTotal(47000, 60000);
    }

    public void minButton(Button btn, Intbox ibx){
        int getVal = 0;
        int postVal = 0;
        if(ibx.getValue() == null|| ibx.getValue().equals(null)){
            getVal = 0;
        } else if(ibx.getValue() == 0) {
            ibx.setValue(0);
        }else{
            getVal = ibx.getValue();
            postVal = getVal - 1;
            ibx.setValue(postVal);
        }
    }

    public void hitungTotal(Integer Harga, Integer Harga1){
        Integer total = 0;
        if(ib1.getValue() == null|| ib1.getValue().equals(null) && ib2.getValue() == null|| ib2.getValue().equals(null)){
                ibTotalAll.setValue("Rp0.00");
        }else if(ib1.getValue().equals(0)|| ib1.getValue().equals(null)){
                if(ib2.getValue().equals(0) || ib2.getValue().equals(null)){
                    ibTotalAll.setValue("Rp0.00");
                }else{
                    total = Harga1 * ib2.getValue();
                    ibTotalAll.setValue("Rp"+total+",00");
                }
            }else{
                if(ib2.getValue().equals(0)|| ib2.getValue().equals(null)){
                    total = Harga * ib1.getValue();
                    ibTotalAll.setValue("Rp"+total+",00");
                }else{
                    total = (Harga * ib1.getValue()) + (Harga1 * ib2.getValue());
                    ibTotalAll.setValue("Rp"+total+",00");
                }
            }
        int qty = ib1.getValue() + ib2.getValue();
        lb1.setValue(String.valueOf(qty) +"(pcs)");
        ibTotalPerBarang.setValue(ibTotalAll.getValue());
        }

    }

