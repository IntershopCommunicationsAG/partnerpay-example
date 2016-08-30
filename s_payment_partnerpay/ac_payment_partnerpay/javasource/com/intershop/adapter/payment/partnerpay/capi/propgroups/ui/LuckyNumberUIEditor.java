package com.intershop.adapter.payment.partnerpay.capi.propgroups.ui;

import com.intershop.ui.web.capi.property.editor.ui.AbstractUIEditor;

/**
 * The UIEditor for the lucky number - a data container that can be serialized as JSON.
 */
public class LuckyNumberUIEditor extends AbstractUIEditor
{
    private Integer luckyNumber;

    public Integer getLuckyNumber()
    {
        return luckyNumber;
    }

    public void setLuckyNumber(Integer luckyNumber)
    {
        this.luckyNumber = luckyNumber;
    }

    @Override
    public boolean isInvalid()
    {
        return false;
    }

}
